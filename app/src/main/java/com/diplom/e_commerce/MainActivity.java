package com.diplom.e_commerce;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.diplom.e_commerce.adapter.CategoryAdapter;
import com.diplom.e_commerce.adapter.ProductAdapter;
import com.diplom.e_commerce.model.Category;
import com.diplom.e_commerce.model.Component;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, productRecycler;
    CategoryAdapter categoryAdapter;
    ConstraintLayout constraintLayout_menu;
    Button btn_form, btn_computers, btn_conf, btn_components;
    @SuppressLint("StaticFieldLeak")
    public static ProductAdapter productAdapter;
    static List<Component> productList = new ArrayList<>();
    static List<Component> fullProductsList = new ArrayList<>();
    public DatabaseReference myDataBase;
    public String db_var = "Component";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getDataFromDB();

        constraintLayout_menu = findViewById(R.id.constraintLayout_menu);
        btn_form = findViewById(R.id.btn_form);
        btn_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });

        btn_components = findViewById(R.id.btn_components);
        btn_components.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_computers = findViewById(R.id.btn_computers);
        btn_computers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ComputersActivity.class);
                startActivity(intent);
            }
        });

        btn_conf = findViewById(R.id.btn_conf);
        btn_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfiguratorActivity.class);
                startActivity(intent);
            }
        });

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(99, "ALL"));
        categoryList.add(new Category(1, "SSD"));
        categoryList.add(new Category(2, "HDD"));
        categoryList.add(new Category(3, "VideoCard"));
        categoryList.add(new Category(4, "CPU"));
        categoryList.add(new Category(5, "Power Supply"));
        categoryList.add(new Category(6, "MotherBoard"));
        categoryList.add(new Category(7, "Frame"));
        categoryList.add(new Category(8, "Cooler"));
        categoryList.add(new Category(9, "RAM"));

        setCategoryRecycler(categoryList);

        productList = fullProductsList;

        setProductRecycler(productList);
    }

    private void setProductRecycler(List<Component> productList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        productRecycler = findViewById(R.id.productRecycler);
        productRecycler.setLayoutManager(layoutManager);

        productAdapter = new ProductAdapter(this, productList);
        productRecycler.setAdapter(productAdapter);

    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

    }

    @SuppressLint("NotifyDataSetChanged")
    public static void showProductsByCategory(String category) {
        System.out.println(category);

        if(category.toUpperCase(Locale.ROOT).equals("ALL")){
            System.out.println("First If Yes");
            productList.clear();
            productList.addAll(fullProductsList);
            System.out.println(productList);
            System.out.println(fullProductsList);
        }
        else{
            System.out.println("First If No");
            List<Component> filterProductsList = new ArrayList<>();
            for(Component component : fullProductsList){
                System.out.println(component.getType().toUpperCase(Locale.ROOT));
                System.out.println(component.type.toUpperCase(Locale.ROOT));
                if(component.getType().toUpperCase(Locale.ROOT).contains(category.toUpperCase(Locale.ROOT))){
                    System.out.println("Совпадение найдено");
                    filterProductsList.add(component);
                }
                else{
                    System.out.println(component.getType().toUpperCase(Locale.ROOT));
                    System.out.println(category.toUpperCase(Locale.ROOT));
                    System.out.println("Совпадение не найдено");
                }
            }
            System.out.println();
            for(Component c : filterProductsList){
                System.out.println(c);
                System.out.println(c.name);
                System.out.println(c.getType());
                System.out.println("===");
            }
            productList = filterProductsList;
            filterProductsList.clear();
        }
        productAdapter.notifyDataSetChanged();
/*
        if(fullProductsList.contains(category)){
            System.out.println(category);
            System.out.println("ALL");
            productList.clear();
            productList.addAll(fullProductsList);
        }
        else{
            productList.clear();
            productList.addAll(fullProductsList);

            List<Component> filterProducts = new ArrayList<>();

            for(Component c : productList) {
                System.out.println(category);
                System.out.println(c.getType());
                if(Objects.equals(c.getType(), category)) {
                    filterProducts.add(c);
                }
            }

            productList.clear();
            productList.addAll(filterProducts);

        }

        productAdapter.notifyDataSetChanged();
*/
    }
    private void init()
    {
        myDataBase = getInstance().getReference(db_var);
    }

    private void getDataFromDB()
    {
        ValueEventListener vListener = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(fullProductsList.size()>0)fullProductsList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Component component = ds.getValue(Component.class);
                    assert component != null;
                    fullProductsList.add(component);
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        };
        myDataBase.addValueEventListener(vListener);
    }
}