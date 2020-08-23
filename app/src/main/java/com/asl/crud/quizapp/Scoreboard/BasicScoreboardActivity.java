package com.asl.crud.quizapp.Scoreboard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.Basicviewmodel;
import com.asl.crud.quizapp.Basicsoredbs.basicaapter;
import com.asl.crud.quizapp.Basicsoredbs.basicviewmodel;
import com.asl.crud.quizapp.Basicsoredbs.bassicenitty;
import com.asl.crud.quizapp.Basicsoredbs.bbmodel;
import com.asl.crud.quizapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class BasicScoreboardActivity extends AppCompatActivity {
    private RecyclerView mrecy;
    private DatabaseReference mscoreboard;
    private ProgressBar mp;
    private basicviewmodel mdailymodel;
    private basicaapter mpp;
   private LiveData<List<bassicenitty>> Alldata;
   private String checck=null;
   private String cool;
  // private LiveData<List<bbmodel>> Alldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_scoreboard);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        checck=getIntent().getStringExtra("name");
        if(checck!=null){
            cool=checck;
            Toast.makeText(getApplicationContext(), cool, Toast.LENGTH_SHORT).show();
        }

        mp=findViewById(R.id.rotatebasicanswer);
        mrecy=findViewById(R.id.basicrecyanswer);
        mrecy.setLayoutManager(new LinearLayoutManager(this));

        mrecy.setVisibility(View.GONE);
        mp.setVisibility(View.VISIBLE);

        mdailymodel = ViewModelProviders.of(this).get(basicviewmodel.class);

        mscoreboard = FirebaseDatabase.getInstance().getReference().child(cool);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
           // Toast.makeText(getApplicationContext(), "yo", Toast.LENGTH_SHORT).show();

            mdailymodel.deletes();

            mscoreboard.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String muserid = dataSnapshot.child("userid").getValue().toString();
                        String musername = dataSnapshot.child("username").getValue().toString();
                        String mscore = dataSnapshot.child("Score").getValue().toString();




                        //store this content in the room database
                        bassicenitty m = new   bassicenitty(0, muserid, musername, mscore);
                        mdailymodel.inserts(m);

                        Alldata = mdailymodel.getAlldata();
                        tunde();


                        mp.setVisibility(View.GONE);
                        mrecy.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        }

    }



    private void tunde() {
        Alldata.observe(this, new Observer<List<bassicenitty>>() {
            @Override
            public void onChanged(@Nullable List<bassicenitty> bassicenitties) {
                mpp=new basicaapter(getApplicationContext(),bassicenitties);
                mrecy.setAdapter(mpp);
                mpp.notifyDataSetChanged();

            }
        });
    }
}
