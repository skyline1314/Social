package com.skyline.social.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.skyline.social.R;
import com.skyline.social.base.BaseActivity;

import java.util.ArrayList;

public class MapActivity extends BaseActivity implements OnGetPoiSearchResultListener {

    private RelativeLayout back_btn;
    private TextView title_text;
    private MapView map_view;
    private RecyclerView nearby_list;
    private ArrayList<PoiInfo> data = new ArrayList<>();
    private nearbyAdapter adapter;

    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private BaiduMap mBaiduMap;


    // 定位相关
    LocationClient mLocationClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private PoiSearch mPoiSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        mPoiSearch = PoiSearch.newInstance();// 初始化搜索模块，注册搜索事件监听
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        initView();
    }

    @Override
    public void doResume() {

    }

    private void initView() {
        back_btn = (RelativeLayout) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setText("请选位置信息");
        map_view = (MapView) findViewById(R.id.map_view);
        nearby_list = (RecyclerView) findViewById(R.id.nearby_list);
        adapter = new nearbyAdapter();
        nearby_list.setLayoutManager(new LinearLayoutManager(this));
        nearby_list.setAdapter(adapter);

        mBaiduMap = map_view.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocationClient = new LocationClient(this);
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(10);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {

        if (poiResult != null) {
            if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
                data.addAll(poiResult.getAllPoi());
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }


    private class nearbyAdapter extends RecyclerView.Adapter<nearbyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = getLayoutInflater().inflate(R.layout.listarray_location_item, null);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            PoiInfo item = data.get(position);
            holder.location_name.setText(item.name);
            holder.location_address.setText(item.address);
            holder.location_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //
                }
            });
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView location_name;
            private TextView location_address;
            private RelativeLayout location_root;

            public ViewHolder(View itemView) {
                super(itemView);
                this.location_name = itemView.findViewById(R.id.location_name);
                this.location_address = itemView.findViewById(R.id.location_address);
                this.location_root = itemView.findViewById(R.id.location_root);
            }
        }

    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || map_view == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    searchNeayBy();
                }
            }).start();

        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


    /**
     * 搜索周边地理位置
     * by hankkin at:2015-11-01 22:54:49
     */
    private void searchNeayBy() {
        PoiNearbySearchOption option = new PoiNearbySearchOption();
        option.keyword("写字楼");
        option.sortType(PoiSortType.distance_from_near_to_far);
        option.location(new LatLng(mCurrentLat, mCurrentLon));

        option.radius(1000);


        option.pageCapacity(20);
        mPoiSearch.searchNearby(option);

    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocationClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        map_view.onDestroy();
        map_view = null;
        super.onDestroy();
    }


}
