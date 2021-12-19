package com.kkapp.hook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    // 这个方法比onCreate调用早; 在这里Hook比较好.
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HookHelper.hookActivityManager(this);
    }

    public void onHookTest(View view){
        HLog.d(TAG, "onHookTest");
        // 测试AMS HOOK (调用其相关方法)
        Uri uri = Uri.parse("http://wwww.baidu.com");
        Intent t = new Intent(Intent.ACTION_VIEW);
        t.setData(uri);
        startActivity(t);
    }
}