package com.whoshungry.stevenzhang.whoshungry;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Created by saiavala on 3/8/15.
 */
public class RestaurantView extends RelativeLayout {

    public RestaurantView(Context context) {
        this(context, null);
    }

    public RestaurantView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.restaurant_view, this, true);
    }
}
