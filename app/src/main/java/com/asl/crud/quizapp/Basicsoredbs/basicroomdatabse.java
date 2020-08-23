package com.asl.crud.quizapp.Basicsoredbs;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import com.asl.crud.quizapp.Basicdbs.Basicdao;
import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.Basicroomdatabase;

@Database(entities = bassicenitty.class, version = 1)
public abstract class basicroomdatabse extends RoomDatabase {
    public abstract basicdao medodao();

    private static volatile basicroomdatabse INSTANCE;

    static basicroomdatabse getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized ( basicroomdatabse.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            basicroomdatabse.class, "BasicScore_database")
                            .fallbackToDestructiveMigration()
                            // .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
