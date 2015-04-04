package com.whoshungry.stevenzhang.whoshungry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.model.GraphUser;
import com.facebook.widget.FriendPickerFragment;
import com.facebook.widget.PickerFragment;

import java.util.List;

/**
 * Created by stevenzhang on 11/16/14.
 */
public class FriendPicker extends FragmentActivity {

    FriendPickerFragment friendPickerFragment;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_picker);
        FragmentManager fm = getSupportFragmentManager();

        if (savedInstanceState == null) {
            // First time through, we create our fragment programmatically.
            friendPickerFragment = new FriendPickerFragment();
            String userid = friendPickerFragment.getUserId();
            //Log.d("USER_ID", userid);
            fm.beginTransaction()
                    .add(R.id.friend_picker_fragment, friendPickerFragment)
                    .commit();
        } else {
            // Subsequent times, our fragment is recreated by the framework and already has saved and
            // restored its state, so we don't need to specify args again. (In fact, this might be
            // incorrect if the fragment was modified programmatically since it was created.)
            friendPickerFragment = (FriendPickerFragment) fm.findFragmentById(R.id.friend_picker_fragment);
        }

        friendPickerFragment.setOnErrorListener(new PickerFragment.OnErrorListener() {
            @Override
            public void onError(PickerFragment<?> fragment, FacebookException error) {
                FriendPicker.this.onError(error);
            }
        });

        friendPickerFragment.setOnDoneButtonClickedListener(new PickerFragment.OnDoneButtonClickedListener() {
            @Override
            public void onDoneButtonClicked(PickerFragment<?> fragment) {
                // We just store our selection in the Application for other activities to look at.
                WhosHungry application = (WhosHungry) getApplication();
                application.setSelectedUsers(friendPickerFragment.getSelection());

                setResult(RESULT_OK, null);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.friend_picker_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Main/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.contacts) {
//            WhosHungry application = (WhosHungry) getApplication();
//            application.setPickedRestaurants(myPicks);
            Intent intent = new Intent(this, ContactFriendsList.class);
            startActivity(intent);
        }
        if (id == R.id.action_log_out) {
            // this needs to log you out and bring you back to the sign in screen

            //           Intent intent = new Intent(this, CreateLobby.class);
            //           startActivity(intent);
        }
        if (id == R.id.action_settings) {
            //we need a settings page

            //          Intent intent = new Intent(this, Settings.class);
            //         startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void onError(Exception error) {
        String text = getString(R.string.exception, error.getMessage());
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            WhosHungry application = (WhosHungry) getApplication();
            List<GraphUser> selectedUsers = application.getSelectedUsers();
            if (selectedUsers != null && !selectedUsers.isEmpty()) {
                friendPickerFragment.setSelection(selectedUsers);
            }
            // Load data, unless a query has already taken place.
            friendPickerFragment.loadData(false);
        } catch (Exception ex) {
            onError(ex);
        }
    }

}
