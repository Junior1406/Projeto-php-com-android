package com.files.blocodenotas.Atividade.Main;

import androidx.annotation.NonNull;

import com.files.blocodenotas.Api.ApiCliente;
import com.files.blocodenotas.Api.ApiInterface;
import com.files.blocodenotas.Modelo.Nota;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private  MainView view;

    public MainPresenter(MainView view){
        this.view = view;
    }

    public void getData(){
        view.showLoading();

        ApiInterface apiInterface = ApiCliente.getApiCliente().create(ApiInterface.class);
        Call<List<Nota>> call = apiInterface.getNotas();
        call.enqueue(new Callback<List<Nota>>() {
            @Override
            public void onResponse(Call<List<Nota>> call, @NonNull Response<List<Nota>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    view.onGetResultt(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Nota>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
