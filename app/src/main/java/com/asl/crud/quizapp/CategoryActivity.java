package com.asl.crud.quizapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.asl.crud.quizapp.Categories.BasicActivity;
import com.asl.crud.quizapp.Categories.CadualActivity;
import com.asl.crud.quizapp.Categories.LegendActivity;
import com.asl.crud.quizapp.Categories.MasterActivity;
import com.asl.crud.quizapp.Scoreboard.BasicScoreboardActivity;
import com.google.firebase.auth.FirebaseAuth;

import static com.asl.crud.quizapp.R.menu.menu;

public class CategoryActivity extends AppCompatActivity {
    private CardView mbasics,mcadual,mmaster,mlegend;
    private TextView msc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mbasics=findViewById(R.id.bassicard);
        mcadual=findViewById(R.id.cadualcard);
        mmaster=findViewById(R.id.mastercard);
        mlegend=findViewById(R.id.legendcard);
        msc=findViewById(R.id.scoorebbb);

        msc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ScoreboardCategoriesActivity.class);
                startActivity(intent);
            }
        });

        mbasics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BasicActivity.class);
                startActivity(intent);
            }
        });

        mcadual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CadualActivity.class);
                startActivity(intent);
            }
        });

        mmaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MasterActivity.class);
                startActivity(intent);
            }
        });

        mlegend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LegendActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.userssignout) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
