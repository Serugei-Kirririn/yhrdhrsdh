package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    API api;
    RecyclerView recyclerView;
    List<Body> mkinos;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
        api = retrofit.create(API.class);
        recyclerView = findViewById(R.id.listOfKino);
        mkinos = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Adapter adapter = new Adapter(mkinos);
        recyclerView.setAdapter(adapter);
        getKino();
    }
    void getKino()
    {
        Call<List<Body>> call = api.getApi();
        call.enqueue(new Callback<List<Body>>() {
            @Override
            public void onResponse(Call<List<Body>> call, Response<List<Body>> response) {
                if(response.isSuccessful())
                {
                    mkinos.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Body>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}