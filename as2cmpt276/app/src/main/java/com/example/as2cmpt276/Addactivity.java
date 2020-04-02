package com.example.as2cmpt276;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Model.Lens;
import Model.LensManager;

public class Addactivity extends AppCompatActivity {
    private LensManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);
        final EditText tr1 = findViewById(R.id.tr1);
        final EditText tr2 = findViewById(R.id.tr2);
        final EditText tr3 = findViewById(R.id.tr3);
        Button bb1 = findViewById(R.id.bb1);
        tr1.addTextChangedListener(checker);
        tr2.addTextChangedListener(checker);
        tr3.addTextChangedListener(checker);
        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = LensManager.getInstance();
                manager.add(new Lens(tr1.getText().toString(), Double.parseDouble(tr3.getText().toString()),
                        Integer.parseInt(tr2.getText().toString())));
                Intent intent3 = new Intent(Addactivity.this, MainActivity.class);
                startActivity(intent3);
            }

        });
        Button bb2 = findViewById(R.id.bb2);
        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Addactivity.this, MainActivity.class);
                startActivity(intent3);

            }
        });
    }

    private TextWatcher checker = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            EditText tr1 = findViewById(R.id.tr1);
            EditText tr2 = findViewById(R.id.tr2);
            EditText tr3 = findViewById(R.id.tr3);
            Button bb1 = findViewById(R.id.bb1);
            String s1 = tr1.getText().toString();
            String s2 = tr2.getText().toString();
            String s3 = tr3.getText().toString();
            bb1.setEnabled(true);
            if (s1.isEmpty()) {
                bb1.setEnabled(false);
            }
            if (s2.isEmpty()) {
                bb1.setEnabled(false);
            } else {
                if (Double.parseDouble(s2) <= 0) {
                    bb1.setEnabled(false);
                }
            }
            if (s3.isEmpty()) {
                bb1.setEnabled(false);
            } else {
                if (Double.parseDouble(s3) < 1.4) {
                    bb1.setEnabled(false);
                }

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
