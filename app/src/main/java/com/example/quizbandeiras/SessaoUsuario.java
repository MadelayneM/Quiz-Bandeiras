package com.example.quizbandeiras;

public class SessaoUsuario {
    private static SessaoUsuario instance;
    private String nomeUsuario;

    private SessaoUsuario() {}

    public static synchronized SessaoUsuario getInstance() {
        if (instance == null){
            instance = new SessaoUsuario();
        }
        return instance;
    }

    public String getNomeUsuario(){
        return nomeUsuario;
    }
    public void setNomeUsuario(String texto){
        this.nomeUsuario = texto;
    }
}
