package com.whoshungry.stevenzhang.whoshungry;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by stevenzhang on 11/20/14.
 */
public class PickPlace extends Activity implements LocationListener{
    GooglePlacesService googlePlacesService;

    PlaceListAdapter listAdapter;
    ListView listView;
    LocationManager locationManager;
    private String provider;
    double lat, lng;

    List<Restaurant> items;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_places);

        //Get LocationManager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the location provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Log.d("Provider", "Provider " + String.valueOf(provider));

            Log.d("LocationGPS", "Initializing location");
            locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                    5000,   // 1 sec
                    0, this);



        Gson gson = new GsonBuilder()
                .create();

        //Make RestAdapter
        RestAdapter restAdapter = new RestAdapter.Builder()
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://maps.googleapis.com/maps/api/place")
                .setConverter(new GsonConverter(gson))
                .build();

        new LoadRestaurantsTask(this).execute();

        items = new ArrayList<Restaurant>();

        listView = (ListView) findViewById(R.id.restaurant_list_view);
        googlePlacesService = restAdapter.create(GooglePlacesService.class);
        Log.d("Adapter", "Going to create new PlaceListAdapter");
        listAdapter = new PlaceListAdapter(this, items);
        Log.d("Adapter", "Setting adapter");
        listView.setAdapter(listAdapter);
        Log.d("Adapter", "Adapter set");
        Log.d("Adapter", items.toString());

    }

    private class LoadRestaurantsTask extends AsyncTask<Void, Void, List<Restaurant>> {
        private ProgressDialog dialog;

        public LoadRestaurantsTask(PickPlace activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
            items.clear();
        }

        @Override
        protected List<Restaurant> doInBackground(Void... params) {
            String lat_string = String.valueOf(lat);
            String lng_string = String.valueOf(lng);
//            Log.d("LocationGPS", lat_string);
//            Log.d("LocationGPS", lng_string);
//            Log.d("Before", "Hello");
            RestaurantList restaurantList = googlePlacesService.restaurants(lat_string + "," + lng_string, "5000", WhosHungry.GOOGLE_KEY, "food");

            for (Restaurant restaurant : restaurantList.restaurantList){
                items.add(restaurant);
            }
            return items;
        }

        @Override
        protected void onPostExecute(List<Restaurant> result) {
            Log.d("PostExecute", "Yes we got here");
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

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            Log.d("getView", String.valueOf(position));
//            LinearLayout view = convertView != null
//                    ? (LinearLayout) convertView
//                    : (LinearLayout) getLayoutInflater().inflate(R.layout.restaurant_item, parent, false);
            View v = convertView;
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                v = inflater.inflate(R.layout.restaurant_item, parent, false);
            }

            TextView text = (TextView) v.findViewById(R.id.restaurant_text);
            text.setText(restaurantsList.get(position).name);

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
