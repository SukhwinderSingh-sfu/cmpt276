/*
this class to set the configuration of the game. Two Radiogroups are populated through this class. one for board size and another for the total number
of mines. when radiobuttons from both the groups are choosen it startactivity to mainmenu. passing the rows,columns and mines using instance object.
 */
package com.example.miniseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class options extends AppCompatActivity {

    int final_row;
    int final_column;
    int final_mines;

    private  static options instance;

     public options(){

    }

    public static options getInstance(){
        if(instance ==null){
            instance = new options();
        }
        return instance;
    }

    public static Intent makeIntent(Context context) {
         if(instance==null){
             instance = new options();
         }
        Intent intent =new Intent(context,options.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        
        createboardsizebuttons();
        createminebutton();

    }


    @SuppressLint("DefaultLocale")
    private void createboardsizebuttons() {  //radio buttons for board sizes
        RadioGroup group=findViewById(R.id.boardsize);
        final RadioGroup groupmine =findViewById(R.id.minesizes);
        int[] rows=getResources().getIntArray(R.array.rows);
        int[] columns=getResources().getIntArray(R.array.columns);

        for(int i=0;i<rows.length;i++){
            final int row=rows[i];
            final int column=columns[i];

            RadioButton button =new RadioButton(this);
            button.setText(String.format("%d%s%d%s", row, getString(R.string.option1), column, getString(R.string.optiontile)));
            group.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    instance.final_row=row;
                    instance.final_column=column;
                    if (groupmine.getCheckedRadioButtonId() != -1) {
                        Intent intent = Mainmenu.makeIntent(options.this, instance.final_row, instance.final_column, instance.final_mines);
                        startActivity(intent);
                    }

                }
            });
        }

    }

    @SuppressLint("DefaultLocale")
    private void createminebutton() { //radio buttons for number  of mines
        RadioGroup group =findViewById(R.id.minesizes);
        final RadioGroup groupboard = findViewById(R.id.boardsize);
        int[] mines=getResources().getIntArray(R.array.mines);
        for(int i=0;i<mines.length;i++){
            final int mine=mines[i];
            RadioButton button=new RadioButton(this);
            button.setText(String.format("%d%s", mine, getString(R.string.mineoptions)));
            group.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    instance.final_mines = mine;
                    if (groupboard.getCheckedRadioButtonId() != -1) {
                        Intent intent = Mainmenu.makeIntent(options.this, instance.final_row, instance.final_column, instance.final_mines);
                        startActivity(intent);
                    }
                }
            });
        }
    }

}
