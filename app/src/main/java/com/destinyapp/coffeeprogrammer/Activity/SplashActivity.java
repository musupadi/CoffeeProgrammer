package com.destinyapp.coffeeprogrammer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.destinyapp.coffeeprogrammer.Activity.Login.LoginActivity;
import com.destinyapp.coffeeprogrammer.Activity.Main.MainActivity;
import com.destinyapp.coffeeprogrammer.R;

public class SplashActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Handler handler = new Handler();
        lottieAnimationView=findViewById(R.id.lottie);
        lottieAnimationView.setAnimation("coffe.json");
        handler.postDelayed(new Runnable() {
            public void run() {
                changeActivity();
            }
        }, 3000); //3000 L = 3 detik
    }
    private void changeActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}