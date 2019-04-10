package com.dikahastanto.antrian.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dikahastanto.antrian.R;
import com.dikahastanto.antrian.api.RetrofitClient;
import com.dikahastanto.antrian.models.LoginResponse;
import com.dikahastanto.antrian.storage.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;


public class login extends AppCompatActivity implements View.OnClickListener{

//    private EditText txt_username,txt_password;
    @BindView(R.id.txt_username_login)
    TextView txt_username;

    @BindView(R.id.txt_password_login)
    TextView txt_password;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.btn_daftar)
    TextView btn_daftar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

//        txt_username = findViewById(R.id.txt_username_login);
//        txt_password = findViewById(R.id.txt_password_login);

//        findViewById(R.id.btn_login).setOnClickListener(this);
            btn_login.setOnClickListener(this);
            btn_daftar.setOnClickListener(this);
//        findViewById(R.id.btn_daftar).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this,home_app.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userLogin(){

        String username = txt_username.getText().toString().trim();
        String password = txt_password.getText().toString().trim();

        if (password.isEmpty()){
            txt_password.setError("Password Wajib diisi");
            txt_password.requestFocus();
            return;
        }

        if (password.length() < 6){
            txt_password.setError("Password minimal 6 karakter");
            txt_password.requestFocus();
            return;
        }

        if (username.isEmpty()){
            txt_username.setError("Username Wajib diisi");
            txt_username.requestFocus();
            return;
        }

        retrofit2.Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogin(username,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (!loginResponse.isError()){
                    //save user and open profile
                    SharedPrefManager.getInstance(login.this)
                            .saveUser(loginResponse
                                    .getUser());

                    Intent intent = new Intent(login.this,home_app.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else {
                    Toast.makeText(login.this, "Login Gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
                Toast.makeText(login.this, "Login gagal", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_login:
                userLogin();
                break;

            case R.id.btn_daftar:
                Intent daftar = new Intent(login.this,daftar.class);
                startActivity(daftar);
                break;
        }
    }
}
