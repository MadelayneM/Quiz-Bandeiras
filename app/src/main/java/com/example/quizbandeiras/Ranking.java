package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {
    private ListView listViewRanking;
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
        listViewRanking = findViewById(R.id.listViewRanking);

        // 1. Recebe a pontuação obtida no Quiz
        int acertosAtuais = getIntent().getIntExtra("TOTAL_ACERTOS", 0);
        String nomeAtual = SessaoUsuario.getInstance().getNomeUsuarioAtual();

        // 2. Se houver um nome válido ativo, registra no Ranking
        if (nomeAtual != null && !nomeAtual.isEmpty()) {
            SessaoUsuario.getInstance().adicionarAoRanking(nomeAtual, acertosAtuais);
            // Limpa o nome atual para evitar que duplique ao girar a tela
            SessaoUsuario.getInstance().setNomeUsuarioAtual("");
        }

        // 3. Monta as strings para a exibição no ListView
        List<Jogador> ranking = SessaoUsuario.getInstance().getListaRanking();
        List<String> exibicaoRanking = new ArrayList<>();

        int posicao = 1;
        for (Jogador j : ranking) {
            exibicaoRanking.add(posicao + "º  -  " + j.getNome() + "  |  Acertos: " + j.getPontuacao());
            posicao++;
        }

        // 4. Configura o Adapter do ListView com cor de texto preta
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                exibicaoRanking
        ) {
            @Override
            public android.view.View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(android.graphics.Color.BLACK); // Força a cor do texto para Preto
                return textView;
            }
        };

        listViewRanking.setAdapter(adapter);

        // Ações dos botões
        btnResponderNovamente.setOnClickListener(v -> {
            // Reutiliza o mesmo nome digitado anteriormente e reinicia o jogo
            SessaoUsuario.getInstance().setNomeUsuarioAtual(nomeAtual);
            Intent intent = new Intent(Ranking.this, Pergunta1.class);
            startActivity(intent);
            finish();
        });

        btnTelaPrincipal.setOnClickListener(v -> {
            Intent intent = new Intent(Ranking.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}