package com.whoshungry.stevenzhang.whoshungry;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by stevenzhang on 11/20/14.
 */
public interface GooglePlacesService {
    @GET("/nearbysearch/json")
    RestaurantList restaurants(
            @Query("location") String location,
            @Query("radius") String radius,
            @Query("key") String key,
            @Query("types") String types
    );

}
