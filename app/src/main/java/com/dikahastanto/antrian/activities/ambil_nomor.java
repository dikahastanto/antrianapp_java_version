package com.dikahastanto.antrian.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dikahastanto.antrian.R;

public class ambil_nomor extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambil_nomor);

        findViewById(R.id.back_ambil_nomor).setOnClickListener(this);
        findViewById(R.id.back_ambil_nomor1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.back_ambil_nomor):

                Intent go_login = new Intent(ambil_nomor.this,home_app.class);
                startActivity(go_login);

                break;
            case (R.id.back_ambil_nomor1):

                Intent go_login1 = new Intent(ambil_nomor.this,home_app.class);
                startActivity(go_login1);

                break;
        }
    }
}
