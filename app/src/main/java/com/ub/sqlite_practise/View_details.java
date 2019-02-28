package com.ub.sqlite_practise;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class View_details extends AppCompatActivity
{
    EditText ed1;
    EditText txt1,txt2;
    Button btn1,btn3,btn4,btn5;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        ed1=(EditText)findViewById(R.id.ed1);
        txt1=(EditText) findViewById(R.id.txt1);
        txt2=(EditText) findViewById(R.id.txt2);
        btn1=(Button)findViewById(R.id.btn_1);
        btn3=(Button)findViewById(R.id.btn_3);
        btn4=(Button)findViewById(R.id.btn_4);
        btn5=(Button)findViewById(R.id.btn_5);
        db=new DatabaseHelper(this);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int id=Integer.parseInt(ed1.getText().toString());
                showPerson(id);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id=ed1.getText().toString();
                String name=txt1.getText().toString();
                String add=txt2.getText().toString();
                updateData(id,name,add);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id=ed1.getText().toString();
                deleteData(id);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor c=db.getAllData();
                if(c.getCount()==0)
                {
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("ID :"+c.getString(0)+"\n");
                    buffer.append("Name :"+c.getString(1)+"\n");
                    buffer.append("Address :"+c.getString(2)+"\n\n");

                }
                showMessage("Data",buffer.toString());

            }
        });
    }
    public void showPerson(int id)
    {
        try
        {
            Cursor c=db.getPerson(id);//getting cursor details into c
            if(c!=null)
            {
                c.moveToFirst();//moving cursor to the first position
                    txt1.setText(c.getString(c.getColumnIndex(db.COLUMN_NAME)));//setting the cursor details of name into textbox
                    txt2.setText(c.getString(c.getColumnIndex(db.COLUMN_ADD)));//setting the cursor details of address into textbox
            }
        }
        catch (Exception e)
        {
            ed1.setText("");
            txt1.setText("");
            txt2.setText("");
            Toast.makeText(getApplicationContext(), "There is no data available with this ID", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateData(String id,String name,String add)
    {
        int is_update=db.update_record(id, name, add);
        if(is_update>0)
            {
                ed1.setText("");
                txt1.setText("");
                txt2.setText("");
                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getApplicationContext(), "There is no such id found", Toast.LENGTH_SHORT).show();
    }
    public void deleteData(String id)
    {
            int deleted_row=db.delete(id);
            if(deleted_row>0)
            {
                ed1.setText("");
                txt1.setText("");
                txt2.setText("");
                Toast.makeText(getApplicationContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getApplicationContext(), "There is no such id to delete", Toast.LENGTH_SHORT).show();
    }
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
