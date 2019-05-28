package com.example.alltimeline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class DBHelper implements BaseColumns {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private static final String _TABLENAME0 = "category";
    private static final String _TABLENAME1 = "event";

    // Category Table Column
    private static final String CATEGORY_NAME = "category_name";
    private static final String TYPE = "type";

    // Event Table Column
    private static final String CATEGORY_ID = "category_id";
    private static final String EVENT_NAME = "event_name";
    private static final String YEAR = "year";
    private static final String MEMO = "memo";

    private static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
            +_ID+" integer primary key autoincrement, "
            +CATEGORY_NAME+" text not null, "
            +TYPE+" integer not null );";

    private static final String _CREATE1 = "create table if not exists "+_TABLENAME1+"("
            +_ID+" integer primary key autoincrement, "
            +CATEGORY_ID+" integer not null , "
            +EVENT_NAME+" text not null , "
            +YEAR+" integer not null , "
            +MEMO+" text not null );";

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(_CREATE0);
            db.execSQL(_CREATE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+_TABLENAME0);
            db.execSQL("DROP TABLE IF EXISTS "+_TABLENAME1);
            onCreate(db);
        }
    }

    public DBHelper(Context context){
        this.mCtx = context;
    }

    public DBHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }

    // Insert DB
    public long insertCategory(String name, int type){
        ContentValues values = new ContentValues();
        values.put(CATEGORY_NAME, name);
        values.put(TYPE, type);
        return mDB.insert(_TABLENAME0, null, values);
    }

    public long insertEvent(int category_id, String name, int year, String memo){
        ContentValues values = new ContentValues();
        values.put(CATEGORY_ID, category_id);
        values.put(EVENT_NAME, name);
        values.put(YEAR, year);
        values.put(MEMO, memo);
        return mDB.insert(_TABLENAME1, null, values);
    }

    // Update DB
    public boolean updateEvent(long id, String name, int year, String memo){
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, name);
        values.put(YEAR, year);
        values.put(MEMO, memo);
        return mDB.update(_TABLENAME1, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllEvents(long c_id) {
        mDB.delete(_TABLENAME1, "category_id="+c_id, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(_TABLENAME0, "_id="+id, null) > 0;
    }

    // Select DB
    public Category selectCategory(long id) {
        String sql = "select * from " + _TABLENAME0 + " where _id = '" + id + "';";
        Cursor results = mDB.rawQuery(sql, null);

        results.moveToFirst();
        Category item = new Category(results.getInt(0), results.getString(1), results.getInt(2));
        results.close();
        return item;
    }

    public Event selectEvent(long id) {
        String sql = "select * from " + _TABLENAME1 + " where _id = '" + id + "';";
        Cursor results = mDB.rawQuery(sql, null);

        results.moveToFirst();
        Event item = new Event(results.getInt(0), results.getInt(1), results.getString(2),
                results.getInt(3), results.getString(4));
        results.close();
        return item;
    }

    public ArrayList<Category> selectAll() {
        String sql = "select * from " + _TABLENAME0 + " ORDER BY CATEGORY_NAME;";
        Cursor results = mDB.rawQuery(sql, null);

        results.moveToFirst();
        ArrayList<Category> data = new ArrayList<>();

        while (!results.isAfterLast()) {
            Category item = new Category(results.getInt(0), results.getString(1),
                    results.getInt(2));
            data.add(item);
            results.moveToNext();
        }
        results.close();
        return data;
    }

    public ArrayList<Event> selectAllEvent(long c_id) {
        String sql = "select * from " + _TABLENAME1 + " where CATEGORY_ID = '" + c_id + "' ORDER BY YEAR;";
        Cursor results = mDB.rawQuery(sql, null);

        results.moveToFirst();
        ArrayList<Event> data = new ArrayList<>();

        while (!results.isAfterLast()) {
            Event item = new Event(results.getInt(0), results.getInt(1), results.getString(2),
                    results.getInt(3), results.getString(4));
            data.add(item);
            results.moveToNext();
        }
        results.close();
        return data;
    }

    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }
}
