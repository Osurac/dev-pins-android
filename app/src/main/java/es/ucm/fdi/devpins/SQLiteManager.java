package es.ucm.fdi.devpins;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import es.ucm.fdi.devpins.data.model.Pin;
import es.ucm.fdi.devpins.data.model.User;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqlIteManager;
    private static final String DATABASE_NAME ="dev_pins_db";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE_NAME ="users";
    private static final String PIN_TABLE_NAME ="pins";
    private static final String USER_COUNTER ="UserCounter";
    private static final String PIN_COUNTER ="PinCounter";

    private static final String USER_ID_FIELD ="id";
    private static final String USER_EMAIL_FIELD ="email";
    private static final String USER_PASSWORD_FIELD ="password";

    private static final String PIN_ID_FIELD ="id";
    private static final String PIN_URL_FIELD ="url";
    private static final String PIN_FAV_FIELD ="fav";
    private static final String PIN_TYPE_FIELD ="type";



    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context){
        if(sqlIteManager == null){
            sqlIteManager = new SQLiteManager(context);
        }
        return sqlIteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      createUserDatabase(sqLiteDatabase);
      createPinDatabase(sqLiteDatabase);
    }

    private void createPinDatabase(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(PIN_TABLE_NAME)
                .append("(")
                .append(PIN_COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(PIN_ID_FIELD)
                .append(" TEXT, ")
                .append(PIN_URL_FIELD)
                .append(" TEXT, ")
                .append(PIN_FAV_FIELD)
                .append(" BOOLEAN)");
        sqLiteDatabase.execSQL(sql.toString());
    }

    private void createUserDatabase(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(USER_TABLE_NAME)
                .append("(")
                .append(USER_COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(USER_ID_FIELD)
                .append(" INT, ")
                .append(USER_EMAIL_FIELD)
                .append(" TEXT, ")
                .append(USER_PASSWORD_FIELD)
                .append(" TEXT)");
        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUserToDatabase(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID_FIELD, user.getId());
        contentValues.put(USER_EMAIL_FIELD, user.getEmail());
        contentValues.put(USER_PASSWORD_FIELD, user.getPassword());
        sqLiteDatabase.insert(USER_TABLE_NAME, null, contentValues);
    }

    public void addPinToDatabase(Pin pin) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PIN_ID_FIELD, pin.getId());
        contentValues.put(PIN_URL_FIELD, pin.getUrl());
        contentValues.put(PIN_FAV_FIELD, pin.getFav());
        sqLiteDatabase.insert(PIN_TABLE_NAME, null, contentValues);
    }

    public void updatePinInDatabase(Pin pin){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PIN_ID_FIELD, pin.getId());
        contentValues.put(PIN_URL_FIELD, pin.getUrl());
        contentValues.put(PIN_FAV_FIELD, pin.getFav());
        sqLiteDatabase.update(PIN_TABLE_NAME, contentValues, PIN_ID_FIELD+" =?", new String[]{String.valueOf(pin.getId())});
    }

    public void populatePinListArray(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Pin.pinArrayList.clear();
        try(Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+PIN_TABLE_NAME, null)){
            if(result.getCount() != 0){
                while(result.moveToNext()){
                    int id = result.getInt(1);
                    String url = result.getString(2);
                    String fav = result.getString(3);
                    Pin pin = new Pin(id, url,  Boolean.parseBoolean(fav));
                    Pin.pinArrayList.add(pin);
                }
            }
        }
    }

    public int getLastUserId(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int id = 0;
        try(Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+USER_TABLE_NAME+" ORDER BY id DESC LIMIT 1", null)){
            if(result.getCount() != 0){
                while(result.moveToNext()) {
                    id = result.getInt(1);
                }
            }
        }
        return id;
    }

}