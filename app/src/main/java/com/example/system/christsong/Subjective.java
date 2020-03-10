package com.example.system.christsong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import dao.DatabaseQueryClass;
import dao.Song;


public class Subjective extends Fragment{
  View view;
  TextView tv;
  Context context;
  ListView listView;

  public Subjective(){

  }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.subjective,container,false);
        final String [] subject=getResources().getStringArray(R.array.subjects);
        String [] subjectnumber=getResources().getStringArray(R.array.subjects_number);
        listView=view.findViewById(R.id.subjectivelist);
       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getBaseContext(),R.layout.subjectivelayout,R.id.subject,subject);
        Customadaptar adapter=new Customadaptar(getActivity(),subject,subjectnumber);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String value=subject[position];
                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(getContext());
                List<Song> songsa = new ArrayList<>();
                songsa = databaseQueryClass.getSonglistBysubject(position);

                if (!songsa.isEmpty()) {
                    String[] btitle = new String[songsa.size()];
                    String[] songbnumber = new String[songsa.size()];
                    int[] songnumber = new int[songsa.size()];
                    //ArrayList<String> numberr=new ArrayList<>();
                    for (int i = 0; i < songsa.size(); i++) {
                        songbnumber[i] = songsa.get(i).getBnumber();
                        songnumber[i] = songsa.get(i).getNumber();
                        btitle[i] = songsa.get(i).getBtitle();
                        //numberr.add(songsa.get(i).getBtitle());
                    }
//                    Locale bangla=new Locale("bn");
//                    Collator banglacontrol=Collator.getInstance(bangla);
//                    Collections.sort(numberr);
//                    btitle=numberr.toArray(new String[numberr.size()]);
                    Intent intent = new Intent(getActivity(), ListOfsong.class);
                    intent.putExtra("actionbarname",value);
                    intent.putExtra("songnumber", songnumber);
                    intent.putExtra("songbnumber", songbnumber);
                    intent.putExtra("title", btitle);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.not_available), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
class  Customadaptar extends ArrayAdapter<String> {
private final Context context;
private final String[] items;
private final String[] descriptions;


public Customadaptar(Context context,String[] items,String[] description) {
        super(context, R.layout.subjectivelayout,items);
        this.context=context;
        this.items=items;
        this.descriptions=description;


        }
@Override
public View getView(int position, View view, ViewGroup parent){
        LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview= layoutInflater.inflate(R.layout.subjectivelayout,parent,false);
        TextView title=(TextView) rowview.findViewById(R.id.subject);
        TextView description=(TextView)rowview.findViewById(R.id.subjectnumber);
        title.setText(items[position]);
        description.setText(descriptions[position]);
        return rowview;



        }
        }

