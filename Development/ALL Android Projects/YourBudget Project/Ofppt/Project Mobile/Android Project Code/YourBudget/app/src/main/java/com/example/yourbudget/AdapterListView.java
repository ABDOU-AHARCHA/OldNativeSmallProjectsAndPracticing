package com.example.yourbudget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListView extends BaseAdapter {
    Context context;
    ArrayList<CustomListView> liste;


    public AdapterListView (Context context,ArrayList<CustomListView> liste){
        this.context=context;
        this.liste=liste;

    }


    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public Object getItem(int i) {
        return liste.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_listview_incomes,null);
            TextView tv1=(TextView) view.findViewById(R.id.textView13);
            TextView tv2=(TextView) view.findViewById(R.id.textView14);
            TextView tv3=(TextView) view.findViewById(R.id.textView15);



            CustomListView object= liste.get(i);
            tv1.setText(object.getName());
            tv2.setText(object.getDate());
            tv3.setText(object.getAmount());

//            if(Integer.valueOf(object.getAmount())>1000){
//                image.setImageResource(0);
//                image.setImageResource(R.drawable.logout);
//
//            }
//            if(Integer.valueOf(object.getAmount())<1000){
//                image.setImageResource(0);
//                image.setImageResource(R.drawable.assurance);
//
//            }


        return view;
    }
}
