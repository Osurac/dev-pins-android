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

    /**
     * Constructor pin
     * @param id
     * @param user_id
     * @param url
     * @param type
     * @param fav
     */
    public Pin(int id, int user_id, String url, String type, boolean fav) {
        this.id = id;
        this.user_id = user_id;
        this.url = url;
        this.type = type;
        this.fav = fav;
    }

    /**
     * Function que retorna un pin por su id
     * @param idToFind
     * @return
     */
    public static Pin getPinFromId(int idToFind){
        for (Pin pin : pinArrayList){
            if(pin.getId() == idToFind) return pin;
        }
        return null;
    }

    /**
     * función get de id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * función get de user_id
     * @return
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * función get de fav
     * @return
     */
    public boolean getFav() {
        return fav;
    }

    /**
     * función get de type
     * @return
     */
    public String getType() { return type; }

    /**
     * función set de id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * función get de url
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Functión que devuelve la id de un video de youtube
     * @return
     */
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

    /**
     * Función set de url
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Función set de fav
     * @param fav
     */
    public void setFav(boolean fav) {
        this.fav = fav;
    }

}
