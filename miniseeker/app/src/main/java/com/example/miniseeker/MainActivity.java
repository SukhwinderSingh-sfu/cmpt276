/*
this class is for welcome screen. when the app is started, class is made to this class. This set the view from the R.layout.welcomescreen.

after pause for  5 sec this class make intent to the mainmenu and startactivity through this intent.
 */

package com.example.miniseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen);

        getSupportActionBar().hide();

        Button button = findViewById(R.id.skip);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=Mainmenu.makeIntent(MainActivity.this);
                startActivity(intent);

            }
        });

        Handler myhandler = new Handler();
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=Mainmenu.makeIntent(MainActivity.this);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_top,R.anim.slide_out_bottom);
            }
        },5000);
        //super code here
    }

}
