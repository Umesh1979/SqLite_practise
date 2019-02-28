package com.ub.sqlite_practise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Modify_Record extends AppCompatActivity
{
    EditText Update_name,Update_add;
    Button Btn_Update,Btn_Delete;
    private long id;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify__record);
//        databaseHelper=new DatabaseHelper(this);


    }
}
