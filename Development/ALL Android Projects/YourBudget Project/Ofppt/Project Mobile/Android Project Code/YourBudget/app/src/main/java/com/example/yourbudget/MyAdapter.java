package com.example.yourbudget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Categories> {

    public MyAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public MyAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater li =
                (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = li.inflate(R.layout.spinner_custom,null); // null : pas de parent

        TextView name = (TextView) v.findViewById(R.id.textView5);
        ImageView icon = (ImageView) v.findViewById(R.id.imageView10);
        Categories cl = getItem(position);
        name.setText(cl.getName());
        icon.setImageResource(cl.getImage());


        return v;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater li =
                (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = li.inflate(R.layout.spinner_custom,null); // null : pas de parent

        TextView name = (TextView) v.findViewById(R.id.textView5);
        ImageView icon = (ImageView) v.findViewById(R.id.imageView10);
        Categories cl = getItem(position);
        name.setText(cl.getName());
        icon.setImageResource(0);


        return v;


    }

}
