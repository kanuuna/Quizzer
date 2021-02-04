package com.example.quizzer;

//We continue by creating a new class. New classes are often created in a separate file
//This file will hold the question text, and the correct answer
//The question text is drawn from the string.xml file

public class TrueFalse {

    private int mQuestionID;
    private boolean mAnswer;

    //Making a true false object using a constructor

    //Defines attributes of class
    public TrueFalse(int questionResourceID , boolean trueOrFalse) {

        mQuestionID = questionResourceID;
        //Correct answer
        mAnswer = trueOrFalse;

    }

    //Defines methods of class

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }


}
