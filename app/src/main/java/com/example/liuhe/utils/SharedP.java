package com.example.liuhe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.liuhe.App;

import java.util.ArrayList;
import java.util.List;

public class SharedP {
    private static SharedPreferences sp = App.appContext.getSharedPreferences("data", Context.MODE_PRIVATE);
    //保存字符串
    public static void saveSharendString(String name,String value){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name,value);
        editor.commit();
    }

    //获取字符串
    public static String getSharedString(String name,String value){
        return sp.getString(name,value);
    }

    //字符串数组保存
    public static void saveSharedList(String name, List values){
        SharedPreferences.Editor editor = sp.edit();
        String value = "";
        for(int i=0;i<values.size();i++){
            value+=values.get(i);
            value+="#";
        }
        editor.putString(name,value);
        editor.commit();
    }
    //字符串数组获取
    public static List getSharedList(String name , String value){
        String str = sp.getString(name, value);
        String[] strArray = str.split("#");
        List<String> list= new ArrayList<String>();
        for(int i=0;i<strArray.length;i++){
            list.add(strArray[i]);
        }
        return list;
    }
}
