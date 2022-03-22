package com.example.mobileretrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileretrofit.R;
import com.example.mobileretrofit.Retrofit.RetrofitClient;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "coding";
    private ProgressDialog pDialog;
    EditText l_number;
    Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         l_number = findViewById(R.id.etNumber);
         bt_login = findViewById(R.id.loginbtn);

         bt_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 LoginCheck(l_number.getText().toString());
             }
         });
    }

    private void LoginCheck(final String usernumber) {
        Call<ResponseBody> call = RetrofitClient.getClient().mobilno(usernumber);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {

                    try {

                        String result = response.body().string();

                        JSONObject obj = new JSONObject(result);

                        String getstatus = obj.getString("status");

                        if (getstatus.equals("false")) {
                            String error = obj.getString("error");
                            Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_LONG).show();
                        } else if (getstatus.equals("true")) {

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                } else {

                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t, Toast.LENGTH_SHORT).show();

            }
        });

    }
}