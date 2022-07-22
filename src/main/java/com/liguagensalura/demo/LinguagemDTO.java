package com.liguagensalura.demo;

import org.springframework.data.annotation.Id;

public class LinguagemDTO {
    @Id
    private String id;
    private String titulo;
    private String imagem;
    private String ranking;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
