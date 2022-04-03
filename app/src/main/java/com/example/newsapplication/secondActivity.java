package com.example.newsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class secondActivity extends AppCompatActivity {

    TextView tv1,tv11,tv2,tv22,tv3,tv33,tv4,tv44,tv5,tv55;
    ImageView image;
    String url="";
    int number;
    MainActivity mn;
    FragmentManager manager;

    //Fragment3 fm;
    FragmentTransaction tc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       /* tv1=findViewById(R.id.textView1);
        //tv11=findViewById(R.id.textView11);
        tv2=findViewById(R.id.textView2);
        //tv22=findViewById(R.id.textView22);
        tv3=findViewById(R.id.textVie36);
        //tv33=findViewById(R.id.textView33);
        tv4=findViewById(R.id.textView4);
        //tv44=findViewById(R.id.textView44);
        tv5=findViewById(R.id.textView5);
        //tv55=findViewById(R.id.textView55);

        //image=findViewById(R.id.imageView);*/
        Toast.makeText(secondActivity.this, "We are on 2nd activity", Toast.LENGTH_SHORT).show();
        mn=new MainActivity();
        Intent intent=getIntent();
        number=intent.getIntExtra("index",-1);
        if(number!=-1)
            show();
        else
            Log.i("second activity wrong",""+number);

    }
    public void show(){



//getSupportFragmentManager().beginTransaction().replace(R.id.layout, new Fragment3()).commit();
Toast.makeText(this, "in show ", Toast.LENGTH_SHORT).show();
        Fragment3 fm =new Fragment3(number);
        manager=getSupportFragmentManager();
        tc=manager.beginTransaction();
       // Bundle data = new Bundle();
       // data.putInt("Num",number);
       // fm.setArguments(data);
        tc.replace(R.id.layout,fm);

        tc.addToBackStack(fm.getClass().getSimpleName());
        tc.commit();


    }
 /*   public void help(File file,TextView tv1){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            String ans = stringBuilder.toString();
            JSONObject jsnObject = new JSONObject(ans);
            String title = jsnObject.getString("title");
            //String data = jsnObject.getString("body");
            //url = jsnObject.getString("image-url");

            Log.i("img-url", url);
            tv1.setText("---***Title***---"+"\n"+title+"\n\n");
           // tv2.setText("---***Description***---"+"\n"+data+"\n");
            //Glide.with(secondActivity.this).load(url).override(600,650).into(image);
        }
        catch(Exception e){
            e.printStackTrace();
            Log.i("message","file not found");
        }

    }

*/

   public void fun(View view) {
        Toast.makeText(this, "Click on news button", Toast.LENGTH_SHORT).show();
        TextView id=view.findViewById(R.id.name);
        String str=id.getText().toString();
        int number=-1;
        String[] arrOfStr = str.split("-", 2);
        number=Integer.parseInt(arrOfStr[1]);

        // getSupportFragmentManager().beginTransaction().replace(R.id.layout, new fragment4(number)).commit();


        fragment4 fm4 =new fragment4(number);
       // FragmentTransaction tc=.beginTransaction();

       tc= manager.beginTransaction();
       tc.replace(R.id.layout,fm4);
       tc.addToBackStack(fm4.getClass().getSimpleName());
      // replaceFragment(new DetailsFragment(this,number));
        tc.commit();
    }

}
