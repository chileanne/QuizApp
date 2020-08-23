package com.asl.crud.quizapp.Legendsdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Legend")
public class Legendentity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ids;

    @NonNull
    @ColumnInfo(name ="question")
    private String mquestion;

    @NonNull
    @ColumnInfo(name ="optionA")
    private String moptionA;

    @NonNull
    @ColumnInfo(name ="optionB")
    private String moptionB;

    @NonNull
    @ColumnInfo(name ="optionC")
    private String moptionC;

    @NonNull
    @ColumnInfo(name ="optionD")
    private String moptionD;

    @NonNull
    @ColumnInfo(name ="correctanswer")
    private String mcorrectanswer;

    @NonNull
    @ColumnInfo(name ="questionid")
    private String mquestionid;


    @NonNull
    @ColumnInfo(name ="descrip")
    private String  mshort;

    public Legendentity(@NonNull int ids, @NonNull String mquestion, @NonNull String moptionA, @NonNull String moptionB, @NonNull String moptionC, @NonNull String moptionD, @NonNull String mcorrectanswer, @NonNull String mquestionid, @NonNull String mshort) {
        this.ids = ids;
        this.mquestion = mquestion;
        this.moptionA = moptionA;
        this.moptionB = moptionB;
        this.moptionC = moptionC;
        this.moptionD = moptionD;
        this.mcorrectanswer = mcorrectanswer;
        this.mquestionid = mquestionid;
        this.mshort = mshort;
    }

    @NonNull
    public int getIds() {
        return ids;
    }

    @NonNull
    public String getMquestion() {
        return mquestion;
    }

    @NonNull
    public String getMoptionA() {
        return moptionA;
    }

    @NonNull
    public String getMoptionB() {
        return moptionB;
    }

    @NonNull
    public String getMoptionC() {
        return moptionC;
    }

    @NonNull
    public String getMoptionD() {
        return moptionD;
    }

    @NonNull
    public String getMcorrectanswer() {
        return mcorrectanswer;
    }

    @NonNull
    public String getMquestionid() {
        return mquestionid;
    }

    @NonNull
    public String getMshort() {
        return mshort;
    }
}
