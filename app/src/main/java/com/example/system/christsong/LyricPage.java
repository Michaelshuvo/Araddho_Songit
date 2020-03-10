package com.example.system.christsong;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import dao.DatabaseQueryClass;
import dao.Song;

public class LyricPage extends AppCompatActivity {
  TextView number,song,writer;
  int numberb;
  //for fav option
  int songnumber;
    Song songsa ;
  //----
  //float x1,x2,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric_page);
        getSupportActionBar().setTitle(getIntent().getStringExtra("actionbartitle"));

       // Slidr.attach(this);
        number=findViewById(R.id.songnumber);
        song=findViewById(R.id.songlyrics);
        writer=findViewById(R.id.writer);
        numberb=getIntent().getIntExtra("songnumber",0);
        DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(LyricPage.this);

       // Toast.makeText(LyricPage.this, numberb,Toast.LENGTH_SHORT).show();
        songsa = databaseQueryClass.getSongByNum(numberb);
        if(songsa!=null) {
            number.setText(songsa.getBnumber());
            song.setText(songsa.getLyrics());
            writer.setText(songsa.getWriter());
            songnumber=songsa.getNumber();//for fav


        } else {
            Toast.makeText(LyricPage.this, "not found",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menur, menu);
        if (songsa.getFav() == 1) {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this,R.drawable.ic_star_red_24dp));
        } else {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp));
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_settings){
            Toast.makeText(LyricPage.this,"setting",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.fav){
            DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(LyricPage.this);
            songsa=databaseQueryClass.getSongByNum(numberb);
            if(songsa.getFav()==1){
                item.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_star_black_24dp));
                databaseQueryClass.set_fav(songnumber,0);
            }
            else{
                item.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_star_red_24dp));
                databaseQueryClass.set_fav(songnumber,1);
            }

        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getActionMasked()){
//            case MotionEvent.ACTION_DOWN:
//                x1=event.getX();
//                y1=event.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2=event.getX();
//                y2=event.getY();
//                if(x1<x2){
//                    Toast.makeText(getApplicationContext(),"yesdid",Toast.LENGTH_LONG).show();
//                    Intent intent=new Intent(getBaseContext(),LyricPage.class);
//                    intent.putExtra("songnumber",numberb+1);
//                    startActivity(intent);
//                    finish();
//                }
//                break;
//
//        }
//        return false;
//    }
}
