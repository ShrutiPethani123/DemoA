package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText edtFn,edtLn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edtFn=findViewById(R.id.edt_fn);
        edtLn=findViewById(R.id.edt_ln);

        Intent i = getIntent();
        String fn = i.getStringExtra("FIRST_NAME");
        String ln = i.getStringExtra("LAST_NAME");
        String id = i.getStringExtra("ID");
        edtFn.setText(fn);
        edtLn.setText(ln);

    }
}