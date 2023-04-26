package com.example.ta_sinarmas.activity;

import static android.provider.MediaStore.Video.VideoColumns.CATEGORY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.ta_sinarmas.LoginActivity;
import com.example.ta_sinarmas.R;
import com.example.ta_sinarmas.RegisterActivity;
import com.example.ta_sinarmas.adapter.MovieAdapter;
import com.example.ta_sinarmas.model.Response;
import com.example.ta_sinarmas.model.Result;
import com.example.ta_sinarmas.rest.ApiClient;
import com.example.ta_sinarmas.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private MovieAdapter adapter;
    private SearchView searchView;

    String API_KEY = "7cd825774cde56bac9a76cd82c020963";
    String language = "en-US";
    String category = "popular";
    int page = 1;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvMovie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CallRetrofit();
    }

    private void CallRetrofit() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Response> call = apiInterface.getmovie(category,API_KEY,language,page);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                List<Result> mList = response.body().getResults();
                adapter = new MovieAdapter(MainActivity.this,mList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logout);
                return true;

        }
        return false;
    }
}