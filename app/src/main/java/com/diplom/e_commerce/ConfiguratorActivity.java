package com.diplom.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.diplom.e_commerce.model.Component;
import com.diplom.e_commerce.model.Computer;
import com.diplom.e_commerce.model.Order;

import java.util.ArrayList;
import java.util.List;

public class ConfiguratorActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout_menu;
    Button btn_cr_computer, btn_configurator_clear, btn_form, btn_computers, btn_conf, btn_components;
    String cCPU, cSSD, cMotherBoard, cPowerSupply, cVideoCard, cHDD, cFrame, cRAM, cCooler, varchar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator);

        constraintLayout_menu = findViewById(R.id.constraintLayout_menu);

        btn_form = findViewById(R.id.btn_form);
        btn_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguratorActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });

        btn_components = findViewById(R.id.btn_components);
        btn_components.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguratorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_computers = findViewById(R.id.btn_computers);
        btn_computers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguratorActivity.this, ComputersActivity.class);
                startActivity(intent);
            }
        });

        btn_conf = findViewById(R.id.btn_conf);
        btn_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfiguratorActivity.this, ConfiguratorActivity.class);
                startActivity(intent);
            }
        });

        btn_configurator_clear = findViewById(R.id.btn_configurator_clear);
        btn_configurator_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order.items_type.clear();
                Order.items_id.clear();
                Toast.makeText(getApplicationContext(), "Конфигуратор очищен!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_cr_computer = findViewById(R.id.btn_cr_computer);
        btn_cr_computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Order.items_id.size() == 9)
                {
                    int var1 = 0;
                    int var2 = 0;
                    for (String s : Order.items_id){
                        if ((s.toLowerCase().contains("intel")) || (s.toLowerCase().contains("asus prime"))){++var1;}/*Изменить условия под все материнки и процы*/
                        if ((s.toLowerCase().contains("amd")) || (s.toLowerCase().contains("msi b450m"))){++var2;}
                    }
                    if ((var1 == 0 && var2 == 2) || (var1 == 2 && var2 == 0))
                    {
                        for (String d : Order.items_id) {
                            varchar = Order.items_type.iterator().next();
                            if (varchar.toLowerCase().equals("cpu")) {
                                cCPU = d;
                            }
                            if (varchar.toLowerCase().equals("ssd")) {
                                cSSD = d;
                            }
                            if (varchar.toLowerCase().equals("motherboard")) {
                                cMotherBoard = d;
                            }
                            if (varchar.toLowerCase().contains("power")) {
                                cPowerSupply = d;
                            }
                            if (varchar.toLowerCase().equals("videocard")) {
                                cVideoCard = d;
                            }
                            if (varchar.toLowerCase().equals("hdd")) {
                                cHDD = d;
                            }
                            if (varchar.toLowerCase().equals("frame")) {
                                cFrame = d;
                            }
                            if (varchar.toLowerCase().equals("ram")) {
                                cRAM = d;
                            }
                            if (varchar.toLowerCase().equals("cooler")) {
                                cCooler = d;
                            }
                        }
                        Computer newComputer = new Computer(cCPU, cSSD, cMotherBoard, cPowerSupply, cVideoCard, cHDD, cFrame, cRAM, cCooler);
                        Intent intent = new Intent(ConfiguratorActivity.this, ComputersActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Ошибка! Несовместимые компоненты: CPU : MotherBoard", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Ошибка! Вы выбрали не все компоненты. Сборка должна содержать 9 компонентов!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ListView orders_list = findViewById(R.id.orders_list);
        List<String> productsTitle = new ArrayList<>();
        for(Component c: MainActivity.fullProductsList) {
            if(Order.items_id.contains(c.getName()))
                productsTitle.add("Категория: " + c.getType() + "\nНазвание: " + c.getName() + "\nХарактеристики:\n" + c.getProperties());
        }

        orders_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productsTitle));
    }
    private void CreateComputer(View view)
    {

    }
}