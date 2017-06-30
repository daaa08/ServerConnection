package com.example.da08.serverconnection;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void run(String url) {
        // 서브 스레드에서 실행
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String result = null;
                try {
                    result = getData(params[0]);
                } catch(Exception e) {
                    Log.e("Main", e.getMessage());
                }
                return result;  // onPostExecute 의 값으로 들어감
            }

            @Override
            protected void onPostExecute(String result) {
                // 결과 값인 jsonString을 객체로 변환

                // listView의 adapter에 세팅

                // listView에 notify하기
            }

        }.execute(url);
    }

    private String getData(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // 화면 xml에 onClick은 위젯 속성이 android:onClick:"btnPost"  추가
    public void btnPost(View view){
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);
    }
}