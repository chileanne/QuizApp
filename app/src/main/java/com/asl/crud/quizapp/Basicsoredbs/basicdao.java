package com.asl.crud.quizapp.Basicsoredbs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.asl.crud.quizapp.Basicdbs.Basicentity;

import java.util.List;

@Dao
public interface basicdao {
    @Insert
    void inserts(bassicenitty madvententity);

    @Query("DELETE from BasicScore")
    void deletealls();

    @Query("SELECT * from BasicScore  LIMIT 5 ")
    LiveData<List<bassicenitty>> getAllmaxdatas();

   /* @Query("SELECT  MAX(score) as maxscore from BasicScore  LIMIT 5 ")
    LiveData<List<bbmodel>> getAllmaxdatas();*/

    //get 5 questions
    @Query("SELECT * from BasicScore ORDER BY RANDOM() LIMIT 5")
    LiveData<List<bassicenitty>> getfiveAlldatas();
}
