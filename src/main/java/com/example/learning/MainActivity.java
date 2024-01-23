package com.example.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  static final  String TAG ="MainActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextview;

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
        Log.d(TAG,"onCreat(Bundle) called");
        setContentView(R.layout.activity_main);

        mQuestionTextview=(TextView) findViewById(R.id.question_text_View);
//        int question=mQuestionBank[mCurrentIndex].getTextResId();
//        mQuestionTextview.setText(question);
        updateQuestion();

        mNextButton =(Button) findViewById(R.id.next_btn);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex =(mCurrentIndex + 1)%mQuestionBank.length;
//                int question = mQuestionBank[mCurrentIndex].getTextResId();
//                mQuestionTextview.setText(question);
                updateQuestion();
            }
        });



        mTrueButton =(Button) findViewById(R.id.true_btn);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
//                Toast.makeText(MainActivity.this,
//                        R.string.correct_toast,
//                        Toast.LENGTH_SHORT).show();
            }
        });
        mFalseButton =(Button) findViewById(R.id.false_btn);
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
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}
