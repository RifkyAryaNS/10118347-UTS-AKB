package com.example.rifkyans10118347;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.wang.avi.AVLoadingIndicatorView;

public class SplashScreen extends AppCompatActivity {

    private LinearLayout splashscreen;
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashscreen = (LinearLayout) findViewById(R.id.splashscreen);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator("LineScalePulseOutRapidIndicator");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        },3000L); //3000L =  3detik
    }
}