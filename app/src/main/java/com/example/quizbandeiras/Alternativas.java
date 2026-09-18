package com.example.quizbandeiras;

import android.widget.RadioGroup;

public class Alternativas {
    public static boolean isRespostaCorreta(RadioGroup radioGroup, int idRadioButtonCorreto) {
        int idSelecionado = radioGroup.getCheckedRadioButtonId();

        return idSelecionado == idRadioButtonCorreto;
    }


    public static int calcularPontuacao(boolean acertou, int pontuacaoAtual) {
        return acertou ? pontuacaoAtual + 1 : pontuacaoAtual;
    }
}