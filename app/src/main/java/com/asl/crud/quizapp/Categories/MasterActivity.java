package com.asl.crud.quizapp.Categories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.asl.crud.quizapp.Basicdbs.Basicadapter;
import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.Basicsmodel;
import com.asl.crud.quizapp.Basicdbs.Basicviewmodel;
import com.asl.crud.quizapp.CategoryActivity;
import com.asl.crud.quizapp.Master.Masteradapter;
import com.asl.crud.quizapp.Master.Masterentity;
import com.asl.crud.quizapp.Master.Masterviewmodel;
import com.asl.crud.quizapp.Master.showmasteradapter;
import com.asl.crud.quizapp.R;
import com.asl.crud.quizapp.Show_answers.showadapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterActivity extends AppCompatActivity {
    private String mbasic,mcadual,mmaster,mlegend;
    private DatabaseReference mdatabaseref,mscoreboard;
    private String mcheck="MASTER";
    private List<Basicsmodel> postlist;
    private Basicsmodel mbasicmodel;
    private Masterviewmodel mdailymodel;
    private LiveData<List<Masterentity>> Alldata;
    public Masteradapter mabasicadapter;
    private ProgressBar mp;
    private RecyclerView mrecy,mrecys;
    private int total=0 ;
    private Button mbtn;
    private String myid,memail;
    private FirebaseUser user;
    private showmasteradapter mshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        user= FirebaseAuth.getInstance().getCurrentUser();
        myid=user.getUid();
        memail=user.getEmail();

        postlist=new ArrayList<>();


        mp=findViewById(R.id.rotatemaster);
        mrecy=findViewById(R.id.masterrecy);
        mbtn=findViewById(R.id.ssdmaster);
        mabasicadapter= new Masteradapter();
        total=mabasicadapter.score();
        mrecy.setLayoutManager(new LinearLayoutManager(this));

        //recyclerview for showing all answers
        mrecys=findViewById(R.id.shomasterrecy);
        mrecys.setLayoutManager(new LinearLayoutManager(this));

        mrecy.setVisibility(View.GONE);
        mrecys.setVisibility(View.GONE);
        mp.setVisibility(View.VISIBLE);
        mbtn.setVisibility(View.GONE);

        mdailymodel = ViewModelProviders.of(this).get(Masterviewmodel.class);

        mdatabaseref = FirebaseDatabase.getInstance().getReference().child(mcheck);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //Toast.makeText(getApplicationContext(), "yo", Toast.LENGTH_SHORT).show();

            mdailymodel.deletes();
            if (mcheck != null) {

                mdatabaseref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String mquestion = dataSnapshot.child("question").getValue().toString();
                        String moptionA = dataSnapshot.child("optionA").getValue().toString();
                        String moptionB = dataSnapshot.child("optionB").getValue().toString();
                        String moptionC = dataSnapshot.child("optionC").getValue().toString();
                        String moptionD = dataSnapshot.child("optionD").getValue().toString();
                        String mshort = dataSnapshot.child("descrip").getValue().toString();
                        String mcorrectanswer = dataSnapshot.child("correctLetter").getValue().toString();
                        String mquestionid = dataSnapshot.child("questionid").getValue().toString();



                        //store this content in the room database
                        Masterentity m = new Masterentity(0, mquestion, moptionA, moptionB, moptionC, moptionD, mcorrectanswer, mquestionid,mshort);
                        mdailymodel.inserts(m);

                        Alldata = mdailymodel.getffivedata();
                        tunde();


                        mp.setVisibility(View.GONE);
                        mrecy.setVisibility(View.VISIBLE);
                       mbtn.setVisibility(View.VISIBLE);
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

        //submit button
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total=mabasicadapter.score();
                final String m = Integer.toString(total);
                if(m!=null) {
                    // String m = Integer.toString(total);
//                    Toast.makeText(getApplicationContext(), m, Toast.LENGTH_LONG).show();

                    mscoreboard = FirebaseDatabase.getInstance().getReference().child("MasterScoreBoard").child(myid);
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        Map update = new HashMap();
                        update.put("userid", myid);
                        update.put("username", memail);
                        update.put("Score", m);

                        mscoreboard.setValue(update).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
  //                                  Toast.makeText(getApplicationContext(),"succy", Toast.LENGTH_LONG).show();
                                    //lets hide the main   question recycler view and display
                                    mrecy.setVisibility(View.GONE);
                                    mrecys.setVisibility(View.VISIBLE);

                                    if(total>3){
                                        yoextrat(m);

                                    }else if(total<3){
                                        bengrim(m);

                                    }



                                }else{
                                    Toast.makeText(getApplicationContext(),"Network error please try again", Toast.LENGTH_LONG).show();
                                    mrecy.setVisibility(View.VISIBLE);
                                    mrecys.setVisibility(View.GONE);
                                    mp.setVisibility(View.GONE);
                                    mbtn.setVisibility(View.VISIBLE);

                                }

                            }
                        });

                    }
                }
            }
        });
    }

    private void tunde() {
        Alldata.observe(this, new Observer<List<Masterentity>>() {
            @Override
            public void onChanged(@Nullable List<Masterentity> masterentities) {
                Collections.shuffle(masterentities);
                mabasicadapter= new Masteradapter(getApplicationContext(),masterentities);
                mshow=new showmasteradapter(getApplicationContext(),masterentities);
                mrecys.setAdapter(mshow);
                mrecy.setAdapter(mabasicadapter);
                mabasicadapter.notifyDataSetChanged();
                mshow.notifyDataSetChanged();

                            }
        });

    }


    private void bengrim(String m) {
        String dd="Your Score is" +" " + m;
        String tt=dd +"\n";
        AlertDialog.Builder alertd=new AlertDialog.Builder(this);
        alertd.setMessage(tt);
        alertd.setCancelable(false);
        alertd.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //go to cadual activity
                finish();
                Intent intent = new Intent(getBaseContext(),MasterActivity.class);
                startActivity(intent);
            }
        });

        alertd.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //remain in the activity

            }
        });

        AlertDialog aad=alertd.create();
        aad.show();
    }

    private void yoextrat(String m) {
        String dd="Your Score is" + " " + m;
        String gg="i think you ready to try Legend";
        String tt=dd +"\n" + gg;
        AlertDialog.Builder alertd=new AlertDialog.Builder(this);
        alertd.setMessage(tt);
        alertd.setCancelable(false);
        alertd.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //go to cadual activity
                finish();
                Intent intent = new Intent(getBaseContext(),LegendActivity.class);
                startActivity(intent);
            }
        });

        alertd.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //remain in the activity

            }
        });

        AlertDialog aad=alertd.create();
        aad.show();
    }


}
