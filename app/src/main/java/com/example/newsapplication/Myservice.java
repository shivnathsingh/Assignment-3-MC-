package com.example.newsapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Myservice extends Service {
   protected EditText ans;
    protected ListView lv;
    protected String str1,str2;
    protected String url_of_image;
    private String TAG = MainActivity.class.getSimpleName();
    protected String msg1,msg2,url2;
    public static HashMap<Integer,String> map = new HashMap<>();
    public static HashMap<Integer,Integer> ratemap=new HashMap<>();
    MainActivity mn;
    //ArrayList<HashMap<String, String>> contactList;
    String contactList;

    static int check=1,i,last=0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        checkConnection();
      //  LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver();
        check=1;
        i=last;
        new asynctask().execute();
        Toast.makeText(this, "Services Started ", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }



    @Override
    public void onDestroy()
    {
        check=2;
        Toast.makeText(this,"Service Stopped ",Toast.LENGTH_LONG).show();
        super.onDestroy();
    }



    public void checkConnection() {
        ConnectivityManager cmng = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNw = cmng.getActiveNetworkInfo();
        if (actNw != null && actNw.isConnected()) {

            if (actNw.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "WIFI ENABLED", Toast.LENGTH_LONG).show();

            } else {
                if (actNw.getType() == ConnectivityManager.TYPE_MOBILE)
                    Toast.makeText(this, "Mobile data is enabled", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    private class asynctask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {



            ConnectivityManager connMan = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo=connMan.getActiveNetworkInfo();
            if(netInfo!=null && netInfo.isAvailable() && netInfo.isConnected()){

                String link="https://petwear.in/mc2022/news/news_";
                for(;i>=0;i++) {
                    if (check == 2) {
                        last = i;
                        break;
                    }
                    link += i + ".json";
                    Log.i("link-",link);
                    File file = new File(getApplicationContext().getFilesDir(),"file"+i+".txt");

                    if(file.exists()) {
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
                            if(ans!=null) {
                                JSONObject jsnObject = new JSONObject(ans);
                                String str1 = jsnObject.getString("title");
                                String str2 = jsnObject.getString("body");
                                String url_of_image=jsnObject.getString("image-url");
                                //sendMessageToActivity(str1, str2,url_of_image,i);
                                Log.i("str1",str1);
                                Log.i("str2",str2);
                                Log.i("str3",url_of_image);

                              Intent intent = new Intent();
                              intent.putExtra("key1", str1);
                             intent.putExtra("key2", str2);
                             intent.putExtra("key3",url_of_image);
                             intent.putExtra("index",i);

                             intent.setAction("BroadCastMyData");
                             LocalBroadcastManager.getInstance(Myservice.this).sendBroadcast(intent);
                                link = "https://petwear.in/mc2022/news/news_";
                                Thread.sleep(1000);
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                            Log.i("file reading message","file reading failed");
                            link = "https://petwear.in/mc2022/news/news_";
                        }

                    }
                    else{
                        try {
                            URL url = new URL(link);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("GET");
                            InputStream inStr = new BufferedInputStream(conn.getInputStream());
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inStr));
                            StringBuilder strbld = new StringBuilder();
                            String line;
                            try {
                                while ((line = reader.readLine()) != null) {
                                    strbld.append(line);
                                }
                            } catch (Exception e) {
                                Log.i("reading message", "reading failed");
                                link = "https://petwear.in/mc2022/news/news_";
                            }
                            String ans = strbld.toString();
                            if (ans != null) {
                                FileWriter fileWriter = new FileWriter(file);
                                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                bufferedWriter.write(ans);
                                bufferedWriter.close();

                                JSONObject jsnObject = new JSONObject(ans);
                                String str1 = jsnObject.getString("title");
                                String str2 = jsnObject.getString("body");
                                String url_of_image = jsnObject.getString("image-url");

                               // sendMessageToActivity(str1, str2,url_of_image,i);


                               Intent intent = new Intent();
                                intent.putExtra("key1", str1);
                                intent.putExtra("key2", str2);
                                intent.putExtra("key3",url_of_image);
                                intent.putExtra("index",i);

                                intent.setAction("BroadCastMyData");
                                LocalBroadcastManager.getInstance(Myservice.this).sendBroadcast(intent);
                                link = "https://petwear.in/mc2022/news/news_";
                                Thread.sleep(1000);
                            } else {
                                Log.i("string message", "ans is null");
                                link = "https://petwear.in/mc2022/news/news_";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.i("Url error", "url connection failed");
                            Log.i("service failed @", "");
                            link = "https://petwear.in/mc2022/news/news_";
                            stopSelf();
                        }
                    }
                }

            }else{
                Log.i("network error","network connection failed");
                Toast.makeText(Myservice.this, "network connection failed", Toast.LENGTH_SHORT).show();
            }

            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         //   Toast.makeText(Myservice.this, "downloding start", Toast.LENGTH_LONG).show();

        }



        /*  private void sendMessageToActivity (String msg1, String msg2, String url,int i){
                Intent intent = new Intent("intentKey");
              //  Toast.makeText(Myservice.this, "Send Message to activity call", Toast.LENGTH_LONG).show();
                // You can also include some extra data.
                intent.putExtra("key1", msg1);
                intent.putExtra("key2", msg2);
                intent.putExtra("key3", url);
                intent.putExtra("Index",i);

                LocalBroadcastManager.getInstance(Myservice.this).sendBroadcast(intent);

            }*/

    }

}





