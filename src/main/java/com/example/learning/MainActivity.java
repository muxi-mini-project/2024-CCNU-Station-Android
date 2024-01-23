package com.example.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton mTrueButton;
    private ImageButton mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mBackButton;
    private TextView mQuestionTextview;
    private TextView mTextViewbtn;
    private Question[] mQuestionBank =new Question[]{
            new Question(R.string.question_africas,false),
            new Question(R.string.question_Amaricas,true),
            new Question(R.string.question_asia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_australia,false)
    };
    private  int mCurrentIndex = 0;

    private void updateQuestion(){
        int question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextview.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId =R.string.correct_toast;
        }else{
            messageResId =R.string.incorrect_toast;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextview=(TextView) findViewById(R.id.question_text_View);
//        int question=mQuestionBank[mCurrentIndex].getTextResId();
//        mQuestionTextview.setText(question);
        updateQuestion();

        mTextViewbtn=(TextView) findViewById(R.id.question_text_View);
        mTextViewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex =(mCurrentIndex + 1)%mQuestionBank.length;
                updateQuestion();
            }
        });


        mNextButton =(ImageButton) findViewById(R.id.next_btn);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex =(mCurrentIndex + 1)%mQuestionBank.length;
//                int question = mQuestionBank[mCurrentIndex].getTextResId();
//                mQuestionTextview.setText(question);
                updateQuestion();
            }
        });

        mBackButton =(ImageButton) findViewById(R.id.back_btn);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex =(mCurrentIndex - 1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton =(ImageButton) findViewById(R.id.true_btn);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
//                Toast.makeText(MainActivity.this,
//                        R.string.correct_toast,
//                        Toast.LENGTH_SHORT).show();
            }
        });
        mFalseButton =(ImageButton) findViewById(R.id.false_btn);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
//                Toast.makeText(MainActivity.this,
//                        R.string.incorrect_toast,
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}