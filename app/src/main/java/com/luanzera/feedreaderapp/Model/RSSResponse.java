package com.luanzera.feedreaderapp.Model;

import java.util.List;

public class RSSResponse {

    private String status;
    private Feed feed;
    private List<Item> items;

    public RSSResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
