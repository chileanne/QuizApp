package com.asl.crud.quizapp.Basicdbs;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asl.crud.quizapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Basicadapter extends RecyclerView.Adapter<Basicadapter.Myholder> {
    private Context mcontext;
    private List<Basicentity> basicentities;
    private boolean connected=false;
    private String myid;
    private FirebaseUser user;
    private DatabaseReference mansweredquestion;
    private String A=null;
    private String B=null;
    private String C=null;
    private String D=null;
    private int Score=0;
    private int a=0;
    private int b=0;
    private int c=0;
    private int d=0;

    public Basicadapter(Context applicationContext, List<Basicentity> basicentities) {
        this.mcontext=applicationContext;
        this.basicentities=basicentities;
    }

    public Basicadapter() {

    }

    @NonNull
    @Override
    public Basicadapter.Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.basicsinglelayout,viewGroup,false);
        return new Basicadapter.Myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Basicadapter.Myholder myholder, int i) {
        user= FirebaseAuth.getInstance().getCurrentUser();
        myid=user.getUid();
        //lets create a database that holds the answered question
        mansweredquestion= FirebaseDatabase.getInstance().getReference("Questionansweredby_"+myid);

       // Collections.shuffle(basicentities);
        Basicentity m=basicentities.get(i);
        String question=m.getMquestion();
        String mma=m.getMoptionA();
        String mmb=m.getMoptionB();
        String mmc=m.getMoptionC();
        String mmd=m.getMoptionD();
        final String mcorrectanswer=m.getMcorrectanswer();
        final String mquestionid=m.getMquestionid();

        myholder.mquestions.setText(question);
        myholder.ma.setText(mma);
        myholder.mb.setText(mmb);
        myholder.mc.setText(mmc);
        myholder.md.setText(mmd);


        //button A
        myholder.mla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myholder.mla.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.choose));
                myholder.mlc.setBackgroundColor(Color.WHITE);
                myholder.mld.setBackgroundColor(Color.WHITE);
                myholder.mlb.setBackgroundColor(Color.WHITE);



                myholder.mla.setClickable(false);
                myholder.mlb.setClickable(false);
                myholder.mlc.setClickable(false);
                myholder.mld.setClickable(false);

                //lets check if the correction option was selected
                if(mcorrectanswer.equals("A")){
                    A="A";
                    a=1;

                    /*Score=Score+1;
                    String m = Integer.toString(Score);
                    Toast.makeText(mcontext, m, Toast.LENGTH_SHORT).show();
                    a=0;*/
                    Score++;
                  String m = Integer.toString(Score);
                  //Toast.makeText(mcontext, m, Toast.LENGTH_SHORT).show();




                    }
                

            }
        });


        //buttonB
        myholder.mlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                myholder.mla.setBackgroundColor(Color.WHITE);
                myholder.mlc.setBackgroundColor(Color.WHITE);
                myholder.mld.setBackgroundColor(Color.WHITE);
                myholder.mlb.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.choose));

                myholder.mlb.setClickable(false);
                myholder.mla.setClickable(false);
                myholder.mlc.setClickable(false);
                myholder.mld.setClickable(false);

                //lets check if the correction option was selected
                if (mcorrectanswer.equals("B")) {

                    b=1;
                    Score = Score + 1;
                   String m = Integer.toString(Score);
                  //  Toast.makeText(mcontext, m, Toast.LENGTH_SHORT).show();
                    B = "B";


                }

            }
        });

        //button c
        myholder.mlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myholder.mla.setBackgroundColor(Color.WHITE);
                myholder.mlc.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.choose));
                myholder.mld.setBackgroundColor(Color.WHITE);
                myholder.mlb.setBackgroundColor(Color.WHITE);





                //lets check if the correction option was selected
                if(mcorrectanswer.equals("C")){
                            c=1;
                    myholder.mlc.setClickable(false);
                    myholder.mlb.setClickable(false);
                    myholder.mla.setClickable(false);
                    myholder.mld.setClickable(false);

                    Score++;
                        String m = Integer.toString(Score);
                       // Toast.makeText(mcontext, m, Toast.LENGTH_SHORT).show();
                        C = "C";




                    }


            }
        });

        //button D
        myholder.mld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myholder.mla.setBackgroundColor(Color.WHITE);
                myholder.mlc.setBackgroundColor(Color.WHITE);
                myholder.mld.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.choose));
                myholder.mlb.setBackgroundColor(Color.WHITE);


                myholder.mld.setClickable(false);
                myholder.mlb.setClickable(false);
                myholder.mlc.setClickable(false);
                myholder.mla.setClickable(false);

                //lets check if the correction option was selected
                if(mcorrectanswer.equals("D")) {
                    d=1;
                        Score++;
                    String m = Integer.toString(Score);
                   // Toast.makeText(mcontext, m, Toast.LENGTH_SHORT).show();
                    D = "D";


                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return basicentities.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private TextView mquestions,ma,mb,mc,md;
        private LinearLayout mla,mlb,mlc,mld;
        public Myholder(@NonNull View v) {
            super(v);
            mquestions=v.findViewById(R.id.basicquestion);
            ma=v.findViewById(R.id.bbasicanswerA);
            mb=v.findViewById(R.id.bbasicanswerB);
            mc=v.findViewById(R.id.bbasicanswerC);
            md=v.findViewById(R.id.bbasicanswerD);

            //linearlayout for each aswers
            mla=v.findViewById(R.id.basicanswerA);
            mlb=v.findViewById(R.id.basicanswerB);
            mlc=v.findViewById(R.id.basicanswerC);
            mld=v.findViewById(R.id.basicanswerD);
        }
    }

    public int score(){
        int Sscore=Score;
        return Sscore;
    }
}
