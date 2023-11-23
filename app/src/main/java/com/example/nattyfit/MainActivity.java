package com.example.nattyfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void imc(View view) {
        Intent in = new Intent (MainActivity.this, IMC.class);
        startActivity(in);
    }

    public void tmb(View view) {
        Intent in = new Intent (MainActivity.this, TMB.class);
        startActivity(in);
    }

    public void percGord(View view) {
        Intent in = new Intent (MainActivity.this, percGord.class);
        startActivity(in);
    }
}