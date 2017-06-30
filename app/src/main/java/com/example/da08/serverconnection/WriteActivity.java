package com.example.da08.serverconnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.da08.serverconnection.domain.Bbs;
import com.example.da08.serverconnection.domain.Result;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;


public class WriteActivity extends AppCompatActivity {


    EditText editTitle;
    EditText editAuthor;
    EditText editContent;
    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        editTitle = (EditText)findViewById(R.id.editTitle);
        editAuthor = (EditText)findViewById(R.id.editAuthor);
        editContent = (EditText)findViewById(R.id.editContent);
        btnWrite = (Button)findViewById(R.id.btnWrite);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String author = editAuthor.getText().toString();
                String content = editContent.getText().toString();
                // 위엣에 입력된 값을 객체에 담고
                Bbs bbs = new Bbs();
                bbs.title = title;
                bbs.author = author;
                bbs.content = content;
                // jsonString으로 변환
                Gson gson = new Gson();
                String jsonString = gson.toJson(bbs);

                // retrofit정의
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.10.253:8080/")   // 도메인 주소 끝에 /꼭 써줘야 함!!!!!!!!!!!!!!!
                        .build();
                // 실제 서비스 인터페이스 생성
                BbsService service = retrofit.create(BbsService.class);
                Call<Result> result= service.createBbs(bbs);

                Toast.makeText(WriteActivity.this,"result"+result.toString(),Toast.LENGTH_SHORT).show();
            }
        });


    }


}
