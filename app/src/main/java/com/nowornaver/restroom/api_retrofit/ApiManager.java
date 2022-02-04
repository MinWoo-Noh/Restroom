package com.nowornaver.restroom.api_retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nowornaver.restroom.data.RestroomData;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// RetrofitClient
public class ApiManager {
    // http://api.data.go.kr/openapi/tn_pubr_public_toilet_api?serviceKey=XlEGlfNRcXG2l3NX32fx%2BNToWmxLKewQAIeaVZTz5ZVnwtGXpxbKlMo%2Bi1YpM5wLjBToPg1pRsesHf7iTAjOdQ%3D%3D&pageNo=1&numOfRows=1&type=json
    private final String BaseUrl = "http://api.data.go.kr/";// openapi/tn_pubr_public_toilet_api/
    private final String serviceKey = "XlEGlfNRcXG2l3NX32fx+NToWmxLKewQAIeaVZTz5ZVnwtGXpxbKlMo+i1YpM5wLjBToPg1pRsesHf7iTAjOdQ==";

    private static final ApiManager apiManager = new ApiManager();
    private RestroomService restroomService;

    //singleton
    public static ApiManager getInstance() {
        return apiManager;
    }

    /**
     * 위도 경도를 드가져오는 메소드
     *
     * @return ex) http://api.data.go.kr/openapi/tn_pubr_public_toilet_api?serviceKey=~~~~&latitude=36.8711506&longitude=128.5134172
     */
    public Call<RestroomData> getRestRoomRequestParam(int pageNo) {
        HashMap<String, String> map = new HashMap<>();
        map.put("serviceKey", serviceKey);
        map.put("pageNo", String.valueOf(pageNo));
        map.put("numOfRows", "500");
        map.put("type", "json");

        return restroomService.getRestroom(map);
    }

    public Call<RestroomData> getLoopRestRoomRequestParam(int pageNo) {
        HashMap<String, String> map = new HashMap<>();
        map.put("serviceKey", serviceKey);
        map.put("pageNo", String.valueOf(pageNo));
        map.put("numOfRows", "100");
        map.put("type", "json");

        return restroomService.getRestroom(map);
    }

    private ApiManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl) // BaseUrl 의 끝은 / 로 끝나야람.
                .addConverterFactory(GsonConverterFactory.create(gson)) // json converter
                .client(client)
                .build();

        restroomService = retrofit.create(RestroomService.class); // Retrofit 인스턴스로 객체구현
    }
}
