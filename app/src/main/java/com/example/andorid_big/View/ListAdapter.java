package com.example.andorid_big.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.andorid_big.Model.Sign_List;
import com.example.andorid_big.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private int resourceId;

    public ListAdapter(Context context, int textViewResourceId, List<Sign_List> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, View covertView, ViewGroup parent) {
        Sign_List user=(Sign_List)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);

        TextView textView_1=view.findViewById(R.id.list_name);
        TextView textView_2=view.findViewById(R.id.list_time);
        textView_1.setText(user.getName());
        textView_2.setText(user.getDate());
        return view;
    }
}
