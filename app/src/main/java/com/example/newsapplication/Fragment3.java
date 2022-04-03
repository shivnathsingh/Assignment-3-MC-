package com.example.newsapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Fragment3 extends Fragment {
    int id;
    public Fragment3(int number) {
        // Required empty public constructor
        this.id=number;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    RecyclerView rview;
    ArrayList<String > str;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int i=0;
        int number =id;
        View view= inflater.inflate(R.layout.fragment_3, container, false);
        rview=view.findViewById(R.id.recycle);
        rview.setLayoutManager(new LinearLayoutManager(getContext()));
        //Toast.makeText(getContext().getApplicationContext(), "We are on frg3", Toast.LENGTH_SHORT).show();
        str =new ArrayList<String >();

        Log.i("NUMBER", ""+number);
       // rview.setLayoutManager(new LinearLayoutManager(getContext()));

        //str =new ArrayList<String >();
        while(i<=number)
        {
          //  Toast.makeText(getContext().getApplicationContext(), "We are on in fr3 loop", Toast.LENGTH_SHORT).show();
            String name1="News -"+i;
            str.add(name1);
            i++;
        }

        rview.setAdapter(new myadapter(str));



        return view;

    }


}