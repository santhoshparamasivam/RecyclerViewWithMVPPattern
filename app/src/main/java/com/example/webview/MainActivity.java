package com.example.webview;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MainContract.MainView{

    RecyclerView recyclerView;
    public MainContract.presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        presenter = new MainPresenterImpl(this, new RetrofitApiCall());
        presenter.requestDataFromServer();
    }

    @Override
    public void setDataToRecyclerView(ArrayList<ResponseModel.Datum> noticeArrayList) {
        RecyclerAdapter adapter = new RecyclerAdapter(this,noticeArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(String throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable,
                Toast.LENGTH_LONG).show();
    }
}