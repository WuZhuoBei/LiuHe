package com.example.liuhe.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper( Context context) {
        super(context, "data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table bookMarkInfo(_id INTEGER PRIMARY key AUTOINCREMENT,bookmarkname varchar(100),bookmarkurl varchar(200))");
        db.execSQL("insert into bookMarkInfo(bookmarkname,bookmarkurl) values(?,?)",new String[]{"百度","http://www.baidu.com"});
        db.execSQL("insert into bookMarkInfo(bookmarkname,bookmarkurl) values(?,?)",new String[]{"百度1","http://www.baidu.com"});
        db.execSQL("insert into bookMarkInfo(bookmarkname,bookmarkurl) values(?,?)",new String[]{"百度2","http://www.baidu.com"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
