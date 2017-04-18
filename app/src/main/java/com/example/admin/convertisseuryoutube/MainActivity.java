package com.example.admin.convertisseuryoutube;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity {


    private Button btnConvert;
    private EditText eTlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eTlink = (EditText) findViewById(R.id.eTlink);
        btnConvert = (Button) findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!eTlink.getText().toString().equals("")){

                    Intent myIntent = new Intent(getApplicationContext(), ConversionActivity.class);
                    startActivity(myIntent);
                   // convertUrl(editUrl.getText().toString());

                }
            }
        });
    }


}

