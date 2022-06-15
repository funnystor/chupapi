package com.diplom.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diplom.e_commerce.model.Constant;

public class ShowActivity extends AppCompatActivity {
    private TextView tvName, tvType, tvHref, tvProperties;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentRead();
    }
    private void init()
    {
        tvName = findViewById(R.id.tvName);
        tvType = findViewById(R.id.tvType);
        tvHref = findViewById(R.id.tvHref);
        tvProperties = findViewById(R.id.tvProperties);
    }
    private void getIntentRead()
    {
        Intent i = getIntent();
        if (i != null)
        {
            tvName.setText(i.getStringExtra(Constant.COMPONENT_NAME));
            tvType.setText(i.getStringExtra(Constant.COMPONENT_TYPE));
            tvProperties.setText(i.getStringExtra(Constant.COMPONENT_PROPERTIES));
            tvHref.setText(i.getStringExtra(Constant.COMPONENT_HREF));
        }
    }
}
