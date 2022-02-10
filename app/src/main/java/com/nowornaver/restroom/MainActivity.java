package com.nowornaver.restroom;

import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Base64;
import android.util.Log;

import android.view.ViewGroup;

import com.nowornaver.restroom.data.RestroomData;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/* MapViewEventListener interface를 구현하는 객체를 MapView 객체에 등록하여
 * 지도 이동/확대/축소, 지도 화면 터치(Single Tap / Double Tap / Long Press) 이벤트를 통보받을 수 있다.
 * */
public class MainActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.CurrentLocationEventListener {

    private static final String TAG = "MainActivity";

    private MapView mapView;
    private ViewGroup mapViewContainer;

    // Api 요청시에 사용
    private ProgressDialog pd;

    // private RestroomData restroomData;
    private RestroomData restroomData;
    private List<RestroomData.Item> restroomDataItems;
    private RestroomManager restroomManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showProgress("지도 요청 중입니다.");

        restroomManager = new RestroomManager(this, new RestroomManager.OnChangeRestroom() {
            @Override
            public void chang(RestroomData restroomData) {
                hideProgress();
                restroomDataItems = restroomData.getResponse().getBody().getItems();
                //setRestroomData(restroomDataItems);
                Log.d("MainActivity", "onCreate :: chang");
                getMapView();
            }
        });
        // getHashKey();
    }

    // 화장실 데이터를 가져오는 메소드
    public List<RestroomData.Item> getRestroomData() {
        return restroomDataItems;
    }

    public void setRestroomData(List<RestroomData.Item> restroomDataItems) {
        this.restroomDataItems = restroomDataItems;
    }

    /**
     * MapView 초기화 및 설
     */
    public void getMapView() {
        mapView = new MapView(this);

        mapViewContainer = findViewById(R.id.map_view);

        mapView.setMapViewEventListener(this);
        // 현위치 트랙킹 모드 On, 단말의 위치에 따라 지도 중심이 이동한다. 나침반 모드는 꺼진 상태 TrackingModeOff 일경우 비활성화
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
        mapViewContainer.addView(mapView);

        // 현위치 마커를 중심으로 그릴 원의 반경을 지정
        //mapView.setCurrentLocationRadius(METER);

        GpsService gpsService = new GpsService(this);
        double lat = gpsService.getLatitude();
        double lon = gpsService.getLongitude();
        // 지도에 현제 좌표
//        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(37.5514579595, 126.951949155);
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(lat, lon);

        // true 면 앱 실행시 애니메이션 효과 나오고 false 면 애니메이션 효과가 안나옴

        //MapPoint[] mapPointArray = {mapPoints};
        //mapView.fitMapViewAreaToShowMapPoints(mapPoints);
        // 중심
        mapView.setMapCenterPoint(mapPoint, true);
        // 줌 레벨
        mapView.setZoomLevel(4, true);

        // 현제 위치 마커 표시
        getMarker(mapPoint);
    }

    /**
     * @param mapPoint 좌표
     */
    public void getMarker(MapPoint mapPoint) {
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker(임시)");
        // Item 의 좌표를 설정
        //marker.setMapPoint(mapPoint);
        // 이게 널임
        List<RestroomData.Item> mapPoints = getRestroomData();

        ArrayList<MapPOIItem> markerArray = new ArrayList<>();

        for (RestroomData.Item item : mapPoints) {
            MapPOIItem markers = new MapPOIItem();

            //marker.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(item.getLatitude()), Double.parseDouble(item.getLongitude())));
            marker.setMapPoint(mapPoint);
            marker.setItemName(item.getToiletNm());
            markerArray.add(markers);
        }

        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItems(markerArray.toArray(new MapPOIItem[markerArray.size()]));
        //mapView.addPOIItem(marker);
    }

    /**
     * 애플리케이션 Key 값 가져오는 함수
     * app 등록을 위해 사용했음
     * 2021.09.12 노민우
     */
    private void getHashKey() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    /**
     * MapView가 사용가능 한 상태가 되었음을 알려준다.
     * onMapViewInitialized()가 호출된 이후에 MapView 객체가 제공하는 지도 조작 API 를 사용할 수 있다.
     *
     * @param mapView
     */
    @Override
    public void onMapViewInitialized(MapView mapView) {
        mapView.setMapViewEventListener(this);
        //mapView.setPOIItemEventListener(this);
    }

    /**
     * 지도 중심 좌표가 이동한 경우 호출된다.
     *
     * @param mapView  맵뷰
     * @param mapPoint 맵 좌표
     */
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    // 지도 확대/축소 레벨이 변경된 경우 호출된다.
    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    // 사용자가 지도 위를 터치한 경우 호출된다.
    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    // 사용자가 지도 위 한 지점을 더블 터치한 경우 호출된다.
    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    // 사용자가 지도 위 한 지점을 길게 누른 경우(long press) 호출된다.
    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    // 사용자가 지도 위 한 지점을 길게 누른 경우(long press) 호출된다.
    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    // 사용자가 지도 드래그를 끝낸 경우 호출된다.
    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    // 사용자가 지도 드래그를 끝낸 경우 호출된다.
    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {

    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    //ProgressDialog 출력 메소드
    public void showProgress(String message) {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }

        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage(message);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    //다이얼로그 종료 메소드
    public void hideProgress() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }
}