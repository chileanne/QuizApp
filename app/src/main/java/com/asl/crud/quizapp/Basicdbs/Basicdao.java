package com.asl.crud.quizapp.Basicdbs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface Basicdao {
    @Insert
    void inserts(Basicentity madvententity);

    @Query("DELETE from Basic")
    void deletealls();

    @Query("SELECT * from Basic")
    LiveData<List<Basicentity>> getAlldatas();

    //get 5 questions
    @Query("SELECT * from Basic ORDER BY RANDOM() LIMIT 10")
    LiveData<List<Basicentity>> getfiveAlldatas();
}
