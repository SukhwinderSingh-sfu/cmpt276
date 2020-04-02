package com.example.as2cmpt276;
/*for recycleview list reference is taken from tutorial on youtube.
   Video Name:Android RecyclerView Tutorial - Working Example In Hindi | Cheezy Code Hindi
   Video Link:https://youtu.be/IGGT_jfZQrA


*  */
/*
for implementing the clicks on the recyleview  list reference has been taken from tutorial on youtube.
Video Name: RecyclerView OnClickListener (Best practice way)
Video Link :https://youtu.be/69C1ljfDvl0
 */

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class jadapter extends RecyclerView.Adapter<jadapter.vholder> {
    private String[] data;
    private OnNoteListener monNoteListener;

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

    public jadapter(String[] data,OnNoteListener monNoteListener){
        this.monNoteListener=monNoteListener;
        this.data=data;

    }

    @Override
    public vholder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater flate= LayoutInflater.from(parent.getContext());
        View view=flate.inflate(R.layout.front,parent,false);
        return new vholder(view,monNoteListener);
    }

    @Override
    public void onBindViewHolder( vholder holder, int position) {
        String title =data[position];
        holder.txxt.setText(title);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class vholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iconimg;
        TextView txxt;
        OnNoteListener onNoteLister;
        public vholder( View itemView,OnNoteListener  onNoteLister) {
            super(itemView);
            iconimg = itemView.findViewById(R.id.iconimg);
            txxt=itemView.findViewById(R.id.textView7);
            this.onNoteLister=onNoteLister;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteLister.onNoteClick(getAdapterPosition());

        }
    }
}
