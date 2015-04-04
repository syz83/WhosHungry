package com.whoshungry.stevenzhang.whoshungry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stevenzhang on 4/3/15.
 */
public class ContactFriendsList extends Activity {

    ContactListAdapter contactListAdapter;
    List<String> contactsList;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_friend_list);

        contactsList = new ArrayList<>();
        contactsList.add("Steven Zhang");
        contactListAdapter = new ContactListAdapter(this, contactsList);
        listView = (ListView) findViewById(R.id.contacts_list_view);
        listView.setAdapter(contactListAdapter);


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
        if (id == R.id.fb) {
//            WhosHungry application = (WhosHungry) getApplication();
//            application.setPickedRestaurants(myPicks);
            Intent intent = new Intent(this, FriendPicker.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public class ContactListAdapter extends BaseAdapter {
        Context context;
        List<String> contactsContractList;

        public ContactListAdapter(Context context, List<String> contactsContractList) {
            this.context = context;
            this.contactsContractList = contactsContractList;
        }


        @Override
        public int getCount() {
            return contactsContractList.size();
        }

        @Override
        public String getItem(int position) {
            return contactsContractList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (convertView == null) {
                InviteFriendsView inviteFriendsView = new InviteFriendsView(context);
                //inviteFriendsView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                inviteFriendsView.setLayoutParams(new AbsListView.LayoutParams(
                        AbsListView.LayoutParams.MATCH_PARENT,
                        AbsListView.LayoutParams.WRAP_CONTENT));

                inviteFriendsView.setFriendName(contactsContractList.get(position));

                convertView = inviteFriendsView;
                v = convertView;
            }

            return v;


//        for(int i = 0; i<myPicks.size(); i++)
//            if(myPicks.get(i).name.equals(contactsContractList.get(position).name))
//                v.setBackgroundColor(Color.parseColor("#F36F46"));

        }
    }
}