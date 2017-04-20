package com.example.admin.convertisseuryoutube;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import java.util.jar.Manifest;

public class MainActivity extends Activity {

    private Button btnConvert;
    private EditText eTlink;
    private Music currentMusic;

    private static final String URL_MUSIC_CONVERT = "http://www.youtubeinmp3.com/fetch/?format=JSON&video=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
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

    private void checkPermission(){

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){

                new AlertDialog.Builder(this).setTitle("Access permission").setMessage("The App needs to access your local storage to download the file")
                        .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.INTERNET}, 1);

                            }
                        }).create().show();
            }ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.INTERNET}, 1);

        }

    }

}

