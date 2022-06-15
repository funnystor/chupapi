package com.diplom.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diplom.e_commerce.model.Order;

public class ProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);

        ImageView productImage = findViewById(R.id.productPageImage);
        TextView productTitle = findViewById(R.id.productPageTitle);
        TextView productCategory = findViewById(R.id.productPageCategory);
        TextView productProperties = findViewById(R.id.productPageProperties);

        productImage.setImageResource(getIntent().getIntExtra("productImage", 0));
        productTitle.setText(getIntent().getStringExtra("productTitle"));
        productCategory.setText(getIntent().getStringExtra("productCategory"));
        productProperties.setText(getIntent().getStringExtra("productProperties"));
    }

    public void addToCart(View view) {
        String item_name = getIntent().getStringExtra("productTitle");
        String item_type = getIntent().getStringExtra("productCategory");
        if(!Order.items_type.contains(item_type)){
            Order.items_id.add(item_name);
            Order.items_type.add(item_type);
            Toast.makeText(this, "Компонент добавлен в конфигуратор", Toast.LENGTH_LONG).show();
        }
        else{
            String errTxt = "Компонент типа " + item_type + " уже присутствует в конфигураторе. Добавьте другой компонент.";
            Toast.makeText(this, errTxt, Toast.LENGTH_LONG).show();
        }
    }
}