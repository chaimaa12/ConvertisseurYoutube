package com.example.admin.convertisseuryoutube;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * Created by admin on 17/04/2017.
 */

public class ConversionActivity extends Activity {

    private Button btnRetour;
    private Button btnDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_layout);
        btnRetour = (Button) findViewById(R.id.btnBack);
        btnDownload = (Button) findViewById(R.id.btnDownload);

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





    }


}
