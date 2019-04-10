package com.dikahastanto.antrian.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.dikahastanto.antrian.R;
import com.dikahastanto.antrian.api.RetrofitClient;
import com.dikahastanto.antrian.models.DefaultResponse;
import com.dikahastanto.antrian.models.NoAntrian;
import com.dikahastanto.antrian.models.NoAntrianResponse;
import com.dikahastanto.antrian.models.User;
import com.dikahastanto.antrian.storage.SharedPrefManager;

import retrofit2.Callback;
import retrofit2.Response;

public class Get_no_antrian extends AppCompatActivity implements View.OnClickListener{

    private TextView txt_no_antrian,txt_jam,txt_ket1,txt_ket2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_no_antrian);


        findViewById(R.id.btn_ambil_nomor).setOnClickListener(this);
        findViewById(R.id.back_ambil_nomor3).setOnClickListener(this);
        findViewById(R.id.back_ambil_nomor2).setOnClickListener(this);

        retrofit2.Call<NoAntrianResponse> call = RetrofitClient.getInstance().getApi().getData_antrian();
        ((retrofit2.Call) call).enqueue(new Callback() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {

                NoAntrianResponse noAntrianResponse = (NoAntrianResponse) response.body();

                txt_no_antrian = findViewById(R.id.txt_no_antrian);
                txt_jam = findViewById(R.id.txt_jam_antrian);
                txt_ket1 = findViewById(R.id.txt_ket1);
                txt_ket2 = findViewById(R.id.txt_ket2);


                txt_no_antrian.setText(noAntrianResponse.getData_antrian().getNo_panggil());
                txt_jam.setText(noAntrianResponse.getData_antrian().getJam());

                txt_ket1.setText("1. Nomor antrian anda " + noAntrianResponse.getData_antrian().getNo_panggil());
                txt_ket2.setText("2. Anda akan dilayani pada jam " + noAntrianResponse.getData_antrian().getJam());



                //Toast.makeText(Get_no_antrian.this,noAntrianResponse.getData_antrian().getJam(),Toast.LENGTH_LONG).show();




            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {
                Toast.makeText(Get_no_antrian.this,"Gagal",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back_ambil_nomor2 :

                Intent go_login = new Intent(this,home_app.class);
                startActivity(go_login);

                break;

            case R.id.back_ambil_nomor3:

                Intent go_login1 = new Intent(this,home_app.class);
                startActivity(go_login1);

                break;

            case R.id.btn_ambil_nomor:
//                Toast.makeText(this,txt_no_antrian.getText().toString(),Toast.LENGTH_LONG).show();
                User user = SharedPrefManager.getInstance(this).getUser();
                retrofit2.Call<DefaultResponse> call = RetrofitClient.getInstance().
                        getApi().
                        insertnoAntrian(txt_no_antrian.getText().toString(),user.getUsername(),user.getNama_lengkap(),txt_jam.getText().toString());

                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<DefaultResponse> call, Response<DefaultResponse> response) {

                        Toast.makeText(Get_no_antrian.this,"Berhasil",Toast.LENGTH_LONG).show();
                        Intent success = new Intent(Get_no_antrian.this,home_app.class);
                        startActivity(success);

                    }

                    @Override
                    public void onFailure(retrofit2.Call<DefaultResponse> call, Throwable t) {

                    }
                });

                break;
        }
    }
}
