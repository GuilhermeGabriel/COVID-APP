package net.yan.covid.activity;

import android.os.Bundle;
import android.os.Handler;


import net.yan.covid.R;

import androidx.fragment.app.FragmentActivity;

public class AnimaActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anima);
//        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2500);
    }


}
