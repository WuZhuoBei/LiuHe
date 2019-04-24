package com.example.liuhe;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuhe.activity.CalculatorActivity;
import com.example.liuhe.activity.WebActivity;
import com.example.liuhe.dao.BookMarkDAO;
import com.example.liuhe.dao.BookMarkDAOFactory;
import com.example.liuhe.dialog.MyAddGridDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private SlidingPaneLayout sliding;
    private GridView gridview;
    private ImageView right_btn;
    private View inflate;
    private ListView popup_listview;
    private List<Map<String, Object>> gridNameKey;
    private SimpleAdapter gridAdapter;
    private PopupWindow pw;
    private ListView sliding_left_listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();
        //数据填充
        initData();
        //设置控件监听事件
        setListener();
    }

    private void setListener() {
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliding.openPane();
            }
        });
        //gridview点击监听事件
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                for(int j = 0; j< gridNameKey.size(); j++){
                    if(gridNameKey.get(i).get("name").toString().trim().equals(gridNameKey.get(j).get("name").toString().trim())){
                        intent.putExtra("url",gridNameKey.get(j).get("url").toString());
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
        //gridview长按事件
        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                BookMarkDAOFactory.getBookMark().doDelete(gridNameKey.get(i).get("id").toString());
                initData();
                return true;
            }
        });

        //点击加号监听事件
        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pw = new PopupWindow(inflate, 100, 80);
                pw.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                pw.setBackgroundDrawable(new BitmapDrawable());
                pw.setOutsideTouchable(true);
                pw.setFocusable(true);
                pw.setTouchable(true);
                pw.showAsDropDown(view, -50, 20);
            }
        });

        //popup对话框列表listview监听事件
        popup_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pw.dismiss();
                switch (i){
                    case 0:
                        MyAddGridDialog dialog = new MyAddGridDialog(MainActivity.this, getWindowManager().getDefaultDisplay().getWidth(), new MyAddGridDialog.OnMyDialogListener() {
                            @Override
                            public void back() {
                                initData();
                            }
                        });
                        dialog.show();
                        break;
                }
            }
        });

        //侧拉listview监听
        sliding_left_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        break;
                    case  1:
                        startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
                        break;
                    case 2:
                        break;
                }
            }
        });

    }

    private void initData() {
        //数据模拟
        //从sqlite获取数据   --主页网格数据
        gridNameKey = BookMarkDAOFactory.getBookMark().doFindAll();
        gridAdapter = new SimpleAdapter(App.appContext, gridNameKey,R.layout.item_grid, new String[]{"name"}, new int[]{R.id.item_grid_text});
        gridAdapter.notifyDataSetChanged();
        gridview.setAdapter(gridAdapter);


        //加号的listview数据模拟
        String[] strlist = new String[]{"添加","查询","收藏"};
        List<Map<String, Object>> popuplist = new ArrayList<>();
        for (int i = 0; i < strlist.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", strlist[i]);
            popuplist.add(map);
        }
        SimpleAdapter popupAdapter = new SimpleAdapter(App.appContext, popuplist, R.layout.item_list, new String[]{"text"}, new int[]{R.id.popup_list_text});
        popupAdapter.notifyDataSetChanged();
        popup_listview.setAdapter(popupAdapter);

        //侧拉栏数据模拟
        String[] leftstrlist = new String[]{"图库", "计算器", "记事本"};
        List<Map<String, Object>> slidinglist = new ArrayList<>();
        for (int i = 0; i < leftstrlist.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", leftstrlist[i]);
            slidinglist.add(map);
        }
        SimpleAdapter slidingAdapter = new SimpleAdapter(App.appContext, slidinglist, R.layout.item_list, new String[]{"text"}, new int[]{R.id.popup_list_text});
        slidingAdapter.notifyDataSetChanged();
        sliding_left_listview.setAdapter(slidingAdapter);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        sliding = (SlidingPaneLayout) findViewById(R.id.sliding);
        gridview = (GridView) findViewById(R.id.gridview);
        //按屏幕大小设置列数
        int number = (int) ((getResources().getDisplayMetrics().widthPixels) / 100);
        gridview.setNumColumns(number);
        right_btn = (ImageView) findViewById(R.id.right_btn);
        inflate = getLayoutInflater().inflate(R.layout.popup_dialog, null);
        popup_listview = (ListView)inflate.findViewById(R.id.popup_listview);
        sliding_left_listview = (ListView)findViewById(R.id.sliding_left_listview);
    }

    //添加一个链接
    public void addMeGrid(String name,String url){
        BookMarkDAOFactory.getBookMark().doCreate(name,url);
        initData();
    }
}
