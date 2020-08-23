package com.asl.crud.quizapp.Basicsoredbs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asl.crud.quizapp.Basicdbs.Basicadapter;
import com.asl.crud.quizapp.R;

import java.util.List;

public class basicaapter extends RecyclerView.Adapter<basicaapter.Myholder>{
    private Context mcontext;
    private List<bassicenitty> bassicenitties;
    private List<bbmodel> bbmodels;

    /*public basicaapter(Context applicationContext, List<bbmodel> bbmodels) {
        this.mcontext=applicationContext;
        this.bbmodels=bbmodels;
    }*/
   public basicaapter(Context applicationContext, List<bassicenitty> bassicenitties) {
        this.mcontext=applicationContext;
        this.bassicenitties=bassicenitties;
    }

    @NonNull
    @Override
    public basicaapter.Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scoresinglelayout,viewGroup,false);
        return new basicaapter.Myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull basicaapter.Myholder myholder, int i) {
        bassicenitty g=bassicenitties.get(i);
        String name=g.getMusername().toString();
        String score=g.getMscore().toString();
        int ss=Integer.parseInt(score);

       /*bbmodel g=bbmodels.get(i);
        String name=g.getMusername().toString();
        String score=g.getMaxscore().toString();*/


           myholder.mname.setText(name);
           myholder.ma.setText(score);



    }

    @Override
    public int getItemCount() {
        return bassicenitties.size() ;
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private TextView mname,ma,mb,mc,md;
        public Myholder(@NonNull View v) {
            super(v);
            mname=v.findViewById(R.id.name);
            ma=v.findViewById(R.id.scoore);

        }
    }
}

