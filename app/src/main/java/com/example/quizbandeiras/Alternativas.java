package com.example.quizbandeiras;

import android.widget.RadioGroup;

public class Alternativas {
    public static boolean isRespostaCorreta(RadioGroup radioGroup, int idRadioButtonCorreto) {
        int idSelecionado = radioGroup.getCheckedRadioButtonId();

        // Se o usuário não marcou nada, retorna falso
        if (idSelecionado == -1) {
            return false;
        }

        return idSelecionado == idRadioButtonCorreto;
    }


    public static int calcularPontuacao(RadioGroup radioGroup, int idRadioButtonCorreto, int pontuacaoAtual) {
        if (isRespostaCorreta(radioGroup, idRadioButtonCorreto)) {
            return pontuacaoAtual + 1;
        }
        return pontuacaoAtual;
    }
}