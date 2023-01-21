package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        listView=findViewById(R.id.list_view);

        DataBaseHandler db = new DataBaseHandler(this);
        final ArrayList<Bean> bean = db.getAllRecords();

        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this,bean);
        listView.setAdapter(myBaseAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id1) {

                String fName=bean.get(position).getFirstName() ;
                String lName=bean.get(position).getLastName();
                String id = bean.get(position).getId();
//                Toast.makeText(DisplayActivity.this,fName,Toast.LENGTH_SHORT).show();


                Intent i1 = new Intent(DisplayActivity.this,UpdateActivity.class);
                i1.putExtra("FIRST_NAME",fName);
                i1.putExtra("LAST_NAME",lName);
                i1.putExtra("ID",id);
                startActivity(i1);

            }
        });




    }
}