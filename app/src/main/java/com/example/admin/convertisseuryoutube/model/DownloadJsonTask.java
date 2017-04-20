package com.example.admin.convertisseuryoutube.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.convertisseuryoutube.model.Music;
import com.example.admin.convertisseuryoutube.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class DownloadJsonTask extends AsyncTask<String, Void, String>{
    private ProgressDialog spinner;
    private Context context;
    private IOListener listener;

    //String link = "https://www.youtube.com/watch?v=JY0SCaEu2R8";



    public DownloadJsonTask(Context context, ProgressDialog spinner, IOListener listener) {
        this.spinner = spinner;
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        return download_xmldom(params[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        youtubeJsonToMusics(result);
        //deezerJsonToMusics(result);
    }

    private void youtubeJsonToMusics(String result) {

        try {
            JSONObject jsonObject = new JSONObject(result);
            Music m = new Music();
            m.setName(jsonObject.getString("title"));
            m.setLenght(jsonObject.getString("length"));
            m.setLink(jsonObject.getString("link"));
            listener.IOListener(m);
            spinner.dismiss();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String download_xmldom(String url){
        String result="";
        try {
            URL aUrl = new URL(url);
            URLConnection conn= aUrl.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            result= convertStreamToString(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            while((line = reader.readLine()) != null){
                sb.append(line).append('\n');
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
