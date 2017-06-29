package com.example.da08.serverconnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WriteActivity extends AppCompatActivity {


    EditText editTitle;

    EditText editAuthor;

    EditText editContent;

    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);



    }

    public void onClick(){
        Toast.makeText(this,"yoyo",Toast.LENGTH_SHORT).show();
    }


}
