package com.example.webview;

import android.util.Log;



import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiCall implements MainContract.GetNoticeIntractor {
    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager api = retrofit.create(ApiManager.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<ResponseModel> call = api.getDetails();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ArrayList<ResponseModel.Datum>data=new ArrayList<>();

                if (response.code()==200) {
                    if (response.body() != null) {
                        data.addAll(response.body().getData());
                        onFinishedListener.onFinished(data);
                    }
                }else{
                    onFinishedListener.onFailure("exception");
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                onFinishedListener.onFailure("please try again later");
            }
        });

    }
}