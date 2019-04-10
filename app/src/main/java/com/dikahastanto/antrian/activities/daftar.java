package com.dikahastanto.antrian.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dikahastanto.antrian.R;
import com.dikahastanto.antrian.api.RetrofitClient;
import com.dikahastanto.antrian.models.DefaultResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class daftar extends AppCompatActivity  implements View.OnClickListener{

//    private EditText txt_username,txt_password,txt_email,txt_nama_lengkap;
    @BindView(R.id.txt_username)
    EditText txt_username;

    @BindView(R.id.txt_password)
    EditText txt_password;

    @BindView(R.id.txt_email)
    EditText txt_email;

    @BindView(R.id.txt_nama_lengkap)
    EditText txt_nama_lengkap;

    @BindView(R.id.btn_register_save)
    Button btn_register_save;

    @BindView(R.id.back_daftar)
    ImageView btn_back_daftar;

    @BindView(R.id.back_daftar1)
    TextView btn_back_daftar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        ButterKnife.bind(this);

        btn_register_save.setOnClickListener(this);
        btn_back_daftar.setOnClickListener(this);
        btn_back_daftar.setOnClickListener(this);

    }

    private void userSignUp(){
        String username = txt_username.getText().toString().trim();
        String password = txt_password.getText().toString().trim();
        String email = txt_email.getText().toString().trim();
        String nama_lengkap = txt_nama_lengkap.getText().toString().trim();

        if (email.isEmpty()){
            txt_email.setError("Email is required");
            txt_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txt_email.setError("Masukan Email yang benar");
            txt_email.requestFocus();
            return;
        }

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

        if (nama_lengkap.isEmpty()){
            txt_nama_lengkap.setError("Nama Lengkap wajib diisi");
            txt_nama_lengkap.requestFocus();
            return;
        }

        //registrasi user dengna api
        retrofit2.Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(username,password,email,nama_lengkap);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(retrofit2.Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if (response.code() == 201){

                    DefaultResponse dr = response.body();
                    Toast.makeText(daftar.this, dr.getMsg() + ". Silahkan Login", Toast.LENGTH_LONG).show();
                    Intent go_login = new Intent(daftar.this,login.class);
                    startActivity(go_login);
                }else {
                    Toast.makeText(daftar.this, "Username Sudah ada, Silahkan ganti username anda", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<DefaultResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_save:
                userSignUp();
                break;

            case R.id.back_daftar:
                Intent intent = new Intent(this,login.class);
                startActivity(intent);
                break;

            case R.id.back_daftar1:
                Intent intent1 = new Intent(this,login.class);
                startActivity(intent1);
                break;
        }
    }
}
