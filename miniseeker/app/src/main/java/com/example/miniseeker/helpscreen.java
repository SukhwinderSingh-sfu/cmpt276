/*
helpscreen set the view for the activity_helpscreen. it also creates the hyperlinks for the resources used from internet and need to cite them.
 */
package com.example.miniseeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class helpscreen extends AppCompatActivity {

   TextView hyperlink;
   TextView hyperlink2;

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context,helpscreen.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpscreen);
        hyperlink = findViewById(R.id.texthyperlink);
        hyperlink.setMovementMethod(LinkMovementMethod.getInstance());
        hyperlink2 =findViewById(R.id.citation_box);
        hyperlink2.setMovementMethod(LinkMovementMethod.getInstance());


    }

}
