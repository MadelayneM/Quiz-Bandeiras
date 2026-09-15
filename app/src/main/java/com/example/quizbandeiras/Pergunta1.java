package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Pergunta1 extends AppCompatActivity {
    private int acertosAtuais = 0;
    private Button btnResponder;
    private RadioGroup rgpAlternativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pergunta1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgpAlternativas =  findViewById(R.id.rgpAlternativas);
        btnResponder =  findViewById(R.id.btnResponder);

        boolean acertou = Alternativas.isRespostaCorreta(rgpAlternativas, R.id.rgbOpcao3);


        rgpAlternativas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Se checkedId for diferente de -1, significa que alguma opção foi marcada
                if (checkedId != -1) {
                    btnResponder.setEnabled(true);
                } else {
                    btnResponder.setEnabled(false);
                }
            }
        });

        btnResponder.setOnClickListener(v -> {
            acertosAtuais = Alternativas.calcularPontuacao(rgpAlternativas, R.id.rgbOpcao3, acertosAtuais);

            Intent intent = new Intent(Pergunta1.this, Pergunta2.class);
            intent.putExtra("TOTAL_ACERTOS", acertosAtuais);
            intent.putExtra("RESPOSTA_ANTERIOR_CORRETA", acertou);

            startActivity(intent);
        });
    }
}
