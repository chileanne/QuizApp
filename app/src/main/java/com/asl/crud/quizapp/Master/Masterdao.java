package com.asl.crud.quizapp.Master;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.asl.crud.quizapp.Basicdbs.Basicentity;

import java.util.List;

@Dao
public interface Masterdao {
    @Insert
    void inserts(Masterentity madvententity);

    @Query("DELETE from Master")
    void deletealls();



    //get 5 questions
    @Query("SELECT * from Master ORDER BY RANDOM() LIMIT 10")
    LiveData<List<Masterentity>> getfiveAlldatas();
}
