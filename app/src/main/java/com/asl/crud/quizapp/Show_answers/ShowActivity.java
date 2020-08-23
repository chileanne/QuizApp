package com.asl.crud.quizapp.Show_answers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.Basicsmodel;
import com.asl.crud.quizapp.R;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    private List<Basicentity> basicentitiess;
    private RecyclerView mrecy;
    private showadapter mshow;
    private String score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        basicentitiess=new ArrayList<>();
        //connetct intent to the arraylist
        Bundle b=getIntent().getExtras();
        basicentitiess= (ArrayList<Basicentity>) b.getSerializable("Key");

        mrecy=findViewById(R.id.showrecy);
        mrecy.setLayoutManager(new LinearLayoutManager(this));
        mshow=new showadapter(this,basicentitiess);
        mrecy.setAdapter(mshow);
        mshow.notifyDataSetChanged();

    }
}
