package com.example.admin.convertisseuryoutube;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity {


    private ImageButton convertButton;
    private EditText editUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUrl = (EditText) findViewById(R.id.editUrl);
        convertButton = (ImageButton) findViewById(R.id.ImageButtonConvert);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editUrl.getText().toString().equals("")){

                    Intent myIntent = new Intent(getApplicationContext(), ConversionActivity.class);
                    startActivity(myIntent);
                   // convertUrl(editUrl.getText().toString());

                }
            }
        });
    }


}

