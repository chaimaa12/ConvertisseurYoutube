package com.example.admin.convertisseuryoutube.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

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


    File outputFile;
    String url;

    public DownloadLinkTask(String url) {
        this.url = url;
    }

    @Override
    protected Void doInBackground(String... param) {

        try {
            URL aUrl = new URL(url);
            URLConnection conn = aUrl.openConnection();
            int contentLength = conn.getContentLength();

            DataInputStream stream = new DataInputStream(aUrl.openStream());
            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();

            DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
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
