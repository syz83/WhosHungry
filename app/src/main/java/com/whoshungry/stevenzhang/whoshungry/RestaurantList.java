package com.whoshungry.stevenzhang.whoshungry;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stevenzhang on 12/5/14.
 */
public class RestaurantList {

    @SerializedName("results")
    List<Restaurant> restaurantList;
}
