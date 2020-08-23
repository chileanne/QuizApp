package com.asl.crud.quizapp.Cadualdbs;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.asl.crud.quizapp.Basicdbs.Basicdao;
import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.Basicroomdatabase;
import com.asl.crud.quizapp.Basicdbs.Basicviewmodel;

import java.util.List;

public class cadualviewmodel extends AndroidViewModel {
    private cadualroomdatabase mdatadb;
    private cadualdao medodao;

    public cadualviewmodel(@NonNull Application application) {
        super(application);
        mdatadb= cadualroomdatabase.getDatabase(application);
        medodao=mdatadb.medodao();
    }

    public LiveData<List<cadualentity>> getAlldata(){
        return medodao.getAlldatas();
    }

    public LiveData<List<cadualentity>> getffivedata(){
        return medodao.getfiveAlldatas();
    }



    public void inserts(cadualentity medo){
        new  cadualviewmodel.InsertsAsyncTassks(medodao).execute(medo);
    }

    public void deletes(){
        new  cadualviewmodel.deleteAsynctasks(medodao).execute();
    }

    private class InsertsAsyncTassks extends AsyncTask<cadualentity,Void,Void> {
        cadualdao medodao;

        public InsertsAsyncTassks(cadualdao medodao) {
            this.medodao=medodao;
        }

        @Override
        protected Void doInBackground(cadualentity... cadualentities) {
            medodao.inserts(cadualentities[0]);
            return null;
        }
    }

    private class deleteAsynctasks extends AsyncTask<cadualentity,Void,Void> {

        cadualdao medodao;

        public deleteAsynctasks(cadualdao medodao) {
            this.medodao=medodao;
        }

        @Override
        protected Void doInBackground(cadualentity... cadualentities) {
            medodao.deletealls();
            return null;
        }
    }

    }

