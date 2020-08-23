package com.asl.crud.quizapp.Master;

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

public class Masterviewmodel extends AndroidViewModel {
    private Masterroomdatabse mdatadb;
    private Masterdao medodao;

    public Masterviewmodel(@NonNull Application application) {
        super(application);
        mdatadb= Masterroomdatabse.getDatabase(application);
        medodao=mdatadb.medodao();
    }

    public LiveData<List<Masterentity>> getffivedata(){
        return medodao.getfiveAlldatas();
    }



    public void inserts(Masterentity medo){
        new  Masterviewmodel.InsertsAsyncTassks(medodao).execute(medo);
    }

    public void deletes(){
        new  Masterviewmodel.deleteAsynctasks(medodao).execute();
    }


    private class InsertsAsyncTassks extends AsyncTask<Masterentity,Void,Void> {
        Masterdao medodao;
        public InsertsAsyncTassks(Masterdao medodao) {
            this.medodao=medodao;
        }

        @Override
        protected Void doInBackground(Masterentity... masterentities) {
            medodao.inserts(masterentities[0]);
            return null;
        }
    }

    private class deleteAsynctasks extends AsyncTask<Masterentity,Void,Void>{
        Masterdao medodao;
        public deleteAsynctasks(Masterdao medodao) {
            this.medodao=medodao;
        }

        @Override
        protected Void doInBackground(Masterentity... masterentities) {
            medodao.deletealls();
            return null;
        }
    }
}
