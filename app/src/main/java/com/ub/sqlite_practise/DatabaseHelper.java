package com.ub.sqlite_practise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "PersonsDB";
    public static final String TABLE_NAME = "Persons";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADD = "address";
    private static final int DB_VERSION = 1;//to modify the database later on
    private SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);//creating database

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " VARCHAR, " + COLUMN_ADD + " VARCHAR);";//to create table into database
        db.execSQL(sql);//to run sql command
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Persons;";//to update new records or upgrade table
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addPerson(String name, String add) {
        SQLiteDatabase db = this.getWritableDatabase();//to write into database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);//to insert name into column
        contentValues.put(COLUMN_ADD, add);//to insert name into column
        long result=db.insert(TABLE_NAME, null, contentValues);//to insert values into table
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getPerson(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();//to read the data from database
        String sql = "SELECT * FROM Persons WHERE id=" + id + ";";//creating select query
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public int update_record(String id, String name, String add)
    {
        SQLiteDatabase db = this.getWritableDatabase();//to write into database
        ContentValues CV=new ContentValues();
        CV.put(DatabaseHelper.COLUMN_ID,id);
        CV.put(DatabaseHelper.COLUMN_NAME,name);
        CV.put(DatabaseHelper.COLUMN_ADD,add);
        return db.update("Persons",CV,"id = ? ",new String[]{id});
        //return true;
    }
    public int delete(String id)
    {
        //database=this.getWritableDatabase();
        //database.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.COLUMN_ID+" = "+id,null);
        SQLiteDatabase db = this.getWritableDatabase();//to write into database
        return db.delete("Persons","id = ? ",new String[]{id});
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+"Persons",null);
        return c;
    }
}
