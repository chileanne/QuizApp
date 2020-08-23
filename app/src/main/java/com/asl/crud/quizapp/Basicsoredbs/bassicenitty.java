package com.asl.crud.quizapp.Basicsoredbs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "BasicScore")
public class bassicenitty {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ids;

    @NonNull
    @ColumnInfo(name ="userid")
    private String muserid;

    @NonNull
    @ColumnInfo(name ="username")
    private String musername;

    @NonNull
    @ColumnInfo(name ="score")
    private String mscore;

    public bassicenitty(@NonNull int ids, @NonNull String muserid, @NonNull String musername, @NonNull String mscore) {
        this.ids = ids;
        this.muserid = muserid;
        this.musername = musername;
        this.mscore = mscore;
    }



    @NonNull
    public int getIds() {
        return ids;
    }

    @NonNull
    public String getMuserid() {
        return muserid;
    }

    @NonNull
    public String getMusername() {
        return musername;
    }

    @NonNull
    public String getMscore() {
        return mscore;
    }
}
