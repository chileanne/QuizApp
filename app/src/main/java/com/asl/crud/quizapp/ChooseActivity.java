package com.asl.crud.quizapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.asl.crud.quizapp.Basicdbs.Basicadapter;
import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.Basicsmodel;
import com.asl.crud.quizapp.Basicdbs.Basicviewmodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ChooseActivity extends AppCompatActivity {
private String mbasic,mcadual,mmaster,mlegend;
private DatabaseReference mdatabaseref;
private String mcheck=null;
    private List<Basicsmodel> postlist;
    private Basicsmodel mbasicmodel;
    private Basicviewmodel mdailymodel;
    private LiveData<List<Basicentity>> Alldata;
    private Basicadapter mabasicadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        mbasic=getIntent().getStringExtra("BASIC");
        mcadual=getIntent().getStringExtra("CADUAL");
        mmaster=getIntent().getStringExtra("MASTER");
        mlegend=getIntent().getStringExtra("LEGEND");

        mdailymodel = ViewModelProviders.of(this).get(Basicviewmodel.class);

        if(mbasic!=null){
            mcheck=mbasic;

        }else{
            Toast.makeText(getApplicationContext(),"basic is null",Toast.LENGTH_LONG).show();
        }


        //read from databse and populate room databse
        if(mcheck!=null) {
            mdatabaseref = FirebaseDatabase.getInstance().getReference().child(mcheck);
            mdatabaseref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        postlist.add(userSnapshot.getValue(Basicsmodel.class));
                    }

                    //shuffle the list
                    Collections.shuffle(postlist);
                    if(postlist!=null){
                        for (int i = 0; i < postlist.size(); i++) {
                            String mquestion=postlist.get(i).getQuestion();
                            String moptionA=postlist.get(i).getOptionA();
                            String moptionB=postlist.get(i).getOptionB();
                            String moptionC=postlist.get(i).getOptionC();
                            String moptionD=postlist.get(i).getOptionD();
                            String mcorrectanswer=postlist.get(i).getCorrectLetter();
                            String mquestionid=postlist.get(i).getQuestionid();

                            //store this content in the room database
                          //  Basicentity m= new Basicentity(0,mquestion,moptionA,moptionB,moptionC,moptionD,mcorrectanswer,mquestionid);
                        //    mdailymodel.inserts(m);

                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //lets make a call to the view model to retrieve all question

            Alldata = mdailymodel.getffivedata();
            tunde();

        }

    }

    private void tunde() {
        Alldata.observe(this, new Observer<List<Basicentity>>() {
            @Override
            public void onChanged(@Nullable List<Basicentity> basicentities) {
               // mabasicadapter= new Basicadapter();


            }
        });
    }

}
