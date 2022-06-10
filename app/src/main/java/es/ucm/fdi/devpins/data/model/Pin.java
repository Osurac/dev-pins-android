package es.ucm.fdi.devpins.data.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public String getUrlIdYoutube() {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url); //url is youtube url for which you want to extract the id.
        if (matcher.find()) {
            return matcher.group();
        }else{
            return "https://www.youtube.es";
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

}
