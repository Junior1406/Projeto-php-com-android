package com.files.blocodenotas.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nota {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("titulo") private String titulo;
    @Expose
    @SerializedName("nota") private String nota;
    @Expose
    @SerializedName("cor") private int cor;
    @Expose
    @SerializedName("data") private String data;
    @Expose
    @SerializedName("sucesso") private Boolean sucesso;
    @Expose
    @SerializedName("menssagem") private String menssagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }
}
