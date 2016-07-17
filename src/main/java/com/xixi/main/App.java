package com.xixi.main;


import com.xixi.utils.HttpUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *{"charset":"utf8","res":{"list":{"cookiepre":"GRHR_2132_","auth":"6cf67jBWQeXKDTT0ZgSaju5qoP84DlQaeibBRsdfxMmabN2b8BzYtbN6yHl2V9wp5V22Aa8Zz28dZ46uzAdXBrQcy5k","saltkey":"oA0AfNXf","appsites":{"QicC_":{"cookiepre":"QicC_2132_","url":"http:\/\/bbs.jsw.com.cn\/bbs\/","auth":"7b68DyGT4RnSBC2t+zLmPyIfIDnDWr7zrFudG4DWFRrYTx2f+QxR02V3Tp\/Z7tTaOwG4HjT7yfNVAHPL8RjDxUYG90A","saltkey":"vDdA026j"},"3q1K_":{"cookiepre":"3q1K_2132_","url":"http:\/\/v.m.zjxuexi.com\/a\/","auth":"e1dd4wdXWLRFg8yTDPh\/6LE5VG72EetUTH+fcYNfNEcHRRcQVGJa5GpZYlZX0GJ1Kf32htdZwb+VxIDZNH2m+acTwlw","saltkey":"jT8FI3tT"}},"member_uid":"196391","member_username":"user174165","member_mobile":"18852866178","groupid":"10","formhash":"6cf67jBWQeXKDTT0ZgSaju5qoP84DlQaeibBRsdfxMmabN2b8BzYtbN6yHl2V9wp5V22Aa8Zz28dZ46uzAdXBrQcy5k","ismoderator":null,"member_properties":{"uid":"196391","mobile":"","isvalid":"0","field1":"eyJzc19iaW5kaW5nc3kiOiIiLCJzc19iaW5kaW5nIjoiMCIsInNzX3Bhc3N3b3JkIjoiIiwic3NfdXNlcm5hbWUiOiIiLCJzc19iaW5kaW5neWIiOiIiLCJzc19iaW5kaW5neWwiOiIiLCJzc19iaW5kaW5nZ3MiOiIiLCJzc19iaW5kaW5nc3l1IjoiIn0=","field2":"","field3":"","field4":"","openid":null},"mobile_auth":"36be%2FO3cGEMAAs37%2Bk0psrTjYyjPSk2RWnFn5dgo3Ad0hauU%2F0Caf6EhnRER3C7O%2Ftgg"}},"msg":{"msgvar":"login_success","msgstr":""}}

 */
public class App 
{
    private static String Uid="";
    private static String loginpage="http://app.17139.cn/mobile.php?ac=login&display=1080*1920&imei=866819026957866&clientversion=30803&uid=0&phone=&city=ZJ&downloadimgmode=1&apiversion=19";
    private static String apipage="http://app.17139.cn/api.php/";
    private static String QianDaopage="";
    private static HttpUtils utils=new HttpUtils();
    public static boolean login()  {
        boolean islogin=false;
        List<NameValuePair> lists=new ArrayList<NameValuePair>();
        //username=18852866178&formhash=d052d07c&loginsubmit=aaaaaaa&password=xyxx1231&loginfield=mobile
        lists.add(new BasicNameValuePair("username","18852866178"));
        lists.add(new BasicNameValuePair("formhash","d052d07c"));
        lists.add(new BasicNameValuePair("loginsubmit","aaaaaaa"));
        lists.add(new BasicNameValuePair("password","xyxx1231"));
        lists.add(new BasicNameValuePair("loginfield","mobile"));
        InputStream response= utils.Post_InputStream(loginpage,lists);
        try {
            String text= IOUtils.toString(response);
            if(text.contains("login_success")){
                islogin=true;

            }
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return islogin;
    }
    public static void QianDao(){

    }
    public static void main( String[] args )
    {
        System.out.println( "Hello ZHZJ!" );
        boolean islogin=login();
        if(islogin){
            //获取用户信息
            String page="";
        }
    }
}
