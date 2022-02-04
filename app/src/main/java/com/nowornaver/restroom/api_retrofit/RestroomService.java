package com.nowornaver.restroom.api_retrofit;

import com.nowornaver.restroom.data.RestroomData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RestroomService {

    // http://api.data.go.kr/openapi/tn_pubr_public_toilet_api
    @GET("openapi/tn_pubr_public_toilet_api") // 전송방식
    Call<RestroomData> getRestroom(@QueryMap Map<String, String> options); // Call<응닫받을 데이터형> 함수명 (서버에 전달할 데이터)
//    Call<RestroomData> getRestroom(@Query("serviceKey") String key, @Path("PageNo") String PageNo , @Query("numOfRows") int numOfRows, @Query("type") String type); // Call<응닫받을 데이터형> 함수명 (서버에 전달할 데이터)
}