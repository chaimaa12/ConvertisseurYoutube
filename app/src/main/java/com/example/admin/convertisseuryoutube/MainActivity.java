package com.example.admin.convertisseuryoutube;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.admin.convertisseuryoutube.model.DownloadJsonTask;
import com.example.admin.convertisseuryoutube.model.IOListener;
import com.example.admin.convertisseuryoutube.model.Music;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Button btnConvert;
    private EditText eTlink;
    private Music currentMusic;

    private static final String URL_MUSIC_CONVERT = "http://www.youtubeinmp3.com/fetch/?format=JSON&video=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eTlink = (EditText) findViewById(R.id.eTlink);

        eTlink = (EditText) findViewById(R.id.eTlink);
        eTlink.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    if(!eTlink.getText().toString().equals("")){
                        Convertmp3(eTlink.getText().toString());
                    }
                    //FERMER CLAVIER
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(eTlink.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        btnConvert = (Button) findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!eTlink.getText().toString().equals("")){
                    Convertmp3(eTlink.getText().toString());
                    //startActivity(new Intent(MainActivity.this, ConversionActivity.class));
                    eTlink.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "You have to paste a YouTube link",
                            Toast.LENGTH_LONG).show();
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(eTlink.getWindowToken(), 0);
            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.action_account:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    private void Convertmp3(String textSearch){
        final ProgressDialog spinner = new ProgressDialog(this);
        spinner.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        spinner.setTitle("Recherche : "+ textSearch);
        spinner.setMessage("Chargement en cours ...");
        spinner.setCancelable(false);
        spinner.show();

        String url= URL_MUSIC_CONVERT+ textSearch.replace(" ", "+");
        DownloadJsonTask jsonTask = new DownloadJsonTask(this, spinner, new IOListener() {
            @Override
            public void IOListener(Music m) {
                Intent videoIntent = new Intent(MainActivity.this, ConversionActivity.class);
                videoIntent.putExtra("videoConverted", m);
                startActivity(videoIntent);
            }
        });
        jsonTask.execute(url);
    }


}

