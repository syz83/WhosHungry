package com.whoshungry.stevenzhang.whoshungry;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


public class CreateLobby extends FragmentActivity {


    private TextView mDisplayTime;

    private Button mTimeButton;
    private Button mPlaceButton;
    private Button mInviteButton;

    private Button mLunchButton;
    private Button mDinnerButton;
    private Button mCoffeeButton;
    private Button mDrinksButton;

    private String mLobbyType;


    private int hour;
    private int minute;
    private String amORpm;

    static final int TIME_DIALOG_ID = 999;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lobby);

        mDisplayTime = (TextView) findViewById(R.id.button_time);

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        mTimeButton = (Button) findViewById(R.id.button_time);

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(TIME_DIALOG_ID);
            }
        });

        mPlaceButton = (Button) findViewById(R.id.button_place);

        mPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO

            }
        });

        mInviteButton = (Button) findViewById(R.id.button_invite);

        mInviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                inviteFriends(view);

            }
        });

        mLunchButton = (Button) findViewById(R.id.button_lunch);
        mLunchButton.setBackgroundColor(Color.LTGRAY);

        mLunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLobbyType = "lunch";

                mLunchButton.setBackgroundColor(Color.DKGRAY);

                mDinnerButton.setBackgroundColor(Color.LTGRAY);
                mDrinksButton.setBackgroundColor(Color.LTGRAY);
                mCoffeeButton.setBackgroundColor(Color.LTGRAY);
            }
        });

        mDinnerButton = (Button) findViewById(R.id.button_dinner);
        mDinnerButton.setBackgroundColor(Color.LTGRAY);

        mDinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLobbyType = "dinner";

                mDinnerButton.setBackgroundColor(Color.DKGRAY);

                mLunchButton.setBackgroundColor(Color.LTGRAY);
                mDrinksButton.setBackgroundColor(Color.LTGRAY);
                mCoffeeButton.setBackgroundColor(Color.LTGRAY);

            }
        });

        mDrinksButton = (Button) findViewById(R.id.button_drinks);
        mDrinksButton.setBackgroundColor(Color.LTGRAY);

        mDrinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLobbyType = "drinks";

                mDrinksButton.setBackgroundColor(Color.DKGRAY);

                mLunchButton.setBackgroundColor(Color.LTGRAY);
                mDinnerButton.setBackgroundColor(Color.LTGRAY);
                mCoffeeButton.setBackgroundColor(Color.LTGRAY);
            }
        });

        mCoffeeButton = (Button) findViewById(R.id.button_coffee);
        mCoffeeButton.setBackgroundColor(Color.LTGRAY);

        mCoffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLobbyType = "coffee";

                mCoffeeButton.setBackgroundColor(Color.DKGRAY);

                mLunchButton.setBackgroundColor(Color.LTGRAY);
                mDinnerButton.setBackgroundColor(Color.LTGRAY);
                mDrinksButton.setBackgroundColor(Color.LTGRAY);

            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this,
                        timePickerListener, hour, minute,false);

        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    convertTimeToDisplay();

                }
            };

    private void convertTimeToDisplay() {
        if (hour < 12) {
            amORpm = "AM";
        }
        else { amORpm = "PM"; }
        if (hour > 12) { hour = hour - 12; }
        if (hour == 0) { hour = 12; }

        // set current time into textview
        mDisplayTime.setText(
                new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute))
                        .append(" ").append(amORpm));
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_lobby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Main/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void inviteFriends(View view){
        Log.d("Friend", "Get here?");
        Intent intent = new Intent(this, FriendPicker.class);
        startActivity(intent);
    }


}
