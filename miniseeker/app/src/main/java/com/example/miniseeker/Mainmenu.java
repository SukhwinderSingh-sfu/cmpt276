/*
this class deal with mainmenu show after the welcome screen. it displays the three buttons . 1- game. 2-options. 3- helpscreen.
on pressing the game button it takes to the game play where for default total_row=4, total_column=6, total_mines= 6 is set.
on pressing the button 2 . take user to options screen for setting the configuration of the game.
button 3- will take to helpscreen.
 */
package com.example.miniseeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainmenu extends AppCompatActivity {
    int final_row;
    int final_column;
    int final_mines;

    public static Intent makeIntent(Context context, int final_row, int final_column, int final_mines) {
        Intent intent = new Intent(context,Mainmenu.class);
        intent.putExtra("final_row",final_row);
        intent.putExtra("final_column",final_column);
        intent.putExtra("final_mines",final_mines);
        return intent;
    }
    public static Intent makeIntent(Context context){
        Intent intent =new Intent(context,Mainmenu.class);
        return intent;
    }
    private void extractdata(){
        Intent intent =getIntent();
        final_row=intent.getIntExtra("final_row",4);
        final_column=intent.getIntExtra("final_column",6);
        final_mines=intent.getIntExtra("final_mines",6);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        Button game1=findViewById(R.id.game);
        Button options1=findViewById(R.id.options);
        Button help_screen=findViewById(R.id.help_screen);
        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractdata();
                Intent intent= game.makeIntent(Mainmenu.this,final_row,final_column,final_mines);
                startActivity(intent);
            }
        });
        options1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = options.makeIntent(Mainmenu.this);
                startActivity(intent);
            }
        });
        help_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =helpscreen.makeIntent(Mainmenu.this);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed(){
        finishAffinity();

    }


}
