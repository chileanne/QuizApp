package com.asl.crud.quizapp.Basicdbs;

public class Basicsmodel {
    private String question,optionA,optionB,optionC,optionD,correctLetter,questionid;

    public Basicsmodel(String question, String optionA, String optionB, String optionC, String optionD, String correctLetter, String questionid) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctLetter = correctLetter;
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectLetter() {
        return correctLetter;
    }

    public String getQuestionid() {
        return questionid;
    }
}
