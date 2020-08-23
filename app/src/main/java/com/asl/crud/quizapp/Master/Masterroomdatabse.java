package com.asl.crud.quizapp.Master;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.asl.crud.quizapp.Basicdbs.Basicdao;
import com.asl.crud.quizapp.Basicdbs.Basicentity;
import com.asl.crud.quizapp.Basicdbs.Basicroomdatabase;

@Database(entities = Masterentity.class, version = 2)
public abstract class Masterroomdatabse extends RoomDatabase {
    public abstract Masterdao medodao();

    private static volatile Masterroomdatabse INSTANCE;

    static Masterroomdatabse getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized ( Masterroomdatabse.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Masterroomdatabse.class, "Master_database")
                            .fallbackToDestructiveMigration()
                            // .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
