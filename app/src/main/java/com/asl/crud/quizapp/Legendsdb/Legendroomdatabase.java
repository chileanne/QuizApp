package com.asl.crud.quizapp.Legendsdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.asl.crud.quizapp.Basicdbs.Basicdao;
import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.Basicroomdatabase;

@Database(entities = Legendentity.class, version = 2)
public abstract class Legendroomdatabase extends RoomDatabase {

    public abstract Legendao medodao();

    private static volatile Legendroomdatabase INSTANCE;

    static Legendroomdatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized ( Legendroomdatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Legendroomdatabase.class, "Legend_database")
                            .fallbackToDestructiveMigration()
                            // .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
