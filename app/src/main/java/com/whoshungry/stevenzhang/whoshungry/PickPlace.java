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
    LocationManager locationManager;
    Location mLocation;
    private String provider;
    double lat, lng;

    List<Restaurant> restList;
    Set<Restaurant> filter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_places);

        //Get LocationManager
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // Define the criteria how to select the location provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Log.d("Provider", "Provider " + String.valueOf(provider));
        Log.d("Provider", LocationManager.GPS_PROVIDER);

        boolean isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        Log.d("Boolean", String.valueOf(isGPSEnabled));

        Log.d("LocationGPS", "Initializing location");
        locationManager.requestLocationUpdates(provider,
                    5000,   // 1 sec
                    0, this);

        mLocation = locationManager
                .getLastKnownLocation(provider);

        if(mLocation!=null) {
            lat = mLocation.getLatitude();
            lng = mLocation.getLongitude();
        }

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

        listView = (ListView) findViewById(R.id.restaurant_list_view);
        googlePlacesService = restAdapter.create(GooglePlacesService.class);
        Log.d("Adapter", "Going to create new PlaceListAdapter");
        listAdapter = new PlaceListAdapter(this, restList);
        Log.d("Adapter", "Setting adapter");
        listView.setAdapter(listAdapter);
        Log.d("Adapter", "Adapter set");
        Log.d("Adapter", restList.toString());

    }

    private class LoadRestaurantsTask extends AsyncTask<Void, Void, List<Restaurant>> {
        private ProgressDialog dialog;
        private List<Restaurant> items;

        public LoadRestaurantsTask(PickPlace activity) {
            dialog = new ProgressDialog(activity);
            items = new ArrayList<Restaurant>();
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
                    Log.d("Why", "WHYYYYY");
                    filter.add(restaurant);
                    items.add(restaurant);
                }
            }
            return items;
        }

        @Override
        protected void onPostExecute(List<Restaurant> result) {
            Log.d("PostExecute", "Yes we got here");

            restList = result;

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
