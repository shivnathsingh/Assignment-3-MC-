package com.example.newsapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;


public class fragment extends Fragment {
    protected String str;
    private TextView title;
    private TextView body;
    private TextView url_of_image;
    private ImageView img;

    // private TextView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment, container, false);
        //LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, new IntentFilter("BroadCastMyData"));
        title = view.findViewById(R.id.fragtitle);
        body = view.findViewById(R.id.fragbody);
        url_of_image = view.findViewById(R.id.fragurl);
        img = view.findViewById(R.id.image);
       // Toast.makeText(getContext().getApplicationContext(), "We are on frg2", Toast.LENGTH_SHORT).show();

        return view;
    }

    BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String tv1=intent.getStringExtra("key1");
            title.setText("Title :- "+tv1);
            String tv2=intent.getStringExtra("key2");
            body.setText("Body :- "+tv2);
            String tv3=intent.getStringExtra("key3");
            url_of_image.setText("Url :- "+tv3);
            Glide.with(context).load(tv3).override(600,650).into(img);
            Log.i("recevier ti ", String.valueOf(title));
            Log.i("reciver body ", String.valueOf(body));
            Log.i("Reciver Url ", String.valueOf(url_of_image));

        }
    };


    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, new IntentFilter("BroadCastMyData"));
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);
    }
}