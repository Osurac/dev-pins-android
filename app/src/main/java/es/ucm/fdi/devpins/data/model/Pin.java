package es.ucm.fdi.devpins.data.model;

import java.util.ArrayList;

public class Pin {
    public static ArrayList<Pin> pinArrayList = new ArrayList<>();
    public static ArrayList<Pin> pinArrayFavList = new ArrayList<>();
    public static ArrayList<Pin> pinArrayListBasic = new ArrayList<>();
    public static ArrayList<Pin> pinArrayListYT = new ArrayList<>();
    public static ArrayList<Pin> pinArrayListPod = new ArrayList<>();
    private int id;
    private int user_id;
    private String url;
    private String type;
    private boolean fav;
    public static String PIN_EDIT_EXTRA = "";

    public Pin(int id, int user_id, String url, String type, boolean fav) {
        this.id = id;
        this.user_id = user_id;
        this.url = url;
        this.type = type;
        this.fav = fav;
    }

    public static Pin getPinFromId(int idToFind){
        for (Pin pin : pinArrayList){
            if(pin.getId() == idToFind) return pin;
        }
        return null;
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

    public String getType() { return type; }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }





}
