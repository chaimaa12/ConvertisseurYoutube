package com.example.admin.convertisseuryoutube;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.convertisseuryoutube.model.DownloadLinkTask;
import com.example.admin.convertisseuryoutube.model.IOListener;
import com.example.admin.convertisseuryoutube.model.Music;

/**
 * Created by admin on 17/04/2017.
 */

public class ConversionActivity extends Activity implements IOListener, View.OnClickListener {

    private Button btnDownload;
    private EditText editText;
    private Music currentMusic;
    private TextView titleVideo;
    private BottomNavigationView bottomNavigationView;

    private String path = "/Downloads/";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_layout);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        titleVideo = (TextView) findViewById(R.id.titleVideo);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        btnDownload.setOnClickListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
               // début du télechargement
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(ConversionActivity.this, MainActivity.class));
                        break;
                    case R.id.action_account:
                        startActivity(new Intent(ConversionActivity.this, MainActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    public void setSelectedMusic(Music m){
        m.getTitle();
        titleVideo.setText(m.getTitle());

    }
    @Override
    protected void onResume() {

        super.onResume();
        Intent myIntent = getIntent();
        Music music = (Music) myIntent.getSerializableExtra("videoConverted");
        currentMusic = music;
        setSelectedMusic(currentMusic);
    }

    @Override
    public void IOListener(Music m) {
        final Activity activity= this;
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, "Download finish", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onClick(View v) {
        DownloadLinkTask linkTask = new DownloadLinkTask(currentMusic, path, this);
        linkTask.execute(currentMusic.getLink());
        // début du télechargement

        Toast.makeText(getApplicationContext(), "Downloading",
                Toast.LENGTH_LONG).show();
    }
}
