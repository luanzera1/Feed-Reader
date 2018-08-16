package com.luanzera.feedreaderapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.luanzera.feedreaderapp.Adapter.ItemAdapter;
import com.luanzera.feedreaderapp.Common.Common;
import com.luanzera.feedreaderapp.Model.RSSResponse;
import com.luanzera.feedreaderapp.Retrofit.RSSApi;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String CACHE = "rssCache";

    Toolbar toolbar;
    RecyclerView recycler_rss;
    RSSApi mService;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = Common.getAPI();
        Paper.init(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        recycler_rss = findViewById(R.id.recycler_rss);
        recycler_rss.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_rss.setHasFixedSize(true);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Paper.book().destroy();
                loadRSS();
            }
        });

        loadRSS();

    }

    private void loadRSS() {
        swipeRefreshLayout.setRefreshing(true);

        String cache = Paper.book().read(CACHE);
        if (cache != null && !cache.isEmpty()) {
            RSSResponse rssResponse = new Gson().fromJson(cache, RSSResponse.class);
            displayRSS(rssResponse);
            Log.d("DEBUG", "via Paper");

        } else {
            mService.getRSS(Common.RSS_URL).enqueue(new Callback<RSSResponse>() {
                @Override
                public void onResponse(Call<RSSResponse> call, Response<RSSResponse> response) {
                    Paper.book().write(CACHE, new Gson().toJson(response.body()));
                    displayRSS(response.body());
                    Log.d("DEBUG", "via Retrofit");
                }

                @Override
                public void onFailure(Call<RSSResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("DEBUG", t.getMessage());
                }
            });
        }
    }

    private void displayRSS(RSSResponse rssResponse) {
        swipeRefreshLayout.setRefreshing(false);
        recycler_rss.setAdapter(new ItemAdapter(getApplicationContext(), rssResponse.getItems()));
    }
}
