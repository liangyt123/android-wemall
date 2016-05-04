package cn.edu.zzu.wemall.ui;

import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.location.LocationClientOption.LocationMode;

import cn.edu.zzu.wemall.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Location_service extends Activity{
	
	private Button btn_back_MainUi;
	private EditText et_write_location=null;
	private Button btn_search_location;
	private TextView tv_location_result=null;
	private Button btn_dingwei;
	private LocationClient mLocationClient=null;
	private BDLocationListener myListener=new MyLocationListener();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_service);
		
		mLocationClient=new LocationClient(getApplicationContext());
		mLocationClient.registerLocationListener(myListener);
		
		btn_back_MainUi=(Button) findViewById(R.id.btn_back_MainUi);
		et_write_location=(EditText) findViewById(R.id.et_write_location);
		btn_search_location=(Button) findViewById(R.id.btn_search_location);
		tv_location_result=(TextView) findViewById(R.id.tv_location_result);
		btn_dingwei=(Button) findViewById(R.id.btn_dingwei);
		
		btn_dingwei.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				init();
				mLocationClient.start();
			}
		});
		
		btn_back_MainUi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Location_service.this,MainUIMain.class);
				//if(et_write_location==null){
				intent.putExtra("location", tv_location_result.getText());
				//}
				/*else {
			    intent.putExtra("location", et_write_location.getText());
				}*/
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
			}
		});
		
	}
	
	private class MyLocationListener implements BDLocationListener{
		// TODO Auto-generated method stub
			
			@Override
			public void onReceiveLocation(BDLocation location) {
				// TODO Auto-generated method stub
				//Receive Location
				StringBuffer sb = new StringBuffer(256);
				/*sb.append("time : ");
				sb.append(location.getTime());
				sb.append("\nerror code : ");
				sb.append(location.getLocType());
				sb.append("\nlatitude : ");
				sb.append(location.getLatitude());
				sb.append("\nlontitude : ");
				sb.append(location.getLongitude());
				sb.append("\nradius : ");
				sb.append(location.getRadius());*/
				if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
					/*sb.append("\nspeed : ");
					sb.append(location.getSpeed());// 单位：公里每小时
					sb.append("\nsatellite : ");
					sb.append(location.getSatelliteNumber());
					sb.append("\nheight : ");
					sb.append(location.getAltitude());// 单位：米
					sb.append("\ndirection : ");
					sb.append(location.getDirection());// 单位度
					sb.append("\naddr : ");
					sb.append(location.getAddrStr());
					sb.append("\ndescribe : ");*/
					//sb.append("gps定位成功");

				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
					/*sb.append("\naddr : ");
					sb.append(location.getAddrStr());
					//运营商信息
					sb.append("\noperationers : ");
					sb.append(location.getOperators());
					sb.append("\ndescribe : ");*/
					//sb.append("网络定位成功");
				} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
					sb.append("\ndescribe : ");
					sb.append("离线定位成功，离线定位结果也是有效的");
				} else if (location.getLocType() == BDLocation.TypeServerError) {
					sb.append("\ndescribe : ");
					sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
				} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
					sb.append("\ndescribe : ");
					sb.append("网络不同导致定位失败，请检查网络是否通畅");
				} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
					sb.append("\ndescribe : ");
					sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
				}
				//sb.append("\nlocationdescribe : ");
				//sb.append(location.getLocationDescribe());// 位置语义化信息
				List<Poi> list = location.getPoiList();// POI数据
				if (list != null) {
					/*sb.append("\npoilist size = : ");
					sb.append(list.size());*/
					String temp="a";
					for (Poi p : list) {
						String str=p.getName();
						//sb.append("\npoi= : ");
						//sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
						//从所有结果中选取最长的作为结果
						if(temp.length()<str.length()){temp=str;}
					}
					sb.append(temp);
				}
				logMsg(sb.toString());
			}
	}

	public void init() {
		// TODO Auto-generated method stub
		
		
		LocationClientOption mOption=new LocationClientOption();
		//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		mOption.setLocationMode(LocationMode.Hight_Accuracy);
		//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
		mOption.setCoorType("bd09ll");
		//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		mOption.setScanSpan(3000);
		//可选，设置是否需要地址信息，默认不需要
		mOption.setIsNeedAddress(true);
		//可选，设置是否需要地址描述
		mOption.setIsNeedLocationDescribe(true);
		//可选，设置是否需要设备方向结果
		mOption.setNeedDeviceDirect(false);
		//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		mOption.setLocationNotify(false);
		//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，
		//设置是否在stop的时候杀死这个进程，默认不杀死   
		mOption.setIgnoreKillProcess(true);
		//可选，默认false，设置是否需要位置语义化结果，可以在
		//BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
		mOption.setIsNeedLocationDescribe(true);
		//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
		mOption.setIsNeedLocationPoiList(true);
		//可选，默认false，设置是否收集CRASH信息，默认收集
		mOption.SetIgnoreCacheException(false);
	    mLocationClient.setLocOption(mOption);

	}
	
	private void logMsg(String str){
		tv_location_result.setText(str);
	}

}
