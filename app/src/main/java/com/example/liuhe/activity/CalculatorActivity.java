package com.example.liuhe.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.liuhe.R;
import com.example.liuhe.adapter.ViewPagerAdapter;
import com.example.liuhe.fragment.ViewpagerFragment1;
import com.example.liuhe.fragment.ViewpagerFragment2;
import com.example.liuhe.fragment.ViewpagerFragment3;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {

    private BottomNavigationView calculator_bottom;
    private ViewPager calculator_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initView();
        initData();
        setListener();
    }

    private void setListener() {
        //按钮组监听
        calculator_bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation1:
                        calculator_viewpager.setCurrentItem(0);
                        return true;
                    case R.id.navigation2:
                        calculator_viewpager.setCurrentItem(1);
                        return true;
                    case R.id.navigation3:
                        calculator_viewpager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        //viewpager监听
        calculator_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                switch (i){
                    case 0:
                        calculator_bottom.getMenu().findItem(R.id.navigation1).setChecked(true);
                        break;
                    case 1:
                        calculator_bottom.getMenu().findItem(R.id.navigation2).setChecked(true);
                        break;
                    case 2:
                        calculator_bottom.getMenu().findItem(R.id.navigation3).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initData() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new ViewpagerFragment1());
        fragments.add(new ViewpagerFragment2());
        fragments.add(new ViewpagerFragment3());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        adapter.notifyDataSetChanged();
        calculator_viewpager.setAdapter(adapter);
    }

    private void initView() {
        calculator_bottom = (BottomNavigationView) findViewById(R.id.calculator_bottom);
        calculator_viewpager = (ViewPager) findViewById(R.id.calculator_viewpager);
    }
}
