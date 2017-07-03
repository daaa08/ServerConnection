package com.example.da08.serverconnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.da08.serverconnection.domain.Bbs;
import com.example.da08.serverconnection.domain.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WriteActivity extends AppCompatActivity {


    EditText editTitle;
    EditText editAuthor;
    EditText editContent;
    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editAuthor = (EditText) findViewById(R.id.editAuthor);
        editContent = (EditText) findViewById(R.id.editContent);
        btnWrite = (Button) findViewById(R.id.btnWrite);

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
//                Gson gson = new Gson();
//                String jsonString = gson.toJson(bbs);

                // retrofit정의
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://172.30.1.15:8080/")   // 도메인 주소 끝에 /꼭 써줘야 함!!!!!!!!!!!!!!!
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                // 실제 서비스 인터페이스 생성
                BbsService service = retrofit.create(BbsService.class);
                // 서비스 호출
                Call<Result> call = service.createBbs(bbs);

                // 서비스를 서브 thread 로 실행 - 비동기로 실행
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        // 전송결과가 정상이면
                        Log.e("Write","in ====== onResponse");
                        if(response.isSuccessful()){
                            // 응답객체에서 바디를 꺼내서 담는다.
                            Result result = response.body();
                            Log.e("Write","결과처리="+result.result);
                            finish();
                        }else{
                            Log.e("Write","not ok="+response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.e("Write","error="+t.getMessage());
                    }
                });

//                Intent intent = new Intent(WriteActivity.this, MainActivity.class);
//                startActivity(intent);

            }
        });
    }
}
