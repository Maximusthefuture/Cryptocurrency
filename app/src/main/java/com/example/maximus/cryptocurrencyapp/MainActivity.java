package com.example.maximus.cryptocurrencyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.maximus.cryptocurrencyapp.model.CurrencyResponse;
import com.example.maximus.cryptocurrencyapp.model.Model;
import com.example.maximus.cryptocurrencyapp.utils.ApiClient;
import com.example.maximus.cryptocurrencyapp.utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private final static String TEST_API_KEY = "";
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
//    private List<Mo> currencyList = new ArrayList<>();
    private CurrencyAdapter currencyAdapter;
    private  Model model;
    List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        model = new Model();
        list = new ArrayList<>();


        testRetrofit();
        recyclerView.setAdapter(new CurrencyAdapter(list));

    }


    public void testRetrofit() {


        if (TEST_API_KEY.isEmpty()) {
            Toast.makeText(this, "Please check API_KEY", Toast.LENGTH_SHORT).show();
            return;
        }
        CurrencyAdapter adapter = new CurrencyAdapter(list);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ApiInterface apiInterface
                = ApiClient.getClient().create(ApiInterface.class);

        Call<CurrencyResponse> call = apiInterface.getLastestCurrency(TEST_API_KEY );

//        ??????????
        Call<CurrencyResponse> callImage =  apiInterface.getCurrencyDetails((int) adapter.getItemId(model.getId()), TEST_API_KEY);


        call.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                int status = response.code();
                List<Model> list = response.body().getData();
                recyclerView.setAdapter(new CurrencyAdapter(list));
                Log.d(TAG, "list size :" + list.size());
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });



     }
}
