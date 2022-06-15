package com.diplom.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComputersActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout_menu;
    Button btn_form, btn_computers, btn_conf, btn_components;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computers);


        constraintLayout_menu = findViewById(R.id.constraintLayout_menu);
        btn_form = findViewById(R.id.btn_form);
        btn_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComputersActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });

        btn_components = findViewById(R.id.btn_components);
        btn_components.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComputersActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_computers = findViewById(R.id.btn_computers);
        btn_computers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComputersActivity.this, ComputersActivity.class);
                startActivity(intent);
            }
        });

        btn_conf = findViewById(R.id.btn_conf);
        btn_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComputersActivity.this, ConfiguratorActivity.class);
                startActivity(intent);
            }
        });
    }
}