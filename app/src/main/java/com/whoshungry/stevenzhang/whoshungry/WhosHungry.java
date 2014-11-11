package com.whoshungry.stevenzhang.whoshungry;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

public class WhosHungry extends Application {

    static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "iBeTBcjpF8acRlEdj6RVTbQHtnnLUZOkHgE5revO", "lIK6djquppHAcj2tikjubAo8lI8X6uk0fdJx9sxn");

        // Set your Facebook App Id in strings.xml
        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));

    }
}