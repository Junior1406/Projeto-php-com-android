package com.files.blocodenotas.Atividade.Editor;

import com.files.blocodenotas.Api.ApiInterface;

import androidx.annotation.NonNull;

import com.files.blocodenotas.Api.ApiCliente;
import com.files.blocodenotas.Modelo.Nota;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view){

        this.view = view;
    }

    void saveNota(final String titulo, final String nota, final int cor) {
        view.showProgress();

        ApiInterface apiInterface = ApiCliente.getApiCliente()
                .create(ApiInterface.class);
        Call<Nota> call = apiInterface.saveNota(titulo, nota, cor);
        call.enqueue(new Callback<Nota>() {
            @Override
            public void onResponse(@NonNull Call<Nota> call, @NonNull Response<Nota> response) {
                view.hideProgress();

                if(response.isSuccessful() && response.body() != null) {

                    Boolean sucesso = response.body().getSucesso();
                    if (sucesso) {
                        view.onRequestSuccess(response.body().getMenssagem());

                    }else {
                        view.onRequestError(response.body().getMenssagem());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Nota> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateNota(int id, String titulo, String nota, int cor){
        view.showProgress();
        ApiInterface apiInterface = ApiCliente.getApiCliente().create(ApiInterface.class);

        Call<Nota> call = apiInterface.updateNota(id, titulo, nota, cor);
        call.enqueue(new Callback<Nota>() {
            @Override
            public void onResponse(Call<Nota> call, Response<Nota> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSucesso();
                    if(success){
                        view.onRequestSuccess(response.body().getMenssagem());
                    }else {
                        view.onRequestError(response.body().getMenssagem());
                    }
                }
            }

            @Override
            public void onFailure(Call<Nota> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }

    void deleteNota(int id){
        view.showProgress();
        ApiInterface apiInterface = ApiCliente.getApiCliente().create(ApiInterface.class);
        Call<Nota> call = apiInterface.deleteNota(id);
        call.enqueue(new Callback<Nota>() {
            @Override
            public void onResponse(Call<Nota> call, Response<Nota> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSucesso();
                    if(success){
                        view.onRequestSuccess(response.body().getMenssagem());
                    }else {
                        view.onRequestError(response.body().getMenssagem());
                    }
                }
            }

            @Override
            public void onFailure(Call<Nota> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }

}
