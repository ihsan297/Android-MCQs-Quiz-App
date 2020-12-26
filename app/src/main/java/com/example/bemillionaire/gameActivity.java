package com.example.bemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class gameActivity extends AppCompatActivity {
    List<Question> questions; //Qrray of Questions
    DBHelper dbHelper; //DB Class Obbj
    int score,questionNumber; //Counter for score and question number
    RadioButton optA,optB,optC,optD;
    TextView tv_question; //current question tetx
    TextView next,tv_score; //next button and score views
    TextView numbering; // for question numbering
    RadioGroup radioGroup; //group of Radion buttons 4 options of Q
    String userAns;//for user answer

    Question question; //current question


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //linking code to XMLs
        optA=findViewById(R.id.optA);
        optB=findViewById(R.id.optB);
        optC=findViewById(R.id.optC);
        optD=findViewById(R.id.optD);
        numbering=findViewById(R.id.numbering);
        next=findViewById(R.id.next);
        radioGroup=findViewById(R.id.radGrp);



        tv_question=findViewById(R.id.quesion);
        tv_score=findViewById(R.id.score);
        score=0;
        questionNumber=0;
        questions=new ArrayList<>(); //Array of questions

    //inializing DB
        dbHelper=new DBHelper(this);
        if(dbHelper.getData().size()==0){//checks if data is no inserted
            dbHelper.createQuestions();
        }

        questions=dbHelper.getData();
        question=getAQuestion(); // getting a question from Array
        numbering.setText(Integer.toString(questionNumber+1)+"/"+Integer.toString(questions.size()) );//(0/6)
        questionNumber++;//incr questions
        tv_question.setText(question.getQuestion());
        //setting options
        optA.setText(question.getOption_a());
        optB.setText(question.getOption_b());
        optC.setText(question.getOption_c());
        optD.setText(question.getOption_d());
        //for radio check selectedd
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.optA){
                    userAns=optA.getText().toString();

                }
                else if(checkedId==R.id.optB){
                    userAns=optB.getText().toString();
                }
                else if(checkedId==R.id.optC){
                    userAns=optC.getText().toString();
                }
                else if(checkedId==R.id.optD){
                    userAns=optD.getText().toString();
                }
                else{
                    userAns=null;
                }
            }
        });
        radioGroup.clearCheck();// clear radio checked


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //movinf to next question
                if(userAns!=null){
                    Log.d("Opt",userAns);
                    updateScore(question,userAns); //update scores

                    tv_score.setText(Integer.toString(score));
                    numbering.setText(Integer.toString(questionNumber+1)+"/"+Integer.toString(questions.size()) );
                    if(questionNumber==questions.size()){
                       Intent intent=new Intent(gameActivity.this,MainActivity.class);
                       intent.putExtra("score",Integer.toString(score));


                        if(score==questions.size()){
                            intent.putExtra("msg","You Become Millionaire");

                        }
                        else{
                            intent.putExtra("msg","Sorry!! You could't");
                        }


                        startActivity(intent);
                        finish();
                    }
                    else{
                        question=getAQuestion();
                        questionNumber++;
                        tv_question.setText(question.getQuestion());
                        optA.setText(question.getOption_a());
                        optB.setText(question.getOption_b());
                        optC.setText(question.getOption_c());
                        optD.setText(question.getOption_d());
                        radioGroup.clearCheck();
                    }


                }
                else{
                    Toast.makeText(gameActivity.this,"Must choose a option",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    Question getAQuestion(){

        return questions.get(questionNumber);
    }
    void updateScore(Question question,String userAns){
        if(userAns.equals(question.ans)){
            score++;
        }
    }
}
