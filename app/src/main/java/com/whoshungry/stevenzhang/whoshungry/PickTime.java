package com.whoshungry.stevenzhang.whoshungry;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by stevenzhang on 4/6/15.
 */
public class PickTime extends Fragment{
    private View myFragView;
    private Button done_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragView = inflater.inflate(R.layout.when_are_you_thinking, container, false);
        done_btn = (Button) myFragView.findViewById(R.id.done_btn);
        setButtonListeners();
        return myFragView;
    }

    public void setButtonListeners() {
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.parseColor("#55F36F46"));
                PickCuisine nextFrag= new PickCuisine();
                getFragmentManager().beginTransaction()
                        .replace(R.id.main, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
