package com.example.lab3.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.lab3.R;

public class Bai1Activity extends AppCompatActivity {
    private ListView lvContact;
    GetContact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        lvContact = findViewById(R.id.listView);
        contact = new GetContact(lvContact, this);
        contact.execute();
    }
}