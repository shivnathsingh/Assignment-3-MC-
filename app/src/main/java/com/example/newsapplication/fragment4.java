package com.example.newsapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class fragment4 extends Fragment {
    int number;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment4() {
        // Required empty public constructor
    }

    public fragment4( int id) {
        this.number = id;
    }

    public Button submit;
    public EditText edit;
    protected String str;
    private TextView title;
    private TextView body;
    private TextView url_of_image;
    private ImageView img;
    public RatingBar rtstar;
    public TextView txt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment4, container, false);
        title = view.findViewById(R.id.fragtitle);
        body = view.findViewById(R.id.fragbody);
        url_of_image = view.findViewById(R.id.fragurl);
        img = view.findViewById(R.id.image);
        edit=view.findViewById(R.id.edit);
        submit=view.findViewById(R.id.submit);
        rtstar=view.findViewById(R.id.ratbar);
        txt=view.findViewById(R.id.rate);

       // Toast.makeText(getContext().getApplicationContext(), "We are on frg4", Toast.LENGTH_SHORT).show();


        //LocalBroadcastManager.getInstance(getContext()).registerReceiver(myreceiver, new IntentFilter("News_number"));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Your comment Added Succesfully", Toast.LENGTH_SHORT).show();
                String str=edit.getText().toString();
                Myservice.map.put(number,str);
                int rate=(int) rtstar.getRating();
                Myservice.ratemap.put(number,rate);
                txt.setText("You rated current news "+rate+"/5");
                edit.setText("");
                rtstar.setRating(0);


                Log.i("Stored"+number, Myservice.map.get(number));
                Log.i("done"+number, str);
            }
        });



        if(number!=-1)
            show1();
        else
            Log.i("No news selected ",""+number);




        return view;
    }



    private void show1() {
       // Toast.makeText(getContext().getApplicationContext(), "show method call", Toast.LENGTH_SHORT).show();
        File file1 = new File(getContext().getApplicationContext().getFilesDir(), "file" + number + ".txt");
        if(file1.exists())
            help(file1,title,body,url_of_image,img,edit);
    }
    @Override
    public void onStart() {
        super.onStart();
      //  LocalBroadcastManager.getInstance(getContext()).registerReceiver(myreceiver, new IntentFilter("News_number"));
    }






    @Override
    public void onStop() {
        super.onStop();
       // LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(myreceiver);
    }



    private void help(File file, TextView title1, TextView body1, TextView url_of_image, ImageView img, EditText edit) {
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
            String data = jsnObject.getString("body");
            String url = jsnObject.getString("image-url");

            if (Myservice.map.containsKey(number)) {

                // Mapping
                String str = Myservice.map.get(number);
                edit.setText(str);
                int rate=Myservice.ratemap.get(number);
                rtstar.setRating(rate);
                txt.setText("Previously rated "+rate+"/5");
                Log.i("News already "+number,str);
                Log.i("News already rated "+number, String.valueOf(rate));

            }


            Log.i("img-url", url);
            title1.setText("---***Title***---"+"\n"+title+"\n\n");
            body1.setText("---***Body***---"+"\n"+data+"\n");
            Glide.with(getContext().getApplicationContext()).load(url).override(600,650).into(img);
        }
        catch(Exception e){
            e.printStackTrace();
            Log.i("message","file not found");
        }

    }

    }
