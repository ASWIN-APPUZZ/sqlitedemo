package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {

    DBHelper dbHelper;
    SQLiteDatabase database;
    Context context;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open(){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void Close(){
        dbHelper.close();
    }

    public void Insert(String country, String currency) {
        ContentValues values = new ContentValues();
        values.put(DBHelper._NAME, country);
        values.put(DBHelper._CURRENCY, currency);

        database.insert(DBHelper.TABLE_NAME, null, values);
        Log.d("Database Inserted", "**********************************");
    }
        public Cursor fetch(){
            String [] columns = new String[] {
                    DBHelper._ID,
                    DBHelper._NAME,
                    DBHelper._CURRENCY
            };
            Cursor query = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);

            if (query!= null){
                query.moveToFirst();
            }
            return query;

        }
    }

