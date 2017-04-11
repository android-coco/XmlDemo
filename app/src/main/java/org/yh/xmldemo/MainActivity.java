package org.yh.xmldemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity
{

    //装载Beauty类型的链表，其内容由XML文件解析得到
    private VitalSignsInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //从文件读取
        try
        {
            //通过assertmanager的open方法获取到beauties.xml文件的输入流
            InputStream is = this.getAssets().open("beauties.xml");
            //初始化自定义的实现类BeautyParserImpl
            BeautyParserImpl pbp = new BeautyParserImpl();
            //调用pbp的parse()方法，将输入流传进去解析，返回的链表结果赋给beautyList
            info = pbp.parse(is);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        setupViews();
    }

    public  String replaceBlank(String str)
    {
        String dest = "";
        if (str != null)
        {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        return dest;
    }

    /**
     * 将数据显示到手机界面上
     */
    private void setupViews()
    {
        //String result = info.toString();

        TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText(result);
        BeautyParserImpl pbp = new BeautyParserImpl();
        VitalSignsInfo info = new VitalSignsInfo();
        info.setPatientID("000091381000");
        info.setVsitID("1");
        ArrayList<DetailInfo> list = new ArrayList<>();
        for (int i = 0; i < 1; i++)
        {
            DetailInfo detailinfo = new DetailInfo();
            detailinfo.setMeasureType("TW");
            detailinfo.setMeasureValue((30 + i * 5) + "");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(new Date());
            detailinfo.setMeasureDateTime(time);
            detailinfo.setRecorderName("护士1");
            detailinfo.setRecorderID("00375");
            list.add(detailinfo);
        }
        info.setDetailInfos(list);
        String result = "";
        try
        {
            //Log.e("成功：", pbp.serialize(info));
            //textView.setText(pbp.serialize(info));
            //模拟数据网络
            result = pbp.serialize(info);
        }
        catch (Exception e)
        {
            //Log.e("失败：", e.getMessage());
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("xml", result);
        Log.e("网络参数：", result);
//        YHOkHttp.post("", "http://192.168.0.197:8080/php1/Login.php", params, new HttpCallBack()
//        {
//            @Override
//            public void onSuccess(String t)
//            {
//                super.onSuccess(t);
//                Log.e("网络成功：", t);
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg)
//            {
//                super.onFailure(errorNo, strMsg);
//                Log.e("网络失败：", strMsg);
//            }
//
//            @Override
//            public void onFinish()
//            {
//                super.onFinish();
//            }
//        }, "");


        ByteArrayInputStream tInputStringStream = null;
        try
        {
            if (result != null && !result.trim().equals(""))
            {
                tInputStringStream = new ByteArrayInputStream(result.getBytes());
                info = pbp.parse(tInputStringStream);
                textView.setText(info.toString());
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
            return;
        }
    }
}
