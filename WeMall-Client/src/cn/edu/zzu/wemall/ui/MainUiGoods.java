package cn.edu.zzu.wemall.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.zzu.wemall.R;
import cn.edu.zzu.wemall.mylazylist.LazyAdapter;
import cn.edu.zzu.wemall.myview.XListView;
import cn.edu.zzu.wemall.myview.XListView.IXListViewListener;
import cn.edu.zzu.wemall.net.NetGoodsData;
import cn.edu.zzu.wemall.net.NetGoodsType;
import cn.edu.zzu.wemall.object.GoodsItem;
import cn.zzu.edu.wemall.utils.Utils;

import com.umeng.analytics.MobclickAgent;

/**
 * Author Liu Dewei
 * 
 * 商品列表
 * 
 * */

public class MainUiGoods extends ListFragment implements OnClickListener,
		IXListViewListener {
	private ArrayList<GoodsItem> Items, DisplayItem;
	private ArrayList<GoodsItem> myDisplayItem;
	private Handler handler = null;
	private Handler handler2 = null;
	private View view;
	private LazyAdapter adapter;
	private ViewGroup topbar, list;
	private ProgressBar loading;
	private TextView reload;
	private int typeid = -1;
	private boolean PullRefresh = true;
	private int num = 30;// 每次显示的条目数，多余的隐藏到上拉加载更多里面
	private int group = 1;// 显示第几组数据，分段加载需要
	
	private ArrayList<HashMap<String, Object>> menudata;
	public LinearLayout type1,type2,type3,type4,type5,type6,type7,type8;
	private TextView type1name,type2name,type3name,type4name,type5name,type6name,type7name,type8name;

	@SuppressLint("InflateParams")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.wemall_tab_goods, null);
		topbar = (ViewGroup) view.findViewById(R.id.errnet);
		reload = (TextView) view.findViewById(R.id.error_notice_button1);
		list = (ViewGroup) view.findViewById(R.id.list);
		loading = (ProgressBar) view.findViewById(R.id.good_loadingBar);
		reload.setOnClickListener(this);
		
		type1=(LinearLayout)view.findViewById(R.id.type1);
		type1name=(TextView)view.findViewById(R.id.type1name);
		type2=(LinearLayout)view.findViewById(R.id.type2);
		type2name=(TextView)view.findViewById(R.id.type2name);
		type3=(LinearLayout)view.findViewById(R.id.type3);
		type3name=(TextView)view.findViewById(R.id.type3name);
		type4=(LinearLayout)view.findViewById(R.id.type4);
		type4name=(TextView)view.findViewById(R.id.type4name);
		type5=(LinearLayout)view.findViewById(R.id.type5);
		type5name=(TextView)view.findViewById(R.id.type5name);
		type6=(LinearLayout)view.findViewById(R.id.type6);
		type6name=(TextView)view.findViewById(R.id.type6name);
		type7=(LinearLayout)view.findViewById(R.id.type7);
		type7name=(TextView)view.findViewById(R.id.type7name);
		type8=(LinearLayout)view.findViewById(R.id.type8);
		type8name=(TextView)view.findViewById(R.id.type8name);
		
		
		type1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainUIMain.titleText.setText((String) menudata.get(1).get("name"));
				MainUIMain.Goods.resetgroup();
				MainUIMain.Goods.initview(Integer.parseInt(menudata.get(1)
						.get("id").toString()));
				MainUIMain.Goods.resetlist();
			}
		});
        type2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainUIMain.titleText.setText((String) menudata.get(2).get("name"));
				MainUIMain.Goods.resetgroup();
				MainUIMain.Goods.initview(Integer.parseInt(menudata.get(2)
						.get("id").toString()));
				MainUIMain.Goods.resetlist();
			}
		});
        type3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainUIMain.titleText.setText((String) menudata.get(3).get("name"));
				MainUIMain.Goods.resetgroup();
				MainUIMain.Goods.initview(Integer.parseInt(menudata.get(3)
						.get("id").toString()));
				MainUIMain.Goods.resetlist();
			}
		});
        type4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainUIMain.titleText.setText((String) menudata.get(4).get("name"));
				MainUIMain.Goods.resetgroup();
				MainUIMain.Goods.initview(Integer.parseInt(menudata.get(4)
						.get("id").toString()));
				MainUIMain.Goods.resetlist();
			}
		});
        type5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainUIMain.titleText.setText((String) menudata.get(5).get("name"));
				MainUIMain.Goods.resetgroup();
				MainUIMain.Goods.initview(Integer.parseInt(menudata.get(5)
						.get("id").toString()));
				MainUIMain.Goods.resetlist();
			}
		});
        type6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainUIMain.titleText.setText((String) menudata.get(6).get("name"));
				MainUIMain.Goods.resetgroup();
				MainUIMain.Goods.initview(Integer.parseInt(menudata.get(6)
						.get("id").toString()));
				MainUIMain.Goods.resetlist();
			}
		});
        type7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainUIMain.titleText.setText((String) menudata.get(7).get("name"));
				MainUIMain.Goods.resetgroup();
				MainUIMain.Goods.initview(Integer.parseInt(menudata.get(7)
						.get("id").toString()));
				MainUIMain.Goods.resetlist();
			}
		});
        type8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainUIMain.titleText.setText((String) menudata.get(8).get("name"));
				MainUIMain.Goods.resetgroup();
				MainUIMain.Goods.initview(Integer.parseInt(menudata.get(8)
						.get("id").toString()));
				MainUIMain.Goods.resetlist();
			}
		});
		
		return view;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Items = new ArrayList<GoodsItem>();
		
		menudata = new ArrayList<HashMap<String, Object>>();
		
		DisplayItem = new ArrayList<GoodsItem>();
		adapter = new LazyAdapter(getActivity(), DisplayItem);
		setListAdapter(adapter);
		// /获取并更新数据
		Getdate();
	}

	public void onViewCreated(View view, Bundle savedInstanceState) {
		((XListView) getListView()).setPullRefreshEnable(PullRefresh);
		((XListView) getListView()).setPullLoadEnable(false);
		((XListView) getListView()).setXListViewListener(this);
	};

	@SuppressLint("HandlerLeak")
	private void Getdate() {

		// 开一条子线程加载网络数据
		Runnable runnable = new Runnable() {
			public void run() {
				// xmlwebData解析网络中xml中的数据
				Items = NetGoodsData.getData();
				// 发送消息
				handler.sendEmptyMessage(0x11021);
			}
		};
		
		Runnable runnable2 = new Runnable() {
			public void run() {
				// xmlwebData解析网络中xml中的数据
				try {
					menudata = NetGoodsType.getData();
				} catch (Exception e) {

				}
				// 发送消息，并把persons结合对象传递过去
				handler2.sendEmptyMessage(0x11024);
			}
		};
		try {
			// 开启线程
			new Thread(runnable2).start();
			// handler与线程之间的通信及数据处理
			handler2 = new Handler() {
				public void handleMessage(Message msg) {
					if (msg.what == 0x11024) {
						type1name.setText(menudata.get(1).get("name").toString());
						type2name.setText(menudata.get(2).get("name").toString());
						type3name.setText(menudata.get(3).get("name").toString());
						type4name.setText(menudata.get(4).get("name").toString());
						type5name.setText(menudata.get(5).get("name").toString());
						type6name.setText(menudata.get(6).get("name").toString());
						type7name.setText(menudata.get(7).get("name").toString());
						type8name.setText(menudata.get(8).get("name").toString());
					}
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// 开启线程
			new Thread(runnable).start();
			handler = new Handler() {
				public void handleMessage(Message msg) {
					if (msg.what == 0x11021) {
						group = 1;
						loading.setVisibility(View.GONE);
						onLoad();
						// 判断数据获取状态,提示失败,或者初始化数据
						if (Items == null) {
							topbar.setVisibility(View.VISIBLE);
							list.setVisibility(View.GONE);
							// 此处添加屏幕提示,让用户尝试手动加载并告知可能存在的网络异常
						} else {
							// 首次初始化,使用全部分类,全部分类标志符为-1
							initview(typeid);
						}
					}
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 初始化ListView,我们在这里使用typeid来标识要用那些数据初始化
	//以供分类时使用,如果初始化全部商品则使用-1
	void initview(int typeid) {
		
		
		System.out.println("要搜索的是："+MainUIMain.search_str);
		
		((XListView) getListView()).setPullLoadEnable(true);
		this.typeid = typeid;
		DisplayItem = new ArrayList<GoodsItem>();
		
		try {
			DisplayItem = (ArrayList<GoodsItem>) DeepCopy(Items);
			
			if (!(typeid == -1)) {
				// 处理要显示的数据,过滤掉不需要的,亲,不要从小往大循环,想想为什么

				for (int i = DisplayItem.size() - 1; i >= 0; i--) {
					if (!(DisplayItem.get(i).getTypeId() == typeid)) {
						DisplayItem.remove(i);
					}
				}
			}
			
			if(MainUIMain.search_str!=null) {
				for (int i = DisplayItem.size() - 1; i >= 0; i--) {
					if(!DisplayItem.get(i).getName().contains(MainUIMain.search_str)) {
						DisplayItem.remove(i);
					}
				}
			}
			
			if (DisplayItem.size() > ((group == 1) ? num : num * (group - 1))) {
				for (int i = DisplayItem.size() - 1; i >= (num * group); i--) {
					DisplayItem.remove(i);
				}
			} else {
				((XListView) getListView()).setPullLoadEnable(false);
				if (group > 1) {
					Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT)
							.show();
				}

			}
			
			//
			adapter.set_datasource(DisplayItem);
			adapter.notifyDataSetChanged();
			onLoad();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*// 初始化ListView,我们在这里使用typeid来标识要用那些数据初始化
		//以供分类时使用,如果初始化全部商品则使用-1
	void myinitview(String str) {
		System.out.println("是否进入这个函数"+str);
		((XListView) getListView()).setPullLoadEnable(true);
		myDisplayItem = new ArrayList<GoodsItem>();

			try {
				System.out.println("是否进入try这个函数"+myDisplayItem.size());
				myDisplayItem = (ArrayList<GoodsItem>) DeepCopy(Items);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (myDisplayItem.size() > ((group == 1) ? num : num * (group - 1))) {
				for (int i = myDisplayItem.size() - 1; i >= (num * group); i--) {
					myDisplayItem.remove(i);
				}
			} else {
				((XListView) getListView()).setPullLoadEnable(false);
				if (group > 1) {
					Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT)
							.show();
					}

				}
			for(int i=0;i<myDisplayItem.size();i++){
				System.out.println("第一个");
				if(!myDisplayItem.get(i).getName().contains(str)){
					System.out.println("第二个");
					System.out.println("myDisplayItem的值是："+myDisplayItem.get(i).getName());
					myDisplayItem.remove(i);
				
			}
			System.out.println("第三个");
			adapter.set_datasource(myDisplayItem);
			adapter.notifyDataSetChanged();
			onLoad();
			}
		}*/

	// 此处坑多！需要修改，未加载的情况下Count=0
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (((LazyAdapter) this.getListAdapter()).getCount() == 0) {
			//Toast.makeText(getActivity(), "正在加载中，请稍候...", Toast.LENGTH_SHORT).show();
		} else if (((LazyAdapter) this.getListAdapter()).getCount() > 0) {
			if (position == 0) {
				//Toast.makeText(getActivity(), "正在加载中，请稍候...",Toast.LENGTH_SHORT).show();
			} else if (position > 0) {
				final GoodsItem thisitem = ((LazyAdapter) this.getListAdapter())
						.getGoodItem((PullRefresh == true) ? position - 1
								: position);
				Intent i = new Intent(getActivity(), ItemDetails.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("t", thisitem);
				i.putExtras(bundle);
				startActivityForResult(i, 0x222);
			}
		}
	}
	// List深度拷贝,保留Items,以备切换分类时从本地获取而不必联网获取
	public List<GoodsItem> DeepCopy(List<GoodsItem> src) throws IOException,
			ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(
				byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		List<GoodsItem> dest = (List<GoodsItem>) in.readObject();
		return dest;
	}

	public void resetgroup() {
		group = 1;
	}

	public void resetlist() {
		getListView().setSelection(0);
	}

	// U盟统计
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("商品浏览"); // 统计页面
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("商品浏览");
	}

	@Override
	public void onClick(View viewid) {
		switch (viewid.getId()) {
		case R.id.error_notice_button1:
			if (Utils.isNetworkConnected(getActivity())) {
				((XListView) getListView()).CorrectTime();// 校准上次加载时间
				// 刷新分类和商品列表
				((MainUIMain) getActivity()).RefreshMenu();
				Getdate();
				topbar.setVisibility(View.GONE);
				list.setVisibility(View.VISIBLE);
				loading.setVisibility(View.VISIBLE);
			} else {
				Toast.makeText(getActivity(), "网络不可用,呜呜...", Toast.LENGTH_SHORT)
						.show();
			}

			break;
		default:
			break;
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (resultCode) {
		// 从详情页退出时捕获的结果,根据结果决定是否刷新购物车
		case 0x222:
			boolean flag = (boolean) data.getBooleanExtra("flag", true);
			if (flag == true) {
				((MainUIMain) getActivity()).refreshcart();
				;
			} else {

			}
			// 从详情页退出时捕获的结果,根据结果决定是否前往购物车
			boolean flag2 = (boolean) data.getBooleanExtra("flag2", false);
			if (flag2 == true) {
				((MainUIMain) getActivity()).gotocart();
			} else {

			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		if (Utils.isNetworkConnected(getActivity())) {
			// 刷新分类和商品列表
			((MainUIMain) getActivity()).RefreshMenu();
			Getdate();
		} else {
			Toast.makeText(getActivity(), "网络不可用,呜呜...", Toast.LENGTH_SHORT)
					.show();
			onLoad();
		}
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		group++;
		initview(typeid);

	}

	private void onLoad() {
		((XListView) getListView()).stopRefresh();
		((XListView) getListView()).stopLoadMore();
	}
}
