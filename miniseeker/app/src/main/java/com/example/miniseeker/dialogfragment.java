/*
this is a fragment class for the congratulation dialog.  when the all the mines are found; "game" class will create new object of this class
and will call <>.show() function to show the dialog. on user pressing ok , intent is made to Mainmenu.

*/

package com.example.miniseeker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

public class dialogfragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.congratulation_dialog,null);
        DialogInterface.OnClickListener listener =new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

             Intent intent = Mainmenu.makeIntent(getActivity(),options.getInstance().final_row,options.getInstance().final_column,options.
                     getInstance().final_mines);
             startActivity(intent);
            }
        };
        return new AlertDialog.Builder(getActivity())
                .setTitle("Congratulation Box")
                .setView(v)
                .setPositiveButton(android.R.string.ok,listener)
                .create();
    }

}
