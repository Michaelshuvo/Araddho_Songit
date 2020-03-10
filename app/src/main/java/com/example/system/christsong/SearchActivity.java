package com.example.system.christsong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
  GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String [] list=getIntent().getStringArrayExtra("list");
        gridView=findViewById(R.id.gridview);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchActivity.this,R.layout.index_icon_view,R.id.customadaptarTextview,list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String a=(String) gridView.getItemAtPosition(i);
                Toast.makeText(SearchActivity.this,"you have been clicked:"+a,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SearchActivity.this,ListOfsong.class);
            }
        });

    }
}
