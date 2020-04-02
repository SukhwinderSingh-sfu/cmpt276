/*
This class will be called when game button will be pressed in Mainmenu layout. Firstly, it will get the total_rows, total_column and total_mines
using the intent calling intent. Then it will populate the n mines in the n random cells. it will also display the number of scan used and
how many mines revealed from the total mines. it will populate buttons as cells .
As the user press on the button it either display the number or the mine. if mine is found then mine count is increased. if on tapping unrevealed button
a number is found or on tapping mine without number; scan count is incremented by one. tapping on button with number or button with mine+number has no
effect. if the all the mines are found call to congratulation dialog is made.
 */
package com.example.miniseeker;
import Gamelogic.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class game extends AppCompatActivity {
    private int scan=0;
    private int minesfound=0;
    int total_rows;
     int total_columns;
     int total_mines;
    Button buttonarr[][];
    boolean b[][]; //array for the storing the mines.
    boolean minedisplayed[][]; //array to store whether mine is displayed or not.
    boolean numberdisplayed[][];//array to store which number are displayed.

    public static Intent makeIntent(Context context, int final_row, int final_column, int final_mines) {
        Intent intent= new Intent(context,game.class);
        intent.putExtra("final_row",final_row);
        intent.putExtra("final_column",final_column);
        intent.putExtra("final_mines",final_mines);
        return intent;

    }
    private void extractdata(){  //instead of using the default values should save the state.
        Intent intent =getIntent();
        total_rows=intent.getIntExtra("final_row",4);
        total_columns =intent.getIntExtra("final_column",6);
        total_mines=intent.getIntExtra("final_mines",6);

        /*
        code below is to make sure that when user go directly to play game without getting values from the
       user from the options screen. after the winning the game and dismissing the congratulation box
       he again play the game with getting the value from the user.
         */
        options.getInstance().final_row=total_rows;
        options.getInstance().final_column=total_columns;
        options.getInstance().final_mines=total_mines;



    }





    private void populateButtuons() {
        TableLayout table= findViewById(R.id.tableforgame);

        for(int i = 0; i < total_rows; i++){
            final int final_row=i;
            TableRow tableRow=new TableRow(this);
            tableRow.setLayoutParams(
                    new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,
                            1.0f)
            );

            table.addView(tableRow);

            for(int j = 0; j< total_columns; j++){

                final int final_column=j;
                Button button =new Button(this);
                buttonarr[i][j]=button;
                button.setLayoutParams(
                        new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,
                                1.0f)
                );




                button.setOnClickListener(new View.OnClickListener() { //when button is clicked
                    @Override
                    public void onClick(View v) {
                        gridbuttonclicked(final_row,final_column);  //function is called when the button is pressed.

                    }
                });

                tableRow.addView(button);
                
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void gridbuttonclicked(int x, int y) {
        final MediaPlayer mp= MediaPlayer.create(this,R.raw.clock);
        final MediaPlayer mpmine=MediaPlayer.create(this,R.raw.bomb);

          logic Logical1 = new logic();
          TextView scantext=findViewById(R.id.scan);
          TextView mfound=findViewById(R.id.minesfoundbox);
       if(!numberdisplayed[x][y]) {
           if (b[x][y] && !minedisplayed[x][y]) {
               mpmine.start();
               Handler myhandler = new Handler();
               myhandler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       mpmine.stop();
                   }
               },500);

           } else {
               mp.start();
               Animation anim = new AlphaAnimation(0.0f, 1.0f);
               anim.setDuration(500);
               anim.startNow();
               anim.setRepeatMode(Animation.REVERSE);
               anim.setRepeatCount(Animation.RESTART);
               for (int a = 0; a < total_columns; a++) {
                   buttonarr[x][a].setAnimation(anim);
               }
               for (int a = 0; a < total_rows; a++) {
                   buttonarr[a][y].setAnimation(anim);
               }
               Handler myhandler = new Handler();
               myhandler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       mp.stop();
                   }
               }, 500);
           }
       }

          if(b[x][y]) { //checks whether the given cell has mine.

              if(minedisplayed[x][y] && !numberdisplayed[x][y]){
                  scan++;
              }
              if(minedisplayed[x][y]){
                  numberdisplayed[x][y]=true;
              }
              minedisplayed[x][y]=true;
              lockButtonSizes();
              int newHeight = buttonarr[x][y].getHeight();
              int newWidth = buttonarr[x][y].getWidth();
              Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mini);
              Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
              Resources resources = getResources();
              buttonarr[x][y].setBackground(new BitmapDrawable(resources, scaledBitmap));
              for(int i=0;i<total_rows;i++) {
                  for (int j = 0; j < total_columns; j++) {
                      if(numberdisplayed[i][j]) {

                          int mine = Logical1.minesrowcolumn(b, minedisplayed, i, j, total_rows, total_columns);  //count the number of mines left.
                          buttonarr[i][j].setText("" + mine);
                          buttonarr[i][j].setPadding(0, 0, 0, 0);
                      }
                  }
              }
              if(!numberdisplayed[x][y]){
                  minesfound++;
              }
              scantext.setText(String.format("%s%d", getString(R.string.scantop), scan));
              mfound.setText(String.format("%d%s%d%s", minesfound, getString(R.string.of12), total_mines, getString(R.string.minw12)));
              if(minesfound==total_mines){//dialog button is called.

                  FragmentManager manager = getSupportFragmentManager();
                  dialogfragment dialog= new dialogfragment();
                  dialog.show(manager,"CongratulationDialog");

              }

          }
          else {  //should run for the all the displayed button whenever button is pressed.
              if(!numberdisplayed[x][y]){
                  scan++;
              }
              numberdisplayed[x][y]=true;

              lockButtonSizes();
              for(int i=0;i<total_rows;i++) {
                  for (int j = 0; j < total_columns; j++) {
                      if(numberdisplayed[i][j]) {

                          int mine = Logical1.minesrowcolumn(b, minedisplayed, i, j, total_rows, total_columns);  //count the number of mines left.
                          buttonarr[i][j].setText("" + mine);
                          buttonarr[i][j].setPadding(0, 0, 0, 0);
                      }
                  }
              }
              scantext.setText(String.format("%s%d", getString(R.string.scan12), scan));
          }


        //Toast.makeText(this,"scanning"+((x+1)*(y+1)),Toast.LENGTH_SHORT).show();
    }

    private void lockButtonSizes() {
        for(int i=0;i<total_rows;i++){
            for(int j = 0; j< total_columns; j++){
                int width=buttonarr[i][j].getWidth();
                int height=buttonarr[i][j].getHeight();
                buttonarr[i][j].setMinWidth(width);
                buttonarr[i][j].setMaxWidth(width);
                buttonarr[i][j].setMinHeight(height);
                buttonarr[i][j].setMaxHeight(height);

            }
        }
    }
    private void populateminedisplayed() {
        for (int i=0;i<total_rows;i++){
            for(int j = 0; j< total_columns; j++){
                minedisplayed[i][j]=false;
            }
        }
    }

    private void populatenumberdisplayed() {
        for (int i=0;i<total_rows;i++){
            for(int j = 0; j< total_columns; j++){
                numberdisplayed[i][j]=false;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView scantext =  findViewById(R.id.scan);
        TextView mfound=findViewById(R.id.minesfoundbox);

        getSupportActionBar().hide();
        logic Logic=new logic();
        extractdata();
        buttonarr=new Button[total_rows][total_columns];
        minedisplayed=new boolean[total_rows][total_columns];
        numberdisplayed=new boolean[total_rows][total_columns];
        b=Logic.distributemines(total_rows, total_columns,total_mines);   //this will populate n mines in random cells.
        populateminedisplayed();
        populatenumberdisplayed();
        scantext.setText(String.format("%s%d", getString(R.string.scan_used1), scan));
        mfound.setText(String.format("%d%s%d%s", minesfound, getString(R.string.of1), total_mines, getString(R.string.minw1)));
        populateButtuons();


        //code here
    }


}
