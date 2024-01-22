package com.example.a20240120dailywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button msign_in_bnt;
    private Button msign_up_bnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button msign_in_bnt = findViewById(R.id.sign_in_botton);
        msign_in_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        R.string.sign_in_succeed_toast,
                        Toast.LENGTH_SHORT).show();
            }
        });
        Button msign_up_bnt = findViewById(R.id.sign_up_botton);
        msign_up_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        R.string.sign_in_succeed_toast,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    
}