package com.example.liuhe.dao;

import android.database.Cursor;
import android.widget.Toast;

import com.example.liuhe.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookMarkDAOProxy {
    private BookMarkDAO dao;

    public BookMarkDAOProxy() {
        this.dao = new BookMarkDAO();
    }

    //创建
        public List doFindAll(){
            ArrayList<Map<String, Object>> gridNameKey = new ArrayList<Map<String, Object>>();
            Cursor cursor = dao.bookMarkQuery();

            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("id",cursor.getInt(0));
                    map.put("name",cursor.getString(1));
                    map.put("url",cursor.getString(2));
                    gridNameKey.add(map);
                }
            }
            cursor.close();
            return gridNameKey;
        }
    //查询
    public void doCreate(String name,String url){
        if(dao.bookMarkInsert(name,url)){

        }else {
            Toast.makeText(App.appContext,"出错",Toast.LENGTH_SHORT).show();
        }
    }
    //删除
    public void doDelete(String id){
        dao.bookMarkDelete(id);
    }
}
