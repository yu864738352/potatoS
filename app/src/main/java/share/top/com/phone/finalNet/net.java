package share.top.com.phone.finalNet;

import android.util.Log;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by ZHOU on 2016/3/10.
 */
public class net extends Thread {
    public static final String path = "http://apis.juhe.cn/cook/query?key=&menu=%E8%A5%BF%E7%BA%A2%E6%9F%BF&rn=10&pn=3";

    public void getConnection(String key, String menu) {
        URL url = null;
        HttpURLConnection connection = null;
        InputStream in = null;
        BufferedReader buffer;
        StringBuffer mBuffer = new StringBuffer();
        try {
            url = new URL(path);
            String temp = "";
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.connect();
            in = connection.getInputStream();
            buffer = new BufferedReader(new InputStreamReader(in, "utf-8"));
            while ((temp = buffer.readLine()) != null) {
                mBuffer.append(temp);
                mBuffer.append("\r\n");
            }
            connection.disconnect();
            in.close();
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void JsonData(String datas) {
        try {
            JSONObject resultO = JSON.parseObject(datas);
            String reason = resultO.getString("Success");
            if (reason.equals("Success")) {
                JSONObject result = resultO.getJSONObject("result");
                JSONArray data = result.getJSONArray("data");

            } else
                Log.i("MSG", "解析失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
    }
}
