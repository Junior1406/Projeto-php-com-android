package com.files.blocodenotas.Atividade.Main;

import com.files.blocodenotas.Modelo.Nota;

import java.util.List;

public interface MainView {

    void showLoading();
    void hideLoading();
    void onGetResultt(List<Nota> notas);
    void onErrorLoading(String menssagem);
}
