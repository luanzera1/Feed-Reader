package com.luanzera.feedreaderapp.Retrofit;

import com.luanzera.feedreaderapp.Model.RSSResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RSSApi {

    @GET("api.json")
    Call<RSSResponse> getRSS(@Query("rss_url") String rss_url);

}
