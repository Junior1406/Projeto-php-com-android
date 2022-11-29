package com.files.blocodenotas.Atividade.Editor;

public interface EditorView {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String menssagem);
    void onRequestError(String menssagem);

}
