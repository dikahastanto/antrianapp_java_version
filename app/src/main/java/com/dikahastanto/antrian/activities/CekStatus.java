package com.dikahastanto.antrian.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dikahastanto.antrian.R;
import com.dikahastanto.antrian.api.RetrofitClient;
import com.dikahastanto.antrian.models.DefaultResponse;
import com.dikahastanto.antrian.models.NoAntrianResponse;
import com.dikahastanto.antrian.models.User;
import com.dikahastanto.antrian.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CekStatus extends AppCompatActivity implements View.OnClickListener {

    private Button btn_back,btn_back1;
    private TextView txt_status_no,txt_jam,txt_ket1,txt_ket2;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_status);

        findViewById(R.id.back_status).setOnClickListener(this);
        findViewById(R.id.back_status1).setOnClickListener(this);
        findViewById(R.id.btn_delete_nomor).setOnClickListener(this);
        txt_status_no = findViewById(R.id.txt_status_no_antrian);
        txt_jam = findViewById(R.id.txt_status_jam_antrian);
        txt_ket1 = findViewById(R.id.status_keterangan1);
        txt_ket2 = findViewById(R.id.status_keterangan2);
        User user = SharedPrefManager.getInstance(this).getUser();

        Call<NoAntrianResponse> call = RetrofitClient.getInstance().getApi().getnoantrianbyusername(user.getUsername());
        call.enqueue(new Callback<NoAntrianResponse>() {
            @Override
            public void onResponse(Call<NoAntrianResponse> call, Response<NoAntrianResponse> response) {
                NoAntrianResponse noAntrianResponse = (NoAntrianResponse) response.body();
//                Toast.makeText(CekStatus.this,noAntrianResponse.getData_antrian().getNo_panggil(),Toast.LENGTH_LONG).show();


                if (noAntrianResponse.getData_antrian().getNo_panggil() == null){
                    txt_status_no.setText("No.--");
                    txt_jam.setText("--:--");
                    txt_ket1.setText("1. Status anda sedang Tidak dalam antrian");
                    txt_ket2.setText("2. Silahkan mengambil nomor antrian");
                }else {
                    txt_status_no.setText(noAntrianResponse.getData_antrian().getNo_panggil());
                    id = Integer.parseInt((String) noAntrianResponse.getData_antrian().getId());
                    txt_jam.setText(noAntrianResponse.getData_antrian().getJam());
                }

            }

            @Override
            public void onFailure(Call<NoAntrianResponse> call, Throwable t) {

            }
        });
    }

    private void deletenoantrian(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apakah Anda Yakin Membatalkan No Antrian?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Call<DefaultResponse> call = RetrofitClient
                                                .getInstance()
                                                .getApi().deletnoantrian(String.valueOf(id));

                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        Toast.makeText(CekStatus.this,response.body().getMsg(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.back_status):
                Intent back = new Intent(this,home_app.class);
                startActivity(back);
                break;

            case (R.id.back_status1) :
                Intent back1 = new Intent(this,home_app.class);
                startActivity(back1);
                break;

                case(R.id.btn_delete_nomor) :
                    if (txt_status_no.getText() == "No.--"){
                        Toast.makeText(this,"Tidak ada Data",Toast.LENGTH_LONG).show();
                    }else{
                        deletenoantrian();
                    }
                    break;
        }
    }
}
