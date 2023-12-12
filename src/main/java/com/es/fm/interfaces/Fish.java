package com.es.fm.interfaces;

import org.springframework.stereotype.Service;

public class Fish {
    Long id;
    Float peso;
    Float expectativaVida;
    String temperamento;
    String habitatNatural;
    String alimentacao;
    String nivelCuidado;
    String compatibilidade;
    Float dimensaoTanque;
    String coloracaoPadrao;
    String nivelDificuldade;
    String regiaoOrigem;
    Float preco;

    public String swim() {
        return "";
    }


    public Long getId() {
        return id;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getExpectativaVida() {
        return expectativaVida;
    }

    public void setExpectativaVida(Float expectativaVida) {
        this.expectativaVida = expectativaVida;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public String getHabitatNatural() {
        return habitatNatural;
    }

    public void setHabitatNatural(String habitatNatural) {
        this.habitatNatural = habitatNatural;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public String getNivelCuidado() {
        return nivelCuidado;
    }

    public void setNivelCuidado(String nivelCuidado) {
        this.nivelCuidado = nivelCuidado;
    }

    public String getCompatibilidade() {
        return compatibilidade;
    }

    public void setCompatibilidade(String compatibilidade) {
        this.compatibilidade = compatibilidade;
    }

    public Float getDimensaoTanque() {
        return dimensaoTanque;
    }

    public void setDimensaoTanque(Float dimensaoTanque) {
        this.dimensaoTanque = dimensaoTanque;
    }

    public String getColoracaoPadrao() {
        return coloracaoPadrao;
    }

    public void setColoracaoPadrao(String coloracaoPadrao) {
        this.coloracaoPadrao = coloracaoPadrao;
    }

    public String getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(String nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public String getRegiaoOrigem() {
        return regiaoOrigem;
    }

    public void setRegiaoOrigem(String regiaoOrigem) {
        this.regiaoOrigem = regiaoOrigem;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public void setId(Long id) {
        this.id = id;
    }
}