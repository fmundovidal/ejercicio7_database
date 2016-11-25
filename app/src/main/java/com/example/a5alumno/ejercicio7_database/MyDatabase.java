package com.example.a5alumno.ejercicio7_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by A5Alumno on 25/11/2016.
 */
public class MyDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Another_database";
    public static final String TABLE_NAME = "My_first_table";
    public static final String KEY_WORD = "Nom";
    public static final String KEY_DESCRIPTION = "Cognom";

    private static final String DATABASE_CREATE_COMMAND = "CREATE TABLE "+MyDatabase.TABLE_NAME + " ("+MyDatabase.KEY_WORD +
            " TEXT, "+MyDatabase.KEY_DESCRIPTION + " TEXT);";

    public MyDatabase(Context context){
        super(context,MyDatabase.DATABASE_NAME, null, MyDatabase.DATABASE_VERSION);
    }

    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MyDatabase.DATABASE_CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
