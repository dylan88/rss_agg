package com.example.wautel_l.rss_reader_android.obj;

/**
 * Created by wautel_l on 24/02/2018.
 */

public class Item {
    private int item_id;
    private int feed_id;
    private String title;
    private String link;
    private int guid;
    private String description;
    private int categorie_id;
    private int read;
    private int favorite;

    private Item()
    {

    }

    public Item(int item_id, int feed_id, String title, String link, int guid, String description, int categorie_id, int read) {
        this.item_id = item_id;
        this.feed_id = feed_id;
        this.title = title;
        this.link = link;
        this.guid = guid;
        this.description = description;
        this.categorie_id = categorie_id;
        this.read = read;
    }

    public Item(int item_id, int feed_id, String title, String link, int guid, String description, int categorie_id) {
        this.item_id = item_id;
        this.feed_id = feed_id;
        this.title = title;
        this.link = link;
        this.guid = guid;
        this.description = description;
        this.categorie_id = categorie_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(int feed_id) {
        this.feed_id = feed_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
