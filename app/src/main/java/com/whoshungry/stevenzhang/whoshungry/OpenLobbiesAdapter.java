package com.whoshungry.stevenzhang.whoshungry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by saiavala on 3/8/15.
 */

public class OpenLobbiesAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<String> data;

    public OpenLobbiesAdapter(Context context, int resource, ArrayList<String> data) {
        super(context, resource, data);

        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.restaurant_view, parent, false);
        }

        return v;
    }
}
