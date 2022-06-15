package com.diplom.e_commerce;

import static com.google.firebase.database.FirebaseDatabase.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diplom.e_commerce.model.Component;
import com.diplom.e_commerce.model.Constant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    public ListView listView;
    public ArrayAdapter<String> adapter;
    public List<String> listData;
    public static List<Component> listTemp;
    public DatabaseReference myDataBase;
    public String db_var = "Component";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        init();
        getDataFromDB();
        setOnClickItem();
    }
    private void init()
    {
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        myDataBase = getInstance().getReference(db_var);
    }
    private void getDataFromDB()
    {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(listData.size()>0)listData.clear();
                if(listTemp.size()>0)listTemp.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Component component = ds.getValue(Component.class);
                    assert component != null;
                    listData.add(component.name);
                    listTemp.add(component);
                }
                for (int i = 0; i < listData.size(); i++)
                    System.out.println(listData.get(i));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        };
     myDataBase.addValueEventListener(vListener);
    }
    private void setOnClickItem()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Component component = listTemp.get(position);
                Intent i = new Intent(ReadActivity.this, ShowActivity.class);
                i.putExtra(Constant.COMPONENT_NAME, component.name);
                i.putExtra(Constant.COMPONENT_TYPE, component.type);
                i.putExtra(Constant.COMPONENT_PROPERTIES, component.properties);
                i.putExtra(Constant.COMPONENT_HREF, component.href);
                startActivity(i);
            }
        });
    }
}
