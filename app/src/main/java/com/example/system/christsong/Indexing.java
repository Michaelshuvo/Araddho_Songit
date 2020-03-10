package com.example.system.christsong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dao.DatabaseQueryClass;
import dao.Song;

public class Indexing extends Fragment {
    View view;
    GridView gridView;
    public Indexing(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.indexing,container,false);
       gridView=view.findViewById(R.id.gridview);
        String [] list=getResources().getStringArray(R.array.listofalphabate);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.index_icon_view,R.id.customadaptarTextview,list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String a = (String) gridView.getItemAtPosition(position);

                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
                List<Song> songsa = new ArrayList<>();
                songsa = databaseQueryClass.getSongByletter(a);

                if (!songsa.isEmpty()) {
                    String[] title = new String[songsa.size()];
                    String[] songbnumber = new String[songsa.size()];
                    int[] songnumber = new int[songsa.size()];
                    for (int i = 0; i < songsa.size(); i++) {
                        songnumber[i]=songsa.get(i).getNumber();
                        songbnumber[i] = songsa.get(i).getBnumber();
                        title[i] = songsa.get(i).getBtitle();

                    }

                    //numberview.setText(text.toString());
                    Intent intent = new Intent(getActivity(), ListOfsong.class);
                    intent.putExtra("actionbarname",a);
                    intent.putExtra("songnumber", songnumber);
                    intent.putExtra("songbnumber", songbnumber);
                    intent.putExtra("title", title);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.not_available), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
