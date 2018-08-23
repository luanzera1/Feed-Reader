package com.luanzera.feedreaderapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen splashScreen = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#1087ff"))
                .withLogo(R.drawable.icon)
                .withBeforeLogoText("Tecmundo Reader")
                .withFooterText("Desenvolvido por luanzera");

        splashScreen.getBeforeLogoTextView().setTextColor(Color.WHITE);
        splashScreen.getFooterTextView().setTextColor(Color.WHITE);

        View view = splashScreen.create();
        setContentView(view);

    }
}
