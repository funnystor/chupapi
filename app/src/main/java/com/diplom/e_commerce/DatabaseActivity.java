package com.diplom.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diplom.e_commerce.model.Component;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DatabaseActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout_menu;
    Button btn_form, btn_computers, btn_conf, btn_components;
    private EditText input_name, input_type, input_href, input_properties, input_img;
    private DatabaseReference myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        init();

        constraintLayout_menu = findViewById(R.id.constraintLayout_menu);
        btn_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });
        btn_components.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_computers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseActivity.this, ComputersActivity.class);
                startActivity(intent);
            }
        });
        btn_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseActivity.this, ConfiguratorActivity.class);
                startActivity(intent);
            }
        });
    }
    public void init()
    {
        btn_form = findViewById(R.id.btn_form);
        btn_components = findViewById(R.id.btn_components);
        btn_computers = findViewById(R.id.btn_computers);
        btn_conf = findViewById(R.id.btn_conf);

        input_name = findViewById(R.id.input_name);
        input_type = findViewById(R.id.input_type);
        input_href = findViewById(R.id.input_href);
        input_properties = findViewById(R.id.input_properties);
        input_img = findViewById(R.id.input_img);
        String db_var = "Component";
        myDataBase = FirebaseDatabase.getInstance().getReference(db_var);
    }
    public void onClickSave(View view)
    {
        String id = myDataBase.getKey();
        String name = input_name.getText().toString();
        String type = input_type.getText().toString();
        String href = input_href.getText().toString();
        String properties = input_properties.getText().toString();
        String img = input_img.getText().toString();
        Component newComponent = new Component(id, name, type, properties, href, img);
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(href) && !TextUtils.isEmpty(properties) && !TextUtils.isEmpty(img)){
            myDataBase.push().setValue(newComponent);
            Toast.makeText(this, "Добавлено!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Компонент не добавлен. Заполнены не все поля!", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickRead(View view)
    {
        Intent intent = new Intent(DatabaseActivity.this, ReadActivity.class);
        startActivity(intent);
    }
}