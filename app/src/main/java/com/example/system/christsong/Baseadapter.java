package com.example.system.christsong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class Baseadaptarcustom extends ArrayAdapter<String> {
private final Context context;
private final String[] title;
private final String[] songnumber;


public Baseadaptarcustom(Context context, String[] title, String[] songnumber) {
        super(context, R.layout.subjectivelayout,title);
        this.context=context;
        this.title=title;
        this.songnumber=songnumber;


        }
@Override
public View getView(int position, View view, ViewGroup parent){
        LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview= layoutInflater.inflate(R.layout.listofsongs_layout,parent,false);
        TextView titlev=(TextView) rowview.findViewById(R.id.songtitle);
        TextView songnumberv=(TextView)rowview.findViewById(R.id.songnumber);
        titlev.setText(title[position]);
        songnumberv.setText(songnumber[position]);
        return rowview;



        }
        }

