package com.example.system.christsong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dao.DatabaseQueryClass;
import dao.Song;

public class LyricsPage extends AppCompatActivity {
  EditText number,title,lyrics;
  Button submit ,getdata,getdatabynumber;
  TextView numberview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_page);
//        number=findViewById(R.id.number);
//        title=findViewById(R.id.title);
//        lyrics=findViewById(R.id.lyrics);
//        getdata=findViewById(R.id.getdata);
//        numberview=findViewById(R.id.numberview);
//        getdatabynumber=findViewById(R.id.getdatabynumber);
//        submit=findViewById(R.id.submitlyrics);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Song song = new Song();
//                song.setNumber(number.getText().toString());
//                song.setTitle(title.getText().toString());
//                song.setLyrics(lyrics.getText().toString());
//                DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(LyricsPage.this);
//                long id = databaseQueryClass.insertStudent(song);
//                if (id == (-1)) {
//                    Toast.makeText(LyricsPage.this, "Faild", Toast.LENGTH_SHORT).show();
//
//                } else
//                    Toast.makeText(LyricsPage.this, "sucssec", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//        getdata.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                StringBuffer text=new StringBuffer();
//                DatabaseQueryClass databaseQueryClass=new DatabaseQueryClass(LyricsPage.this);
//                List<Song> songsa=new ArrayList<>();
//                       //songsa=databaseQueryClass.getAllStudent();
//                       for(int i=0;i<songsa.size();i++){
//                           text.append(songsa.get(i).getNumber()+"\n");
//                           text.append(songsa.get(i).getTitle()+"\n");
//                           text.append(songsa.get(i).getLyrics()+"\n");
//                }
//                numberview.setText(text.toString());
//            }
//        });
//        getdatabynumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //StringBuffer text=new StringBuffer();
////                DatabaseQueryClass databaseQueryClass=new DatabaseQueryClass(LyricsPage.this);
////                Song songsa=new Song();
////                songsa=databaseQueryClass.getSongByNum(number.getText().toString());
////
////                    text.append(songsa.getNumber()+"\n");
////                    text.append(songsa.getTitle()+"\n");
////                    text.append(songsa.getLyrics()+"\n");
////
////                numberview.setText(text.toString());
//                StringBuffer text=new StringBuffer();
//                DatabaseQueryClass databaseQueryClass=new DatabaseQueryClass(LyricsPage.this);
//                List<Song> songsa=new ArrayList<>();
//                songsa=databaseQueryClass.getSongByletter(title.getText().toString());
//                for(int i=0;i<songsa.size();i++){
//                    text.append(songsa.get(i).getNumber()+"\n");
//                    text.append(songsa.get(i).getTitle()+"\n");
//                    //text.append(songsa.get(i).getLyrics()+"\n");
//                }
//                numberview.setText(text.toString());
//            }
//        });

    }
}
