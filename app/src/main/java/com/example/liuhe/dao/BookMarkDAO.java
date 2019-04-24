package com.example.liuhe.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.liuhe.App;

public class BookMarkDAO {
    private  SQLiteDatabase db;
    private MyHelper myHelper;

    public BookMarkDAO() {
        myHelper = new MyHelper(App.appContext);
        db = myHelper.getReadableDatabase();
    }

    //插入一条信息
    public boolean bookMarkInsert(String name,String url){

        ContentValues value = new ContentValues();
        value.put("bookmarkname",name);
        value.put("bookmarkurl",url);
        long result = db.insert("bookMarkInfo", null, value);
        db.close();
        if(result>0){
            return true;
        }else {
            return false;
        }
    }
    //查询所有信息

    public Cursor bookMarkQuery(){
        Cursor cursor = db.query("bookMarkInfo", null, null, null, null, null, "_id asc");   //大到小order by _id desc
        return cursor;
    }

    //删除一条记录
    public boolean bookMarkDelete(String id){
        int result = db.delete("bookMarkInfo", "_id=?", new String[]{id});
        if(result>0){
            return true;
        }else {
            return false;
        }
    }
}
