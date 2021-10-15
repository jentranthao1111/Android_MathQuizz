package com.example.mathquizz.model;

public class Question {
    private String text;
    private double respostaCorrecta;
    private double respostaIncorreta;

   // Constructor for the questions
    public Question(String texto, double respostaCorrecta, double respostaIncorreta){

        // this refers to the current obj in the method or the constructor
        this.text = texto;
        this.respostaCorrecta = respostaCorrecta;
        this.respostaIncorreta = respostaIncorreta;
    }

    public String getText(){return this.text;}

    public void setText(String text) {
        this.text = text;
    }

    public double getRespostaCorrecta(){return respostaCorrecta; }

    public double getRespostaIncorreta(){return respostaIncorreta;}
}
