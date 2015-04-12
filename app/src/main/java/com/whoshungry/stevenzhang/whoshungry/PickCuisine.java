package com.whoshungry.stevenzhang.whoshungry;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by stevenzhang on 4/6/15.
 */
public class PickCuisine extends Fragment {
    private View myFragView;
    private LinearLayout bar_food_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragView = inflater.inflate(R.layout.what_kind_of_cuisine, container, false);
        bar_food_btn = (LinearLayout) myFragView.findViewById(R.id.bar_food_btn);
        setButtonListeners();
        return myFragView;
    }

    public void setButtonListeners() {
        bar_food_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.parseColor("#55F36F46"));
                PickLocal nextFrag= new PickLocal();
                getFragmentManager().beginTransaction()
                        .replace(R.id.main, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
