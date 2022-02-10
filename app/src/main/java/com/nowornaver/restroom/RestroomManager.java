package com.nowornaver.restroom;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nowornaver.restroom.api_retrofit.ApiManager;
import com.nowornaver.restroom.data.RestroomData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestroomManager {

    private OnChangeRestroom onChangeRestroom;
    private ApiManager apiManager;
    private Activity activity;

    public RestroomManager(Activity activity, OnChangeRestroom onChangeRestroom) {

        this.activity = activity;
        this.onChangeRestroom = onChangeRestroom;

        apiManager = ApiManager.getInstance();

        // 위치 확인
        final LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        //위도 경도를 가져옴.
        if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 권한 없으면 메소드 실행 중지
            Toast.makeText(activity, "위치 권한이 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, mLocationListener, null);
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, mLocationListener, null);

    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            String lat = String.format("%.2f", latitude);
            String lon = String.format("%.2f", longitude);

            requestRestroomData();
        }
    };

    //loopItem
    int loopItemPageNo = 1;
    boolean isResponse = false;

    private void requestRestroomData() {

            Call<RestroomData> response = apiManager.getRestRoomRequestParam(loopItemPageNo);

            Log.d("RetrofitTest", "enqueue 전 loopItemPageNo :: " + loopItemPageNo + "isResponse :: "+isResponse);

            response.enqueue(new Callback<RestroomData>() {
                @Override
                public void onResponse(Call<RestroomData> call, Response<RestroomData> response) {
                    Log.d("Retrofit :: ", "requestRestRoomData : onResponse");

                    if (response.code() == 200) {
                        isResponse = true;
                        Log.d("RetrofitTest", "onResponse loopItemPageNo :: " + loopItemPageNo + "isResponse :: "+isResponse);
                        RestroomData restroomData = response.body();
                        onChangeRestroom.chang(restroomData);
                    } else {
                        isResponse = false;
                        Log.d("RetrofitTest", "code????? :: " + response.code());
                        getFailure();
                    }
                    RestroomData restroomData = response.body();

                    Log.d("Retrofit :: ", "item :: " + response.body().getResponse().getBody().getItems().size());

                    // TODO 첫 데이터들 DB에 저장 하고 다음 데이터 부터 넘겨줘야함
                    //loopItemPageNo = Integer.parseInt(response.body().getResponse().getBody().getPageNo());

                    //againResponse(restroomData, loopItemPageNo);

                    //String tc = response.body().getResponse().getBody().toString();
                    //Log.d("Retrofit :: ", "tc=" + tc);

                    //onChangeRestroom.chang(restroomData);
                }

                @Override
                public void onFailure(Call<RestroomData> call, Throwable t) {
                    Log.d("Retrofit", "requestRestRoomData : onFailure");
                    Log.d("Retrofit", "requestForecast error message : " + t.getMessage());

                    getFailure();

                }
            });
    }

    private void getFailure(){
        Toast.makeText(activity, "네트워크 연결을 확인해 주세요", Toast.LENGTH_SHORT).show();
        isResponse = false;
        //다이얼로그 종료
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).hideProgress();
        }
    }

    public interface OnChangeRestroom {
        void chang(RestroomData restroomData);
    }
}
