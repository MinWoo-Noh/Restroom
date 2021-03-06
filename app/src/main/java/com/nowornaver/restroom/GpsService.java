package com.nowornaver.restroom;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * Gps 서비스를 위한 클래스
 * 현재위치를 가져와 주소로 변환처리를 함.
 */
public class GpsService extends Service implements LocationListener {

    private final static String TAG = GpsService.class.getName();

    private final Context mContext;
    private Location location;

    public double latitude; // 위도
    public double longitude; // 경도

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 거리 업데이트
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // gps 받아오는 갱신 시간
    protected LocationManager locationManager;

    public GpsService(Context context) {
        this.mContext = context;
        getLocation();
    }

    // Location (위치) 가저오는 함수
    public Location getLocation() {
        try {
            // Acquire a reference to the system Location Manager (시스템 위치 관리자에대한 참조 획득)
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            // GPS provider(제공자)사용가능 여부
            boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            // 네트워크 provider 사용가능 여부
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGpsEnabled && isNetworkEnabled) {
                // 사용 가능하지 않을경우
            } else {
                // 네트워크 및 GPS 퍼미션 체크하기위한 변수
                int hasFindLocationPermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);
                int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION);

                if (!(hasFindLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED)) {
                    return null;
                }

                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location == null) {
                            // location 이 null 일 경우 좌표 가져오기
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                if (isGpsEnabled) {
                    if (location == null && locationManager != null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            // location 이 null 일 경우 좌표 가져오기
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

        return location;
    }

    public double getLatitude() {

        if (location != null) {
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude() {

        if (location != null) {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void stopUsingGps() {
        if (locationManager != null) {
            locationManager.removeUpdates(GpsService.this);
        }
    }
}
