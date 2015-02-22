package com.whoshungry.stevenzhang.whoshungry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Main/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_create_lobby) {
            Intent intent = new Intent(this, CreateLobby.class);
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

}
