package com.example.liuhe.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuhe.App;
import com.example.liuhe.MainActivity;
import com.example.liuhe.R;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome_activity extends AppCompatActivity {
    private TextView times;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            times.setText(""+msg.arg1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_activity);

        times = (TextView)findViewById(R.id.time_s);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=4;i++){
                    if(i<4){
                        Message msg = Message.obtain();
                        msg.arg1 = i;
                        mHandler.sendMessage(msg);
                    }else {
                        startActivity(new Intent(Welcome_activity.this, MainActivity.class));
                        finish();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
