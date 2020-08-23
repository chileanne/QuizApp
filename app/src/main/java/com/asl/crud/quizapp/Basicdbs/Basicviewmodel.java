package com.asl.crud.quizapp.Basicdbs;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

public class Basicviewmodel extends AndroidViewModel {
    private Basicroomdatabase mdatadb;
    private Basicdao medodao;
    public Basicviewmodel(@NonNull Application application) {
        super(application);
        mdatadb= Basicroomdatabase.getDatabase(application);
        medodao=mdatadb.medodao();

    }

    public LiveData<List<Basicentity>> getAlldata(){
        return medodao.getAlldatas();
    }

    public LiveData<List<Basicentity>> getffivedata(){
        return medodao.getfiveAlldatas();
    }



    public void inserts(Basicentity medo){
        new  Basicviewmodel.InsertsAsyncTassks(medodao).execute(medo);
    }

    public void deletes(){
        new  Basicviewmodel.deleteAsynctasks(medodao).execute();
    }

    private class InsertsAsyncTassks extends AsyncTask<Basicentity,Void,Void> {

        Basicdao medodao;

        public InsertsAsyncTassks(Basicdao medodao) {
            this.medodao=medodao;
        }


        @Override
        protected Void doInBackground(Basicentity... basicentities) {
            medodao.inserts(basicentities[0]);
            return null;
        }
    }

    private class deleteAsynctasks extends AsyncTask<Basicentity,Void,Void> {

        Basicdao medodao;
        public deleteAsynctasks(Basicdao medodao) {
            this.medodao=medodao;
        }

        @Override
        protected Void doInBackground(Basicentity... basicentities) {
            medodao.deletealls();
            return null;
        }
    }

}
