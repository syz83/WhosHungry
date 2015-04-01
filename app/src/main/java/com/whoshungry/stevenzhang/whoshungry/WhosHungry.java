package com.whoshungry.stevenzhang.whoshungry;

import android.app.Application;

import com.facebook.model.GraphUser;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import java.util.List;

public class WhosHungry extends Application {

    static final String TAG = "MyApp";
    private List<GraphUser> selectedUsers;
    private List<Restaurant> pickedRestaurants;
    static final String GOOGLE_KEY = "AIzaSyBw4xKLv4O5TFX3O3UI7lkvTKb-oFyHREk";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "iBeTBcjpF8acRlEdj6RVTbQHtnnLUZOkHgE5revO", "lIK6djquppHAcj2tikjubAo8lI8X6uk0fdJx9sxn");

        // Set your Facebook App Id in strings.xml
        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));
    }

    public List<GraphUser> getSelectedUsers() {
        return selectedUsers;
    }
    public List<Restaurant> getPickedRestaurants() { return pickedRestaurants; }

    public void setSelectedUsers(List<GraphUser> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }
    public void setPickedRestaurants(List<Restaurant> pickedRestaurants) {
        this.pickedRestaurants = pickedRestaurants;
    }
}