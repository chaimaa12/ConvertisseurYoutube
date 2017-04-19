package com.example.admin.convertisseuryoutube;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.admin.convertisseuryoutube.model.Music;

/**
 * Created by admin on 17/04/2017.
 */

public class ConversionActivity extends Activity {

    private Button btnDownload;
    private EditText editText;
    private Music currentMusic;
    private TextView titleVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_layout);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        titleVideo = (TextView) findViewById(R.id.titleVideo);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // début du télechargement
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
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



}
