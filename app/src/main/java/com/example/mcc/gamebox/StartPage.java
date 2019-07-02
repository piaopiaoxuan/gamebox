package com.example.mcc.gamebox;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartPage extends AppCompatActivity {
//该Activity用作启动页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        //显示0.1秒后关闭此Activity，并跳转到 MainActivity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartPage.this,MainActivity.class));
                finish();
            }
        },100);
    }

    //禁用返回键
    @Override
    public void onBackPressed(){
        //null
    }

}
