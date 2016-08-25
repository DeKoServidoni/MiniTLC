package com.dekoservidoni.minitlc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.dekoservidoni.minitlc.utils.AppConstants;

/**
 * Initial screen of the application.
 *
 * Created by DeKoServidoni on 2/21/16.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivity = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(mainActivity);
                finish();
            }
        }, AppConstants.SPLASH_TIMEOUT);
    }
}
