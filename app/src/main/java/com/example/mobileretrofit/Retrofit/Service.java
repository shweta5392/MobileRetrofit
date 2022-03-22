package com.example.mobileretrofit.Retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {
    @FormUrlEncoded
    @POST("logincheck")
     Call<ResponseBody> mobilno (@Field("usernumber") String usernumber);

}


