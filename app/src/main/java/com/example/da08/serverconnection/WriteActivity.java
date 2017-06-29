package com.example.da08.serverconnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteActivity extends AppCompatActivity {

    @BindView(R.id.editTitle)
    EditText editTitle;
    @BindView(R.id.editAuthor)
    EditText editAuthor;
    @BindView(R.id.editContent)
    EditText editContent;
    @BindView(R.id.btnWrite)
    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnWrite)
    public void onClick(){
        Toast.makeText(this,"yoyo",Toast.LENGTH_SHORT).show();
    }


}
