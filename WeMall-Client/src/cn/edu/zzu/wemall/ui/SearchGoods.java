package cn.edu.zzu.wemall.ui;

import cn.edu.zzu.wemall.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchGoods extends Activity{
	
	private EditText et_search_goods;
	private Button btn_search_goods;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_goods);
		setTitle("搜索商品");
		et_search_goods=(EditText) findViewById(R.id.text_searchgoods);
		btn_search_goods=(Button) findViewById(R.id.btn_search_goods);
		
		btn_search_goods.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			/*	if(et_search_goods.getText().toString()==null){
					Toast.makeText(getApplicationContext(), "请输入商品再进行搜索",
							Toast.LENGTH_LONG).show();
					return;
				}*/
				
				Intent in=new Intent(SearchGoods.this,MainUIMain.class);
				in.putExtra("searchName", et_search_goods.getText().toString());
				in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(in);
			}
		});
	}
}
