package com.asl.crud.quizapp.Cadualdbs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.asl.crud.quizapp.Basicdbs.Basicentity;

import java.util.List;

@Dao
public interface cadualdao {
    @Insert
    void inserts(cadualentity madvententity);

    @Query("DELETE from Cadual")
    void deletealls();

    @Query("SELECT * from Cadual")
    LiveData<List<cadualentity>> getAlldatas();

    @Query("SELECT * from Cadual ORDER BY RANDOM() LIMIT 10")
    LiveData<List<cadualentity>> getfiveAlldatas();
}
