package com.example.system.christsong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListofSubjectiveSong extends AppCompatActivity {
  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_subjective_song);
        textView=(TextView)findViewById(R.id.subjecttext);

        textView.setText(getIntent().getStringExtra("subject"));
    }
}
