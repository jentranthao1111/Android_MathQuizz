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

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class QuizzActivity extends Activity {

    public static final String INDICE_QUESTION = "INDICE_QUESTION";
    private final QuestionRepository respository = new QuestionRepository();
    private final Locale locale = new Locale("en", "CA");
    private int indice_question = 0;
    private TextView textViewTextoPergunta ;
    int maxQuestion = respository.getListQuestions().size();
    private Button btnAns1;
    private Button btnAns2;
    private Button btnResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // class utility

        Question question = respository.getListQuestions().get(indice_question);

        // R - class utility resources, id - what to access, txtQuestion - id of obj
        textViewTextoPergunta = findViewById(R.id.text_question_textview);

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

                try {
                    // NumberFormat is a java abstract class for all the number
                    NumberFormat format = NumberFormat.getCurrencyInstance(locale); // instantiate format as a var to store currency of the locale
                    // Number is a java abstract class that represent values that are automatically convertible to format below
                    Number number = format.parse(reposta);
                    if (analyzeQuestion.isRespotaCorrecta(question, number.doubleValue())) {
                        message = "Congrats , Correct Answer!";

                    } else {
                        message = "Ahh, Wrong Answer";
                    }

                }catch (ParseException e){
                    message = e.getMessage();
                };
                Toast.makeText(QuizzActivity.this, message, Toast.LENGTH_SHORT).show();

            } // end function OnClick
        }; // end instantiate listener for options


        btnAns1 = findViewById(R.id.option1_button); // this button has a variable named btnAns1
        btnAns1.setText(String.valueOf(question.getRespostaCorrecta()));
        btnAns1.setOnClickListener(listener);

        btnAns2 = findViewById(R.id.option2_button);
        btnAns2.setText(String.valueOf(question.getRespostaIncorreta()));
        btnAns2.setOnClickListener(listener);

        //listener for btnNextQuestion
        View.OnClickListener listenerNextQuestion = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indice_question ++;
                if (indice_question >= maxQuestion){
                   indice_question = 0;
                }
               exibirQuestao(indice_question);
            } // end func onclick
        }; // end instantiate listener for next question

        btnResult = findViewById(R.id.proxima_question_button);
        btnResult.setOnClickListener(listenerNextQuestion);

        if(savedInstanceState != null){
            indice_question = savedInstanceState.getInt(INDICE_QUESTION );
            //indice_question = savedInstanceState.getInt()
        }
        exibirQuestao(indice_question);

    }// end onCreate

    // to reload the previous data in case the application is crashed/ exploded
    protected void onSaveInstanceState(Bundle outState){ // bundle is out of its state - being exploded
       // super.onSaveInstanceState(INDICE_QUESTION,  indice_question);
       super.onSaveInstanceState(outState);
       outState.putInt(INDICE_QUESTION, indice_question);
    }
    public void exibirQuestao (final int indice_question){
        Question question = respository.getListQuestions().get(indice_question);

        textViewTextoPergunta.setText(question.getText());

        String repostaCorreta = String.format(locale," %.2f", question.getRespostaCorrecta());
        String repostaIncorreta = String.format(locale," %.2f", question.getRespostaIncorreta());

        btnAns1.setText(String.valueOf(question.getRespostaIncorreta()));
        btnAns2.setText(String.valueOf(question.getRespostaIncorreta()));
    }

}// end class

//Listeners are interfaces that connect the button with his interface that we code
// that meaning is what will happen when the button is clicked - it is also called as events

/*2021-09-07: Create the Question class,
    QuizzActiivty for the Listener,
    QuestionRespository for List<Question>,
    AnalyzeQuestion to return correct answer*/

/*2021-09-14:  Create listener for the options - answer and create the toast message */

/*2021-09-16:  Create listener for the result - "next question",
               Optimize the code by using function and private variables */

/*2021-09-21: explaining abt bundle, onSaveInstanceState */
