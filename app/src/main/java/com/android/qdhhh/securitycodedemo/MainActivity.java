package com.android.qdhhh.securitycodedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.qdhhh.securitycodedemo.widget.SecurityCode;

public class MainActivity extends AppCompatActivity {

    private SecurityCode sc_id;
    private Button bt_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hahha);

        initView();
    }

    private void initView() {

        sc_id = (SecurityCode) findViewById(R.id.sc_id);
        bt_id = (Button) findViewById(R.id.bt_id);

        bt_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, sc_id.getCurrentCode() + "-----", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
