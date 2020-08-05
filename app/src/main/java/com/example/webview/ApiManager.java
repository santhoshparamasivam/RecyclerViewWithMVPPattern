package com.example.webview;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {

//    https://run.mocky.io/v3/325512ea-9fd0-4aeb-9cf5-cfda5c96f7a0
    @GET("v3/325512ea-9fd0-4aeb-9cf5-cfda5c96f7a0")
    Call<ResponseModel> getDetails();






}

