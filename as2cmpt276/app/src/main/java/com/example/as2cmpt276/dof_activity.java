package com.example.as2cmpt276;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

import Model.*;

public class dof_activity extends AppCompatActivity {
    LensManager manager=LensManager.getInstance();



    public static Intent makeIntent(Context context, int choice) {
        Intent intent=new Intent(context,dof_activity.class);
        intent.putExtra("Choice",choice);
         return intent;
    }

    private int extractdatafromintent() {
        Intent intent=getIntent();
        int choice;
        choice =intent.getIntExtra("Choice",0);
        return choice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dof_activity);
        final int choice=extractdatafromintent();
        TextView t1=findViewById(R.id.textViewmiddle);
        t1.setText("Photo details with  "+manager.get(choice).toString());
        final EditText bottom12=findViewById(R.id.bottom12);
        final EditText bottom22=findViewById(R.id.bottom22);
        final EditText bottom32=findViewById(R.id.bottom32);
        Button bottomlast=findViewById(R.id.bottomlast);
        bottom12.addTextChangedListener(checker);
        bottom22.addTextChangedListener(checker);
        bottom32.addTextChangedListener(checker);
        bottomlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a=Double.parseDouble(bottom32.getText().toString());
                double b=Double.parseDouble(bottom22.getText().toString())*1000;
                double coc=Double.parseDouble(bottom12.getText().toString());
                Depth_of_field_calculator dof=new Depth_of_field_calculator(manager.getfocus(choice),a,b,coc);
                TextView t1=findViewById(R.id.t1);
                TextView t2=findViewById(R.id.t2);
                TextView t3=findViewById(R.id.t3);
                TextView t4=findViewById(R.id.t4);
                double p1=dof.getHyperfocal_distance()/1000;
                double p2=dof.getNearfocal_point()/1000;
                double p3=dof.getFarfocal_point();
                double p4=dof.getDepth_of_field()/1000;
                t3.setText(formatM(p1)+" m");
                t1.setText(formatM(p2)+" m");
                t4.setText(formatM(p3)+" m");
                t2.setText(formatM(p4)+" m");


            }
        });




    }
    private TextWatcher checker=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            EditText bottom12 = findViewById(R.id.bottom12);
            EditText bottom22 = findViewById(R.id.bottom22);
            EditText bottom32 = findViewById(R.id.bottom32);
            Button bottomlast = findViewById(R.id.bottomlast);
            String S1=bottom12.getText().toString();
            String S2=bottom22.getText().toString();
            bottomlast.setEnabled(true);
            if(S1.isEmpty()){
                bottomlast.setEnabled(false);
            }
            else{
                if(Double.parseDouble(S1)<=0){
                    bottomlast.setEnabled(false);
                }

            }

            if(S2.isEmpty()){
                bottomlast.setEnabled(false);
            }
            else{
                if(Double.parseDouble(S2)<=0){
                    bottomlast.setEnabled(false);
                }

            }


            if (!bottom32.getText().toString().isEmpty()) {
                double dp = Double.parseDouble(bottom32.getText().toString());
                if (dp < manager.getmaxaperture(extractdatafromintent())) {
                    bottomlast.setEnabled(false);
                }

            }
            else{
                bottomlast.setEnabled(false);
            }


        }
        @Override
        public void afterTextChanged(Editable s) {

        }


    };
    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}
