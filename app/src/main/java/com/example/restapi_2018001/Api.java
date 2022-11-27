package com.example.restapi_2018001;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://api-blue-archive.vercel.app/api/";
    @GET("characters")
    Call<PojoResponse> getStudent();
}

