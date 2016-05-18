package cn.edu.zzu.wemall.ui;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Jay on 2015/9/2 0002.
 */
public class SharedHelper {

    private Context mContext;

    public SharedHelper() {
    }

    public SharedHelper(Context mContext) {
        this.mContext = mContext;
    }
    

  //定义一个保存数据的方法
    /**
     * @param username
     * @param passwd
     * 1.获取sharepreference对象
     * 2.利用edit（）方法获取editor对象
     * 3.通过editor对象存储key-value键值对数据
     * 4.通过commit()方法提交数据
     */
    public void save(String username, String passwd) {
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.commit();
    }

  //定义一个读取SP文件的方法
    /**
     * @return
     * 1.获取sharepreference对象
     * 2.通过getString()方法获取字符数据
     */
    public Map<String, String> read() {
        Map<String, String> data = new HashMap<String, String>();
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        data.put("username", sp.getString("username", ""));
        data.put("passwd", sp.getString("passwd", ""));
        return data;
    }
}