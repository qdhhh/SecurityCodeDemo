package com.android.qdhhh.securitycodedemo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.qdhhh.securitycodedemo.widget.SecurityCode;

public class MainActivity extends AppCompatActivity {

    private Button bt_id;
    private SecurityCode sc_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        bt_id = (Button) findViewById(R.id.bt_id);
        sc_id = (SecurityCode) findViewById(R.id.sc_id);

        bt_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, sc_id.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
