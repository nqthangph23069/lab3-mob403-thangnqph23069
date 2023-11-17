package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.lab3.bai1.Bai1Activity;
import com.example.lab3.bai2.Bai2Activity;
import com.example.lab3.bai3.Bai3Activity;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btnBai1);
        btn2 = findViewById(R.id.btnBai2);
        btn3 = findViewById(R.id.btnBai3);
        btn4 = findViewById(R.id.btnBai4);
        btn1.setOnClickListener(v -> {
            startActivity(new Intent(this, Bai1Activity.class));
        });
        btn2.setOnClickListener(v -> {
            startActivity(new Intent(this, Bai2Activity.class));
        });
        btn3.setOnClickListener(v -> {
            startActivity(new Intent(this, Bai3Activity.class));
        });
    }
}