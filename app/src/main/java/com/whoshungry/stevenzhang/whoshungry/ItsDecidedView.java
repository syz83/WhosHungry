package com.whoshungry.stevenzhang.whoshungry;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by saiavala on 4/3/15.
 */
public class ItsDecidedView extends LinearLayout {

    TextView restaurantName;
    TextView numReviews;
    TextView restaurantDistance;
    TextView restaurantCost;
    TextView restaurantType;

    RatingBar ratingBar;

    public ItsDecidedView(Context context) {
        this(context, null);
    }

    public ItsDecidedView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.its_decided_view, this, true);

        restaurantName = (TextView) findViewById(R.id.restaurantName);
        numReviews = (TextView) findViewById(R.id.numReviews);
        restaurantDistance = (TextView) findViewById(R.id.restaurantDistance);
        restaurantCost = (TextView) findViewById(R.id.restaurantCost);
        restaurantType = (TextView) findViewById(R.id.restaurantType);

    }

    public void setRestaurantName(String name) {
        this.restaurantName.setText(name);
    }

    public String getRestaurantName() {
        return restaurantName.getText().toString();
    }

    public void setNumReviews(String reviews) {
        this.numReviews.setText(reviews);
    }

    public String getNumReviews() {
        return numReviews.getText().toString();
    }

    public void setRestaurantDistance(String distance) {
        this.restaurantDistance.setText(distance);
    }

    public String getRestaurantDistance() {
        return restaurantDistance.getText().toString();
    }

    public void setRestaurantCost(String cost) {
        this.restaurantCost.setText(cost);
    }

    public String getRestaurantCost() {
        return restaurantCost.getText().toString();
    }

    public void setRestaurantType(String restType) {
        this.restaurantType.setText(restType);
    }

    public String getRestaurantType() {
        return restaurantType.getText().toString();
    }

    public void setRating(float rating) {
        this.ratingBar.setRating(rating);
    }

    public float getRating() {
        return ratingBar.getRating();
    }
}
