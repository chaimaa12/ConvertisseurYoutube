package com.example.admin.convertisseuryoutube.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import com.example.admin.convertisseuryoutube.ConversionActivity;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by admin on 19/04/2017.
 */

public class DownloadLinkTask extends AsyncTask<String, Void, Void> {



   private Music music;
   private String path;

    public DownloadLinkTask(Music music, String path) {
        this.music = music;
        this.path = path;
    }

    @Override
    protected Void doInBackground(String... param) {

        try {
            URL aUrl = new URL(music.getLink());
            URLConnection conn = aUrl.openConnection();
            int contentLength = conn.getContentLength();

            DataInputStream stream = new DataInputStream(aUrl.openStream());
            byte[] buffer = new byte[Integer.parseInt(music.getLenght())];
            stream.readFully(buffer);
            stream.close();

            File myFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+music.getTitle()+".mp3");
           // File myFile = new File(Environment.getRootDirectory()+"/Download"+music.getTitle()+".mp3");
            DataOutputStream fos = new DataOutputStream(new FileOutputStream(myFile));
            fos.write(buffer);
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
   // private Bitmap download_music(String url) {}
