package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ranking extends AppCompatActivity {
    private int acertosAtuais;
    private TextView txtNomeUsuario, txtPontuacao;
    private Button btnResponderNovamente, btnTelaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ranking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnResponderNovamente = findViewById(R.id.btnResponderNovamente);
        btnTelaPrincipal = findViewById(R.id.btnTelaPrincipal);
        txtNomeUsuario = findViewById(R.id.txtNomeUsuario);
        txtPontuacao = findViewById(R.id.txtPontuacao);


        acertosAtuais = getIntent().getIntExtra("TOTAL_ACERTOS", 0);
        String nome = SessaoUsuario.getInstance().getNomeUsuario();
        txtNomeUsuario.setText(nome);

        txtPontuacao.setText(String.valueOf(acertosAtuais));


        btnResponderNovamente.setOnClickListener(v -> {
            Intent intent = new Intent(Ranking.this, Pergunta1.class);
            intent.putExtra("NOME_USUARIO", SessaoUsuario.getInstance().getNomeUsuario());
            startActivity(intent);
        });

        btnTelaPrincipal.setOnClickListener(v ->{
            Intent intent = new Intent(Ranking.this, MainActivity.class);
            startActivity(intent);
        });

    }
}