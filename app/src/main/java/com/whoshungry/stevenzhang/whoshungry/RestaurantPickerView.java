package com.whoshungry.stevenzhang.whoshungry;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by saiavala on 4/2/15.
 */
public class RestaurantPickerView extends RelativeLayout {

    TextView restaurantName;
    TextView numReviews;
    TextView restaurantDistance;
    TextView restaurantCost;
    TextView restaurantType;

    RatingBar ratingBar;
    CheckBox checkBox;

    public RestaurantPickerView(Context context) {
        this(context, null);
    }

    public RestaurantPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.restaurant_picker_view, this, true);

        restaurantName = (TextView) findViewById(R.id.restaurantName);
        numReviews = (TextView) findViewById(R.id.numReviews);
        restaurantDistance = (TextView) findViewById(R.id.restaurantDistance);
        restaurantCost= (TextView) findViewById(R.id.restaurantCost);
        restaurantType = (TextView) findViewById(R.id.restaurantType);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        checkBox = (CheckBox) findViewById(R.id.chooseRestaurant);
    }

    public void setRestaurantName(String name) {
        this.restaurantName.setText(name);
    }

    public String getName() {
        return restaurantName.getText().toString();
    }

    public void setNumReviews(String numReviews) {
        this.numReviews.setText(numReviews);
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

    public void setRestaurantType(String rType) {
        this.restaurantType.setText(rType);
    }

    public String getRestaurantType() {
        return restaurantType.getText().toString();
    }

    public void setRating(float rating) {
        ratingBar.setRating(rating);
    }

    public float getRating() {
        return ratingBar.getRating();
    }

    public void setCheckBox(boolean check) {
        checkBox.setChecked(check);
    }

    public void setChecked() {
        checkBox.setChecked(true);
    }

    public void setUnchecked() {
        checkBox.setChecked(false);
    }

    public boolean getChecked() {
       return checkBox.isChecked();
    }
}
