package com.example.as2cmpt276;

import Model.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements jadapter.OnNoteListener {

    private  LensManager manager;
    String tt[];




    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = LensManager.getInstance();
        FloatingActionButton fb=findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent2=new Intent(MainActivity.this,Addactivity.class);
            startActivity(intent2);

            }
        });
        if (manager.size()==0)
            getstart();

        populatelistview();

    }
    private void getstart(){

        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));

    }
    private void populatelistview() {

        tt=new String[manager.size()];
        for (int i=0;i<manager.size();i++) {
            tt[i]=manager.get(i).toString();
        }



        RecyclerView list=(RecyclerView) findViewById(R.id.viewlist2);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new jadapter(tt,this));


    }
    @Override
    public void onNoteClick(int position) {

        Intent intent=dof_activity.makeIntent(MainActivity.this,position);
     startActivity(intent);

    }
}












