package com.asl.crud.quizapp.Basicdbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.asl.crud.quizapp.R;

public class RadActivity extends AppCompatActivity {
    private TextView mtext;
    private String hh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rad);
        hh=getIntent().getStringExtra("title");

        mtext=findViewById(R.id.ddd);
        mtext.setText(hh);
    }
}
