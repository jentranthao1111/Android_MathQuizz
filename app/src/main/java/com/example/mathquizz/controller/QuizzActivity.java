package com.example.mathquizz.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathquizz.R;
import com.example.mathquizz.model.AnalyzeQuestion;
import com.example.mathquizz.model.Question;
import com.example.mathquizz.model.QuestionRepository;

import java.text.DecimalFormat;

public class QuizzActivity extends Activity {

    private final QuestionRepository respository = new QuestionRepository();
    private int indice_question = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // class utility
        Question question = respository.getListQuestions().get(0);

        //listener for btnNextQuestion
        View.OnClickListener listenerQuestion = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = indice_question; i == respository.getListQuestions().size(); i ++){
                    Question question = respository.getListQuestions().get(i+1);
                }
            }
        };

        // R - class utility resources, id - what to access, txtQuestion - id of obj
        TextView textViewTextoPergunta = findViewById(R.id.text_question_textview);
        textViewTextoPergunta.setText(question.getText());

        // need to instantiate listener
        View.OnClickListener listener = new View.OnClickListener(){ // connect main activity - view to the code
            @Override
            public void onClick(View view) {
                // restricted  variable - This method cannot be overridden for another function
                // ((Button)view) - any buttons in the view
                final String reposta  = ((Button)view).getText().toString();
                AnalyzeQuestion analyzeQuestion = new AnalyzeQuestion();
                String message;
                Question question = respository.getListQuestions().get(0);

                if (analyzeQuestion.isRespotaCorrecta( question, Double.valueOf(reposta)) ){
                    message = "Congrats , Correct Answer!";

                }else{
                    message = "Ahh, Wrong Answer";
                }
                Toast.makeText(QuizzActivity.this, message, Toast.LENGTH_SHORT).show();

            } // end function OnClick
        }; // end instantiate listener for options




        Button btnAns1 = findViewById(R.id.option1_button); // this button has a variable named btnAns1
        btnAns1.setText(String.valueOf(question.getRespostaCorrecta()));
        btnAns1.setOnClickListener(listener);

        Button btnAns2 = findViewById(R.id.option2_button);
        btnAns2.setText(String.valueOf(question.getRespostaIncorreta()));
        btnAns2.setOnClickListener(listener);



        Button btnResult = findViewById(R.id.proxima_question_button);
        btnResult.setText("Next Question");
        btnResult.setOnClickListener(listenerQuestion);


        //Listeners are interfaces that connect the button with his interface that we code
        // that meaning is what will happen when the button is clicked - it is also called as events


    }// end onCreate
    /*2021-09-07: Create the Question class,
    QuizzActiivty for the Listener,
    QuestionRespository for List<Question>,
    AnalyzeQuestion to return correct answer*/

}// end class
