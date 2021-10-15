package com.example.mathquizz.model;

public class AnalyzeQuestion {

    // method isResporaCorrecta returns boolean by comparing the question.getRepostaCorrecta with the ans
    // chosen
    public boolean isRespotaCorrecta(Question question, double ans){
        return question.getRespostaCorrecta() == ans;
    }

}// end class
