package com.asl.crud.quizapp.Cadualdbs;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.asl.crud.quizapp.Basicdbs.Basicdao;
import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.Basicroomdatabase;

@Database(entities = cadualentity.class, version = 2)
public abstract class cadualroomdatabase extends RoomDatabase {
    public abstract cadualdao medodao();

    private static volatile cadualroomdatabase INSTANCE;

    static cadualroomdatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized ( cadualroomdatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            cadualroomdatabase.class, "Cadual_database")
                            .fallbackToDestructiveMigration()
                            // .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
