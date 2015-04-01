package com.whoshungry.stevenzhang.whoshungry;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by stevenzhang on 11/20/14.
 */
public class PickPlace extends Activity implements LocationListener{
    GooglePlacesService googlePlacesService;

    PlaceListAdapter listAdapter;
    ListView listView;
    double lat, lng;

    List<Restaurant> restList;
    Set<Restaurant> filter;

    List<Restaurant> myPicks;
    int[] myPickPositions;
    int count;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_places);
        //getActionBar().setTitle("");

        GPSService gpsService = new GPSService(this);
        gpsService.getLocation();

        lat = gpsService.getLatitude();
        lng = gpsService.getLongitude();

        Gson gson = new GsonBuilder()
                .create();

        //Make RestAdapter
        RestAdapter restAdapter = new RestAdapter.Builder()
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://maps.googleapis.com/maps/api/place")
                .setConverter(new GsonConverter(gson))
                .build();

        new LoadRestaurantsTask(this).execute();

        restList = new ArrayList<Restaurant>();
        filter = new HashSet<Restaurant>();
        myPicks = new ArrayList<Restaurant>();
        myPickPositions = new int[3];
        for(int i = 0; i<3; i++)
            myPickPositions[i] = -1;
        count = 0;

        listView = (ListView) findViewById(R.id.restaurant_list_view);
        googlePlacesService = restAdapter.create(GooglePlacesService.class);
        Log.d("Adapter", "Going to create new PlaceListAdapter");
        listAdapter = new PlaceListAdapter(this, restList);
        listView.setAdapter(listAdapter);
        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //here you can use the position to determine what checkbox to check
                //this assumes that you have an array of your checkboxes as well. called checkbox
                if(myPicks.size()<3) {
                    view.setBackgroundColor(Color.parseColor("#F36F46"));
                    myPickPositions[count] = position;
                    count++;
                    //((ListView)parent).setItemChecked(position, true);

                    myPicks.add(restList.get(position));
                    Toast.makeText(getApplicationContext(), restList.get(position).name, Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "You have already chosen 3 restaurants", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pick_places_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Main/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_submit_myPicks) {
            Log.d("My Restaurants", myPicks.toString());
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
        }
        if (id == R.id.action_log_out) {
            // this needs to log you out and bring you back to the sign in screen

            //           Intent intent = new Intent(this, CreateLobby.class);
            //           startActivity(intent);
        }
        if (id == R.id.action_settings) {
            //we need a settings page

            //          Intent intent = new Intent(this, Settings.class);
            //         startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private class LoadRestaurantsTask extends AsyncTask<Void, Void, List<Restaurant>> {
        private ProgressDialog dialog;
        private List<Restaurant> items;

        public LoadRestaurantsTask(PickPlace activity) {
            dialog = new ProgressDialog(activity);
            items = new ArrayList<>();
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
        }

        @Override
        protected List<Restaurant> doInBackground(Void... params) {
            String lat_string = String.valueOf(lat);
            String lng_string = String.valueOf(lng);
            Log.d("LocationGPS", lat_string);
            Log.d("LocationGPS", lng_string);
            Log.d("Before", "Hello");
            RestaurantList restaurantList = googlePlacesService.restaurants(lat_string + "," + lng_string, "5000", WhosHungry.GOOGLE_KEY, "food");
            Log.d("After", String.valueOf(restaurantList.restaurantList.size()));
            for (Restaurant restaurant : restaurantList.restaurantList){
                if(!filter.contains(restaurant)) {
                    filter.add(restaurant);
                    items.add(restaurant);
                }
            }
            return items;
        }

        @Override
        protected void onPostExecute(List<Restaurant> result) {
            Log.d("PostExecute", "Yes we got here");

            restList.addAll(result);
            Log.d("onPostExecute", restList.toString());
            listAdapter.notifyDataSetChanged();
            if(dialog.isShowing()) {
                dialog.dismiss();
            }

        }

    }


    public class PlaceListAdapter extends BaseAdapter {
        Context context;
        List<Restaurant> restaurantsList;

        public PlaceListAdapter(Context context, List<Restaurant> restaurantsList){
            this.context = context;
            this.restaurantsList = restaurantsList;
        }

//        public void updateRestaurants(){
//            new LoadRestaurantsTask(this).execute();
//            notifyDataSetChanged();
//        }

        @Override
        public int getCount() {
            return restaurantsList.size();
        }

        @Override
        public Restaurant getItem(int position) {
            return restaurantsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d("getView", String.valueOf(position));
//            LinearLayout view = convertView != null
//                    ? (LinearLayout) convertView
//                    : (LinearLayout) getLayoutInflater().inflate(R.layout.restaurant_item, parent, false);
            View v = convertView;
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                v = inflater.inflate(R.layout.restaurant_item, parent, false);
                v.setBackgroundColor(Color.WHITE);
            }

            TextView text = (TextView) v.findViewById(R.id.restaurant_text);
            text.setText(restaurantsList.get(position).name);

            v.setBackgroundColor(Color.WHITE);

            for(int i = 0; i<myPickPositions.length; i++)
                if(myPickPositions[i]==position)
                    v.setBackgroundColor(Color.parseColor("#F36F46"));

            return v;
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        Log.d("LocationGPS?", "Location " + String.valueOf(location));
        lat = (location.getLatitude());
        lng = (location.getLongitude());

        String lat_string = String.valueOf(lat);
        String lng_string = String.valueOf(lng);
        Log.d("LocationGPS", lat_string);
        Log.d("LocationGPS", lng_string);

        new LoadRestaurantsTask(this).execute();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}
