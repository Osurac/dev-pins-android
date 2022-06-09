package es.ucm.fdi.devpins.data.model;

import java.util.ArrayList;

public class Pin {
    public static ArrayList<Pin> pinArrayList = new ArrayList<>();
    private int id;
    private int user_id;
    private String url;
    private boolean fav;

    public Pin(int id, int user_id, String url, boolean fav) {
        this.id = id;
        this.user_id = user_id;
        this.url = url;
        this.fav = fav;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public boolean getFav() {
        return fav;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }





}
