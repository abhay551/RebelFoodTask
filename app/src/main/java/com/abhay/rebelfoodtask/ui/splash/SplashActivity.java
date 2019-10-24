package com.abhay.rebelfoodtask.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.abhay.rebelfoodtask.R;
import com.abhay.rebelfoodtask.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    public static final int SPLASH_TIME = 1000;
    private Context context;

    public static Intent newInstance(Context context){
        return new Intent(context,SplashActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.newInstance(context));
                finish();
            }
        },SPLASH_TIME);
    }
}
