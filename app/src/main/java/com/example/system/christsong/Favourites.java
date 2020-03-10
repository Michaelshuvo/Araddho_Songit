package com.example.system.christsong;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import dao.DatabaseQueryClass;
import dao.Song;

public class Favourites extends AppCompatActivity {
 ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        //listView=(ListView)findViewById(R.id.listofsongs);
        SwipeMenuListView listView=(SwipeMenuListView) findViewById(R.id.listofsongs);
        final DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(this);
        List<Song> songsa = new ArrayList<>();
        songsa = databaseQueryClass.getFav();
        if (!songsa.isEmpty()) {
            final String[] title = new String[songsa.size()];
            final String[] songbnumber = new String[songsa.size()];
            final int[] songnumber = new int[songsa.size()];
            for (int i = 0; i < songsa.size(); i++) {
                songnumber[i]=songsa.get(i).getNumber();
                songbnumber[i] = songsa.get(i).getBnumber();
                title[i] = songsa.get(i).getBtitle();

            }

            final Baseadaptarcustom baseadaptarcustom =new Baseadaptarcustom(Favourites.this,title,songbnumber);
         //   ArrayAdapter<String> adapter=new ArrayAdapter<>(Favourites.this,android.R.layout.simple_list_item_1,android.R.id.text1,title);


            listView.setAdapter(baseadaptarcustom);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background

                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_cancel_black_24dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView.setMenuCreator(creator);

            listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    switch (index) {
                        case 0:
                            //Toast.makeText(Favourites.this, title[position], Toast.LENGTH_SHORT).show();

                            DatabaseQueryClass databaseQueryClass1=new DatabaseQueryClass(getApplicationContext());
                            databaseQueryClass.set_fav(songnumber[position],0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);

                            break;

                    }
                    // false : close the menu; true : not close the menu
                    return false;
                }
            });








            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int songonumber=songnumber[i];
                    String songnymber=songbnumber[i];
                    Intent intent=new Intent(getBaseContext(),LyricPage.class);
                    intent.putExtra("songnumber",songonumber);
                    intent.putExtra("actionbartitle",title[i]);
                    intent.putExtra("songbnumber",songnymber);
                    startActivity(intent);
                }
            });
    }
        else {
            Toast.makeText(Favourites.this, getResources().getString(R.string.not_available), Toast.LENGTH_SHORT).show();
        }
}
        }
