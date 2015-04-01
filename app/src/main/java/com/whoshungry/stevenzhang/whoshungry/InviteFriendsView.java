package com.whoshungry.stevenzhang.whoshungry;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by saiavala on 3/31/15.
 */
public class InviteFriendsView extends LinearLayout {

    TextView friendName;
    CheckBox checkBox;

    public InviteFriendsView(Context context) {
        this(context, null);
    }

    public InviteFriendsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.invite_friends_view, this, true);

        friendName = (TextView) findViewById(R.id.friendName);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
    }

    public void setFriendName(String friendName) {
        this.friendName.setText(friendName);
    }

    public String getFriendName() {
        return friendName.getText().toString();
    }

    public void setCheckBoxValue(boolean value) {
        this.checkBox.setChecked(value);
    }

    public void setChecked() {
        this.checkBox.setChecked(true);
    }

    public void setUnchecked() {
        this.checkBox.setChecked(false);
    }

    public boolean getCheckedValue() {
        return checkBox.isChecked();
    }
}
