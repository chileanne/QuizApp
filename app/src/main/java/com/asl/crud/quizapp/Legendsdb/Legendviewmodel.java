package com.asl.crud.quizapp.Legendsdb;

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

public class Legendviewmodel extends AndroidViewModel {
    private Legendroomdatabase mdatadb;
    private Legendao medodao;

    public Legendviewmodel(@NonNull Application application) {
        super(application);
        mdatadb= Legendroomdatabase.getDatabase(application);
        medodao=mdatadb.medodao();
    }

    public LiveData<List<Legendentity>> getffivedata(){
        return medodao.getfiveAlldatas();
    }



    public void inserts(Legendentity medo){
        new  Legendviewmodel.InsertsAsyncTassks(medodao).execute(medo);
    }

    public void deletes(){
        new  Legendviewmodel.deleteAsynctasks(medodao).execute();
    }

    private class InsertsAsyncTassks extends AsyncTask<Legendentity,Void,Void> {
        Legendao medodao;

        public InsertsAsyncTassks(Legendao medodao) {
            this.medodao=medodao;
        }

        @Override
        protected Void doInBackground(Legendentity... legendentities) {
            medodao.inserts(legendentities[0]);
            return null;
        }
    }

    private class deleteAsynctasks extends AsyncTask<Legendentity,Void,Void> {

        Legendao medodao;

        public deleteAsynctasks(Legendao medodao) {
            this.medodao=medodao;
        }

        @Override
        protected Void doInBackground(Legendentity... legendentities) {
            medodao.deletealls();
            return null;
        }
    }
}
