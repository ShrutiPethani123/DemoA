package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    Button btnAdd , btnDisplay;
    EditText edtFn,edtLn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnAdd=findViewById(R.id.btn_add);
        btnDisplay=findViewById(R.id.btn_display);
        edtFn=findViewById(R.id.edt_fn);
        edtLn = findViewById(R.id.edt_ln);
        final DataBaseHandler db = new DataBaseHandler(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strfn =edtFn.getText().toString();
                String strln = edtLn.getText().toString();

                Bean b = new Bean();
                b.setFirstName(strfn);
                b.setLastName(strln);

                db.insertRecord(b);
                edtFn.setText("");
                edtLn.setText("");

            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddActivity.this,DisplayActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}