package com.example.easyorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity implements Animation.AnimationListener {
    ImageView logo, logoImg;
    TextView eslogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        logo=findViewById(R.id.logo);
        Animation animacionLogo= AnimationUtils.loadAnimation(this, R.anim.logo);
        logo.startAnimation(animacionLogo);
        logoImg=findViewById(R.id.logoImg);
        Animation animacionLogoImg= AnimationUtils.loadAnimation(this, R.anim.scale);
        logoImg.startAnimation(animacionLogoImg);
        eslogan=findViewById(R.id.eslogan);
        Animation animacionEslogan= AnimationUtils.loadAnimation(this, R.anim.eslogan);
        eslogan.startAnimation(animacionEslogan);


        animacionLogo.setAnimationListener(this);

    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent index = new Intent(Splash.this, MainActivity.class);
        startActivity(index);
        finish();

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
