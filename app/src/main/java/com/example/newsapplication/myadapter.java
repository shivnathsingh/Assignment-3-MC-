package com.example.newsapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myholder> {
ArrayList<String> str;

    public myadapter(ArrayList<String> str) {
        this.str=str;
    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // Toast.makeText(parent.getContext(), "In my adapter class", Toast.LENGTH_SHORT).show();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_recycle, parent,false);
        return new myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, int position) {
     holder.name.setText(str.get(position));

    }

    @Override
    public int getItemCount() {
        return str.size();
    }

    public class myholder extends RecyclerView.ViewHolder
    {
        
        TextView name;
        public myholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);

        }

    }


}
