package com.example.system.christsong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.CollationKey;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collector;

public class ListOfsong extends AppCompatActivity {
  ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ofsong);
        getSupportActionBar().setTitle(getIntent().getStringExtra("actionbarname"));
        listView=(ListView) findViewById(R.id.listofsongs);
        final String [] title=getIntent().getStringArrayExtra("title");
        final String [] songbnumber=getIntent().getStringArrayExtra("songbnumber");
        final int [] songnumber=getIntent().getIntArrayExtra("songnumber");
        //Toast.makeText(ListOfsong.this, songnumber[1],Toast.LENGTH_SHORT).show();
        Baseadaptarcustom baseadaptarcustom =new Baseadaptarcustom(ListOfsong.this,title,songbnumber);
        listView.setAdapter(baseadaptarcustom);
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
}
