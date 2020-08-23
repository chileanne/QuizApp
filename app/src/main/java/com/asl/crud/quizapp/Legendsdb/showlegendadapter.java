package com.asl.crud.quizapp.Legendsdb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asl.crud.quizapp.Basicdbs.Basicadapter;
import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.RadActivity;
import com.asl.crud.quizapp.R;
import com.asl.crud.quizapp.Show_answers.showadapter;

import java.util.List;

public class showlegendadapter extends RecyclerView.Adapter<showlegendadapter.Myholder> {
    private  Context mcontext;
    private List<Legendentity> legendentities;
    public showlegendadapter(Context applicationContext, List<Legendentity> legendentities) {
        this.mcontext=applicationContext;
        this.legendentities=legendentities;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.basicsinglelayout,viewGroup,false);
        return new showlegendadapter.Myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder myholder, int i) {
        Legendentity m=legendentities.get(i);
        String question=m.getMquestion();
        String mma=m.getMoptionA();
        String mmb=m.getMoptionB();
        String mmc=m.getMoptionC();
        String mmd=m.getMoptionD();
        final String mcorrectanswer=m.getMcorrectanswer();
        final String mquestionid=m.getMquestionid();
        final String mm=m.getMshort();

        myholder.mquestions.setText(question);
        myholder.ma.setText(mma);
        myholder.mb.setText(mmb);
        myholder.mc.setText(mmc);
        myholder.md.setText(mmd);

        if(mcorrectanswer.equals("A")){
            myholder.mla.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.choosess));

        }else if(mcorrectanswer.equals("B")){
            myholder.mlb.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.choosess));
        }else if(mcorrectanswer.equals("C")){
            myholder.mlc.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.choosess));
        }else if(mcorrectanswer.equals("D")){
            myholder.mld.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.choosess));
        }


        //click to show dialog box
        myholder.mla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mcorrectanswer.equals("A")) {


                    Intent intent = new Intent(mcontext, RadActivity.class);
                    intent.putExtra("title", mm);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }
            }
        });

        myholder.mlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mcorrectanswer.equals("B")){
                    Intent intent = new Intent(mcontext, RadActivity.class);
                    intent.putExtra("title",mm);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }
            }
        });


        myholder.mlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mcorrectanswer.equals("C")){
                    Intent intent = new Intent(mcontext, RadActivity.class);
                    intent.putExtra("title",mm);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }
            }
        });

        myholder.mld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mcorrectanswer.equals("D")){
                    Intent intent = new Intent(mcontext, RadActivity.class);
                    intent.putExtra("title",mm);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return legendentities.size();
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
}
