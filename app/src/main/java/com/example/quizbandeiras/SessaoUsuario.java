package com.example.quizbandeiras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SessaoUsuario {
    private static SessaoUsuario instance;
    private String nomeUsuarioAtual;
    private List<Jogador> listaRanking;

    private SessaoUsuario() {
        listaRanking = new ArrayList<>();
    }

    public static synchronized SessaoUsuario getInstance() {
        if (instance == null){
            instance = new SessaoUsuario();
        }
        return instance;
    }

    public String getNomeUsuarioAtual(){
        return nomeUsuarioAtual;
    }

    public void setNomeUsuarioAtual(String texto){
        this.nomeUsuarioAtual = texto;
    }

    // Adiciona uma nova pontuação ao ranking e ordena do maior para o menor
    public void adicionarAoRanking(String nome, int pontuacao) {
        listaRanking.add(new Jogador(nome, pontuacao));
        // Ordena por pontuação em ordem decrescente (maiores pontuações primeiro)
        Collections.sort(listaRanking, (j1, j2) -> Integer.compare(j2.getPontuacao(), j1.getPontuacao()));
    }

    public List<Jogador> getListaRanking() {
        return listaRanking;
    }
}