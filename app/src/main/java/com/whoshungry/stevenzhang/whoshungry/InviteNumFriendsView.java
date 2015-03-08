package com.whoshungry.stevenzhang.whoshungry;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class InviteNumFriendsView extends RelativeLayout {

    TextView numFriendsTextView;

    public InviteNumFriendsView(Context context) {
        this(context, null);
    }

    public InviteNumFriendsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.invite_num_friends_view, this, true);

        numFriendsTextView = (TextView) findViewById(R.id.num_friends);
    }

    public void setNumFriends(Integer numFriends) {
        numFriendsTextView.setText(String.valueOf(numFriends));
    }


}