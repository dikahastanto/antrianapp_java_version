package com.dikahastanto.antrian.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dikahastanto.antrian.R;
import com.dikahastanto.antrian.adapter.AntrianAdapter;
import com.dikahastanto.antrian.api.RetrofitClient;
import com.dikahastanto.antrian.models.Antrian;
import com.dikahastanto.antrian.models.AntrianResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tampil_all extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AntrianAdapter adapter;
    private List<Antrian> antrianList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_all);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<AntrianResponse> call = RetrofitClient.getInstance().getApi().getDatas_antrian();
        call.enqueue(new Callback<AntrianResponse>() {
            @Override
            public void onResponse(Call<AntrianResponse> call, Response<AntrianResponse> response) {

                antrianList = response.body().getDatas_antrian();

                adapter = new AntrianAdapter(getApplication(), antrianList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<AntrianResponse> call, Throwable t) {

            }
        });
    }


}
