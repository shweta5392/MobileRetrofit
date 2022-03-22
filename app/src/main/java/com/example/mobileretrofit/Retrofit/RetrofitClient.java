package com.example.mobileretrofit.Retrofit;

import android.app.Service;

import com.example.mobileretrofit.utilities.Utils;

import retrofit2.Retrofit;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static com.example.mobileretrofit.Retrofit.Service getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.site_url)
                    .build();
        }
        final com.example.mobileretrofit.Retrofit.Service service = (com.example.mobileretrofit.Retrofit.Service) retrofit.create(Service.class);
        return service;
    }
}
