package com.example.quizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    //Creating button var
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    TextView mScoreTextView;
    ProgressBar mProgressBar;

    //Create counter and score
    int mCount = 0;
    int mScore = 0;

    //This creates a question bank array of TrueFalse elements
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
   };

    //Declaring constants
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //If there exists a saved state, then we grab the values of mScore and mCount from the savestate
        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mCount = savedInstanceState.getInt("CountKey");
        } else {
            //If there is no savestate, reset count and score
            mScore = 0;
            mCount = 1;

        }

        //Define elements in Java
        mTrueButton = findViewById(R.id.buttonTrue);
        mFalseButton = findViewById(R.id.buttonFalse);
        mQuestionTextView = findViewById(R.id.question);
        mScoreTextView = findViewById(R.id.scoreCalc);
        mProgressBar = findViewById(R.id.progressBar);



        //Drawing questions from question array
        int mQuestion = mQuestionBank[mCount].getQuestionID();
        boolean mAnswer = mQuestionBank[mCount].isAnswer();


        //We can change the text in a textview item using setText attribute. It accepts both a string
        //and a resource ID
        mQuestionTextView.setText(mQuestion);

        //mScoreCalc.setText(mScore)


        //Setting listeners

        //We can create common actions which are easier to assign. If an action is used more than
        //once, according to convention we should use this method
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Quizzer" , "This is a common message");
                //Toast making. This is an anonymous toast because no variable is created
                //Toast.makeText(getApplicationContext() , "True Pressed!" , Toast.LENGTH_SHORT).show();

                if (mAnswer == true) {
                    //mScore = (mScore + 1);
                    //Toast.makeText(getApplicationContext() , "Correct!" , Toast.LENGTH_SHORT).show();
                

                }
                checkAnswer(true);
                updateQuestion();



            }

        };

        //myListener is now active on mTrueButton
        mTrueButton.setOnClickListener(myListener);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Creating a toast message. Needs context, message and duration
                    Toast myToast = Toast.makeText(getApplicationContext() , "False Pressed!" , Toast.LENGTH_SHORT);
                    //myToast.show();
                    if (mAnswer == false) {
                        //mScore = (mScore + 1);
                        //Toast.makeText(getApplicationContext() , "Correct!" , Toast.LENGTH_SHORT).show();
                    }

                    checkAnswer(false);
                    updateQuestion();


                }

        });



    //This creates an instance of the TrueFalse class with the specified attributes
    TrueFalse exampleQuestion = new TrueFalse(R.string.question_1 , true );
    //type      name                            constructor + arguments


    }

    //Making the program run again until desired count is reached. Create the updateQuestion method,
    // Then call it when a button is clicked


    private void updateQuestion() {

        mCount = (mCount + 1) % mQuestionBank.length;

        //Creating the end-game dialogue
        if (mCount == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You scored " + mScore + " points!");
            alert.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }

            });
            alert.setNegativeButton("Reset Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mCount = 1;
                    mScore = 0;
                    updateQuestion();
                }
            });
            alert.show();

        }

        int mQuestion = mQuestionBank[mCount].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        //This moves progressbar by set increment
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);

    }

    //New method to check answers
    private void checkAnswer(boolean userSelection) {
        boolean correctAnswer = mQuestionBank[mCount].isAnswer();

        if(userSelection == correctAnswer) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast , Toast.LENGTH_SHORT).show();
            //This increases score if answer is correct
            mScore = (mScore + 1);

        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast , Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        //This code will save our score and count if the user closes the app
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey" , mScore);

        outState.putInt("CountKey" , mCount);
    }

}