package com.whoshungry.stevenzhang.whoshungry;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by stevenzhang on 4/6/15.
 */
public class PickLocal extends Fragment{
    private View myFragView;
    private LinearLayout near_me_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragView = inflater.inflate(R.layout.where_do_you_wanna_go, container, false);
        near_me_btn = (LinearLayout) myFragView.findViewById(R.id.near_me_btn);
        setButtonListeners();
        return myFragView;
    }

    public void setButtonListeners() {
        near_me_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.parseColor("#55F36F46"));
                Intent intent = new Intent(getActivity(), PickPlace.class);
                startActivity(intent);
            }
        });
    }
}
