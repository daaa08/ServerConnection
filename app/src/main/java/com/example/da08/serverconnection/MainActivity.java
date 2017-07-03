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

import com.example.da08.serverconnection.domain.Data;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    // 리모트 관련 설정
    final String DOMAIN = "http://172.30.1.15:8080";
    final String SERVERPATH = "/Bbs/List";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

    private void load(){
        run(DOMAIN+SERVERPATH);
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
                Gson gson = new Gson();
                Data data = gson.fromJson(result, Data.class);
                // listView의 adapter에 세팅
                adapter.setData(data.bbsList);

                // listView에 notify하기
                adapter.notifyDataSetChanged();
            }

        }.execute(url);
    }

    private String getData(String url) throws IOException {
        // 요청 정보를 담고 있는 객체
        Request request = new Request.Builder()
                .url(url)
                .build();

        // 응답 정보를 담고 있는 객체
        Response response = null;
                  // 서버로 요청
        response = client.newCall(request).execute();
        ResponseBody resBody = response.body();  // body 실제 우리가 봐야하는 내용들(html)을
        return resBody.string();   // String으로 받겠다는 의미
    }

    // 화면 xml에 onClick은 위젯 속성이 android:onClick:"btnPost"  추가
    public void btnPost(View view){
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);
    }
}