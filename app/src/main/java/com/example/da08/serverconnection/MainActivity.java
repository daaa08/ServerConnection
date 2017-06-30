package com.example.da08.serverconnection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @Click
    public void btnPost(){
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);
    }

}
