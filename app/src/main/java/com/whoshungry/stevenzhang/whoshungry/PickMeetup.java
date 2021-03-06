package com.whoshungry.stevenzhang.whoshungry;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by stevenzhang on 3/31/15.
 */
public class PickMeetup extends Fragment {

    private View myFragView;
    private LinearLayout mBreakfast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragView = inflater.inflate(R.layout.what_kind_of_meetup, container, false);
        mBreakfast = (LinearLayout) myFragView.findViewById(R.id.breakfast);
        setButtonListeners();
        return myFragView;
    }

    public void setButtonListeners() {
        mBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.parseColor("#55F36F46"));
                PickTime nextFrag= new PickTime();
                getFragmentManager().beginTransaction()
                        .replace(R.id.main, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
