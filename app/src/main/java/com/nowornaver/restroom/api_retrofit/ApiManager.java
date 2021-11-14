package com.nowornaver.restroom.api_retrofit;

import com.nowornaver.restroom.data.RestroomData;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// RetrofitClient
public class ApiManager {
    // http://api.data.go.kr/openapi/tn_pubr_public_toilet_api?serviceKey=pcARzaI9VDoUfFCPAvEVJFnybdl8qYoOmlp03aQwVKw9y1x8s%2Bsj7nA5df%2FenVUC1RVLiR%2FDVoLnimmA268%2B%2Fw%3D%3D&pageNo=1&numOfRows=1&type=json

    private final String BaseUrl = "http://api.data.go.kr/";// openapi/tn_pubr_public_toilet_api/
    private final String serviceKey = "pcARzaI9VDoUfFCPAvEVJFnybdl8qYoOmlp03aQwVKw9y1x8s%2Bsj7nA5df%2FenVUC1RVLiR%2FDVoLnimmA268%2B%2Fw%3D%3D";

    private static final ApiManager apiManager = new ApiManager();
    private  RestroomService RestroomService;

    //singleton
    public static ApiManager getInstance(){
        return apiManager;
    }

    private ApiManager(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl) // BaseUrl 의 끝은 / 로 끝나야람.
                .addConverterFactory(GsonConverterFactory.create()) // json converter
                .client(client)
                .build();

        RestroomService = retrofit.create(RestroomService.class); // Retrofit 인스턴스로 객체구현
    }

    // 좌표 위도 latitude 경도 longitude


    /**
     * 위도 경도를 드가져오는 메소드
     * @param latitude 위도
     * @param longitude 경도
     * @return
     * ex) http://api.data.go.kr/openapi/tn_pubr_public_toilet_api?serviceKey=~~~~&latitude=36.8711506&longitude=128.5134172
     */
    public Call<RestroomData> getRestRoomLatitude(String latitude, String longitude){
        HashMap<String , String> map = new HashMap<>();
        map.put("serviceKey", serviceKey);
        map.put("latitude", latitude);
        map.put("longitude", longitude);

        return RestroomService.getRestroom(map);
    }

}
