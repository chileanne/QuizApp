package com.asl.crud.quizapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.asl.crud.quizapp.Categories.BasicActivity;
import com.asl.crud.quizapp.Categories.CadualActivity;
import com.asl.crud.quizapp.Categories.LegendActivity;
import com.asl.crud.quizapp.Categories.MasterActivity;
import com.asl.crud.quizapp.Scoreboard.BasicScoreboardActivity;

public class ScoreboardCategoriesActivity extends AppCompatActivity {
    private CardView mbasics,mcadual,mmaster,mlegend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard_categories);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mbasics=findViewById(R.id.bassicards);
        mcadual=findViewById(R.id.cadualcards);
        mmaster=findViewById(R.id.mastercards);
        mlegend=findViewById(R.id.legendcards);


        mbasics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BasicScoreboardActivity.class);
                intent.putExtra("name","BasicScoreBoard");
                startActivity(intent);
            }
        });

        mcadual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BasicScoreboardActivity.class);
                intent.putExtra("name","CadualScoreBoard");
                startActivity(intent);
            }
        });

        mmaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BasicScoreboardActivity.class);
                intent.putExtra("name","MasterScoreBoard");
                startActivity(intent);
            }
        });

        mlegend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BasicScoreboardActivity.class);
                intent.putExtra("name","LegendScoreBoard");
                startActivity(intent);
            }
        });

    }
}
