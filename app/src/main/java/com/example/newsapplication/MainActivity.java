package com.example.newsapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //private static ObjectOutputStream.PutField map;
    protected Button start,stop;
    protected RelativeLayout frame;
    private MyBatteryRecevier mbr;
    EditText edit;
    Button done;
    int number  =0;


    // Adding elements to the Map
    // using standard add() method


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(receiver, new IntentFilter(""));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, new IntentFilter("BroadCastMyData"));
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
       frame=(RelativeLayout)findViewById(R.id.frame);
       edit=(EditText)findViewById(R.id.edit) ;
       done=(Button)findViewById(R.id.submit) ;


        mbr=new MyBatteryRecevier(this);
        IntentFilter iF_1=new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
        registerReceiver(mbr,iF_1);
        IntentFilter iF_2=new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        registerReceiver(mbr,iF_2);
        IntentFilter iF_3=new IntentFilter("android.intent.action.ACTION_BATTERY_low");
        registerReceiver(mbr,iF_3);
        IntentFilter iF_4=new IntentFilter("android.intent.action.ACTION_BATTERY_OKAY");
        registerReceiver(mbr,iF_4);

    }





    @Override
    public void onClick(View view) {
        if(view==start) {

            startService(new Intent(this,Myservice.class));


        }

        else if(view==stop){
            stopService(new Intent(this,Myservice.class));

        }

    }
    public void start(){
        startService(new Intent(this,Myservice.class));
    }


    public void stop(){
        stopService(new Intent(this,Myservice.class));
    }


BroadcastReceiver receiver =new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        number=intent.getIntExtra("index", -1);
    }
};



  /*  public void top5recent(View view){
        Intent intentSecond=new Intent(MainActivity.this,secondActivity.class);
        Toast.makeText(this, "Second Activity start", Toast.LENGTH_SHORT).show();
        Log.i("second activity start ",""+number);
        intentSecond.putExtra("index",number);
        startActivity(intentSecond);
   }*/


    public void top5recent(View view) {
      //  Toast.makeText(this, "in show ", Toast.LENGTH_SHORT).show();
        Fragment3 fm =new Fragment3(number);
        FragmentTransaction tc =getSupportFragmentManager().beginTransaction();
        tc.replace(R.id.fragmentContainerView,fm);

        tc.addToBackStack(fm.getClass().getSimpleName());
        tc.commit();

    }
    public void fun(View view) {
       // Toast.makeText(this, "Click on news button", Toast.LENGTH_SHORT).show();
        TextView id=view.findViewById(R.id.name);
        String str=id.getText().toString();
        int number=-1;
        String[] arrOfStr = str.split("-", 2);
        number=Integer.parseInt(arrOfStr[1]);

        // getSupportFragmentManager().beginTransaction().replace(R.id.layout, new fragment4(number)).commit();


        fragment4 fm4 =new fragment4(number);
        FragmentTransaction tc=getSupportFragmentManager().beginTransaction();

        tc.replace(R.id.fragmentContainerView, fm4);
        //tc.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        tc.addToBackStack(null);

        tc.commit();
    }




}