package com.example.da08.serverconnection;

import com.example.da08.serverconnection.domain.Bbs;
import com.example.da08.serverconnection.domain.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Da08 on 2017. 6. 30..
 */

// 도메인을 제외한 서버 주소
public interface BbsService {
    @POST("Bbs")
    Call<Result> createBbs(@Body Bbs bbs);
}
