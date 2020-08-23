package com.asl.crud.quizapp.Legendsdb;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Cadualdbs.cadualentity;

import java.util.List;
@Dao
public interface Legendao {
    @Insert
    void inserts(Legendentity madvententity);

    @Query("DELETE from Legend")
    void deletealls();

    //get 5 questions
    @Query("SELECT * from Legend ORDER BY RANDOM() LIMIT 10")
    LiveData<List<Legendentity>> getfiveAlldatas();
}
