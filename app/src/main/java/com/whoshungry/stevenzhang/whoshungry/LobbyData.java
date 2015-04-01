package com.whoshungry.stevenzhang.whoshungry;

/**
 * Created by stevenzhang on 3/29/15.
 */
public class LobbyData {
    private Restaurant[] restaurants;

    public void setRestaurants(Restaurant[] restaurants){
        this.restaurants = restaurants;
    }

    public Restaurant[] getRestaurants(){
        return this.restaurants;
    }

    private static final LobbyData data = new LobbyData();
    public static LobbyData getInstance(){
        return data;
    }

}
