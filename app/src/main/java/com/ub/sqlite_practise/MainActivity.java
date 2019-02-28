package com.ub.sqlite_practise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText ed1,ed2;
    Button btn1,btn2,btn3,btn4;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.ED1);
        ed2=(EditText)findViewById(R.id.ED2);
        btn1=(Button)findViewById(R.id.btn_1);
        btn2=(Button)findViewById(R.id.btn_2);
        db=new DatabaseHelper(this);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                insert();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this,View_details.class);
                startActivity(intent);
            }
        });
    }
    public void insert()
    {
        String name=ed1.getText().toString().trim();
        String address=ed2.getText().toString().trim();
        db.addPerson(name,address);
        Toast.makeText(getApplicationContext(), "Data added succesffully", Toast.LENGTH_SHORT).show();
    }

}
