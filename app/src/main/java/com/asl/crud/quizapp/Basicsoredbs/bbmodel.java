package com.asl.crud.quizapp.Basicsoredbs;

public class bbmodel {
    private String maxscore, musername;

    public bbmodel(String maxscore, String musername) {
        this.maxscore = maxscore;
        this.musername = musername;
    }

    public String getMaxscore() {
        return maxscore;
    }

    public String getMusername() {
        return musername;
    }
}
