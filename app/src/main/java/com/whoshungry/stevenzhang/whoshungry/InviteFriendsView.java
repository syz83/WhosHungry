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
        layoutInflater.inflate(R.layout.invite_friends_view_item, this, true);

        friendName = (TextView) findViewById(R.id.friendName);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
    }

    /**
     * Set the TextView value for your Friends name
     * @param friendName
     */
    public void setFriendName(String friendName) {
        this.friendName.setText(friendName);
    }

    /**
     * Get the TextView value for your Friends name
     * @return
     */
    public String getFriendName() {
        return friendName.getText().toString();
    }

    /**
     * Provide a booolean - true/false. The checkbox value will be set to that
     * @param value
     */
    public void setCheckBoxValue(boolean value) {
        this.checkBox.setChecked(value);
    }

    /**
     * Set the checkbox value to CHECKED
     */
    public void setChecked() {
        this.checkBox.setChecked(true);
    }

    /**
     * Set the checkbox value to UNCHECKED
     */
    public void setUnchecked() {
        this.checkBox.setChecked(false);
    }

    /**
     * Get the checkbox value
     * @return
     */
    public boolean getCheckedValue() {
        return checkBox.isChecked();
    }
}
