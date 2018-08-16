package com.luanzera.feedreaderapp.Common;

import com.luanzera.feedreaderapp.Model.Item;
import com.luanzera.feedreaderapp.Retrofit.RSSApi;
import com.luanzera.feedreaderapp.Retrofit.RetrofitClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    public static final String RSS_URL = "https://rss.tecmundo.com.br/feed";

    public static Item currentItem;

    public static RSSApi getAPI() {
        return RetrofitClient.getInstance().create(RSSApi.class);
    }

    public static String pubDateFormatter(String pubDate) {
        DateFormat currentDate = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date date = currentDate.parse(pubDate);
            DateFormat newDate = new SimpleDateFormat("dd/mm/yyyy");
            String newDateFormatted = newDate.format(date);
            return newDateFormatted;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
