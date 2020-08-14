package com.destinyapp.coffeeprogrammer.SharedPreferance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "coffee_programmer.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "session";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    public DB_Helper(Context context){super(
            context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (" +
                COLUMN_ID+" TEXT PRIMARY KEY, "+
                COLUMN_NAME+" TEXT NOT NULL);"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void saveSession(String ID,String nama){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, ID);
        values.put(COLUMN_NAME,nama);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public Cursor checkSession(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME+"";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public void userLogout(String username,Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+"");
        Toast.makeText(context, "Logout Berhasil", Toast.LENGTH_SHORT).show();
    }
}
