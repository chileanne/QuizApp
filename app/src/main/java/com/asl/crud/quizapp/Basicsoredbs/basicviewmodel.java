package com.asl.crud.quizapp.Basicsoredbs;

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

public class basicviewmodel extends AndroidViewModel {
    private basicroomdatabse mdatadb;
    private basicdao medodao;

    public basicviewmodel(@NonNull Application application) {
        super(application);
        mdatadb= basicroomdatabse.getDatabase(application);
        medodao=mdatadb.medodao();
    }


    public LiveData<List<bassicenitty>> getAlldata(){
        return medodao.getAllmaxdatas();
    }

   /* public LiveData<List<bbmodel>> getAlldata() {
        return medodao.getAllmaxdatas();
    }*/

    public void inserts(bassicenitty medo){
        new  basicviewmodel.InsertsAsyncTassks(medodao).execute(medo);
    }

    public void deletes(){
        new  basicviewmodel.deleteAsynctasks(medodao).execute();
    }

    private class InsertsAsyncTassks extends AsyncTask<bassicenitty,Void,Void> {
       basicdao medodao;
        public InsertsAsyncTassks(basicdao medodao) {
            this.medodao=medodao;
        }

        @Override
        protected Void doInBackground(bassicenitty... bassicenitties) {
            medodao.inserts(bassicenitties[0]);
            return null;
        }
    }

    private class deleteAsynctasks extends AsyncTask<bassicenitty,Void,Void> {
        basicdao medodao;

        public deleteAsynctasks(basicdao medodao) {
            this.medodao=medodao;
        }

        @Override
        protected Void doInBackground(bassicenitty... bassicenitties) {
            medodao.deletealls();
            return null;
        }
    }

    }
