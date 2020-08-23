package com.asl.crud.quizapp.Basicdbs;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Basicentity.class, version = 2)
public abstract class Basicroomdatabase extends RoomDatabase {
    public abstract Basicdao medodao();

    private static volatile Basicroomdatabase INSTANCE;

    static Basicroomdatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized ( Basicroomdatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Basicroomdatabase.class, "Basic_database")
                            .fallbackToDestructiveMigration()
                            // .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
