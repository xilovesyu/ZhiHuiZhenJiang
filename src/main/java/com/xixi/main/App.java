package com.xixi.main;


import com.xixi.utils.HttpUtils;
import com.xixi.utils.MyTimer;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *{"charset":"utf8","res":{"list":{"cookiepre":"GRHR_2132_","auth":"6cf67jBWQeXKDTT0ZgSaju5qoP84DlQaeibBRsdfxMmabN2b8BzYtbN6yHl2V9wp5V22Aa8Zz28dZ46uzAdXBrQcy5k","saltkey":"oA0AfNXf","appsites":{"QicC_":{"cookiepre":"QicC_2132_","url":"http:\/\/bbs.jsw.com.cn\/bbs\/","auth":"7b68DyGT4RnSBC2t+zLmPyIfIDnDWr7zrFudG4DWFRrYTx2f+QxR02V3Tp\/Z7tTaOwG4HjT7yfNVAHPL8RjDxUYG90A","saltkey":"vDdA026j"},"3q1K_":{"cookiepre":"3q1K_2132_","url":"http:\/\/v.m.zjxuexi.com\/a\/","auth":"e1dd4wdXWLRFg8yTDPh\/6LE5VG72EetUTH+fcYNfNEcHRRcQVGJa5GpZYlZX0GJ1Kf32htdZwb+VxIDZNH2m+acTwlw","saltkey":"jT8FI3tT"}},"member_uid":"196391","member_username":"user174165","member_mobile":"18852866178","groupid":"10","formhash":"6cf67jBWQeXKDTT0ZgSaju5qoP84DlQaeibBRsdfxMmabN2b8BzYtbN6yHl2V9wp5V22Aa8Zz28dZ46uzAdXBrQcy5k","ismoderator":null,"member_properties":{"uid":"196391","mobile":"","isvalid":"0","field1":"eyJzc19iaW5kaW5nc3kiOiIiLCJzc19iaW5kaW5nIjoiMCIsInNzX3Bhc3N3b3JkIjoiIiwic3NfdXNlcm5hbWUiOiIiLCJzc19iaW5kaW5neWIiOiIiLCJzc19iaW5kaW5neWwiOiIiLCJzc19iaW5kaW5nZ3MiOiIiLCJzc19iaW5kaW5nc3l1IjoiIn0=","field2":"","field3":"","field4":"","openid":null},"mobile_auth":"36be%2FO3cGEMAAs37%2Bk0psrTjYyjPSk2RWnFn5dgo3Ad0hauU%2F0Caf6EhnRER3C7O%2Ftgg"}},"msg":{"msgvar":"login_success","msgstr":""}}
 * GET /api.php?ac=userinfo&uid=196391&system=android&display=1080*1920&imei=866819026957866&clientversion=30803&uid=196391&phone=18852866178&city=ZJ&downloadimgmode=1&apiversion=19?
 */
public class App
{
    private  String loginJson;
    private String userJson;
    private  String loginAPPpage ="http://app.17139.cn/mobile.php?ac=login&display=1080*1920&imei=866819026957866&clientversion=30803&uid=0&phone=&city=ZJ&downloadimgmode=1&apiversion=19";
    private  String apipage="http://app.17139.cn/api.php?";
    private String avatorpage="http://www.17139.cn/uc_server/avatar.php?size=middle&uid=";


    ////
    //注意loginhash
    private String loginHash="";
    private String formhash="";
    private String referer="";
    private String username="user174165";
    private String password="xyxx1231";
    private String loginDisuzpage="http://v.m.zjxuexi.com/a/member.php?mod=logging&action=login&loginsubmit=yes&handlekey=login&inajax=1&loginhash=";
    private String QianDaopage="http://v.m.zjxuexi.com/a/plugin.php?id=singcere_sign";
    private String loginBeforeDiscuzpage="http://v.m.zjxuexi.com/a/member.php?mod=logging&action=login&referer=http%3A%2F%2Fv.m.zjxuexi.com%2Fa%2Fforum.php&infloat=yes&handlekey=login&inajax=1&ajaxtarget=fwin_content_login";

    private  HttpUtils utils=new HttpUtils();
    public  boolean login()  {
        boolean islogin=false;
        List<NameValuePair> lists=new ArrayList<NameValuePair>();
        //username=18852866178&formhash=d052d07c&loginsubmit=aaaaaaa&password=xyxx1231&loginfield=mobile
        lists.add(new BasicNameValuePair("username","18852866178"));
        lists.add(new BasicNameValuePair("formhash","d052d07c"));
        lists.add(new BasicNameValuePair("loginsubmit","aaaaaaa"));
        lists.add(new BasicNameValuePair("password","xyxx1231"));
        lists.add(new BasicNameValuePair("loginfield","mobile"));
        InputStream response= utils.Post_InputStream(loginAPPpage,lists);
        try {
            String text= IOUtils.toString(response);
            if(text.contains("login_success")){
                islogin=true;
                loginJson=text;
            }
            System.out.println("login response:"+text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return islogin;
    }
    /**
     * get user id
     * */
    public String getUid(){
        if(loginJson.contains("member_uid")){
            //截取,否则太长了
            loginJson=loginJson.substring(loginJson.indexOf("member_uid"));
            //利用正则表达式
//          System.out.println( test.matches("^.*member_uid\":\"\\w+\"(.|\\s)*"));
            String[] results=loginJson.split("\"(:|,)\"");
            return results[1];
        }else{
            return "";
        }
    }
    /**
     * user info get from this method
     * */
    public String getApi(){
        String json="";
        String apiParam="ac=userinfo&uid="+getUid()+"&system=android&display=1080*1920&imei=866819026957866&clientversion=30803&uid="+getUid()+"&phone=18852866178&city=ZJ&downloadimgmode=1&apiversion=19";
        apipage+=apiParam;
        InputStream response=utils.Get_InputStream(apipage);
        try {
           json=IOUtils.toString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            userJson=json;
            return json;
        }
    }
    public Map<String,String> getFromUserJson(){
        Map<String,String> maps=new HashMap<String, String>();
        String[] splitResults=userJson.split("\"(:|,|:\\{|\\{)\"");
        maps.put("uid",splitResults[5]);
        maps.put("username",splitResults[7]);
        return maps;
    }


    public void getDiscuzBeforeLoginPage(){
        InputStream response=utils.Get_InputStream(loginBeforeDiscuzpage);
        try {

            String s=IOUtils.toString(response);
            System.out.println(s);
            //得到loginhash 和 formhash,正则表达式，不用jsoup
            if(s.contains("loginform")){
                Pattern pattern = Pattern.compile("<form id=\"loginform\" method=\"post\" action=.*>");
                Matcher matcher = pattern.matcher(s);
                if(matcher.find()){
                    System.out.println("get some");
                   String text= matcher.group(0);
                    //再进行loginhash 的搜索
                    pattern=Pattern.compile("loginhash=\\w+&");
                    matcher=pattern.matcher(text);
                    if(matcher.find()){
                        loginHash=matcher.group(0).substring(10,15);
                        System.out.println("loginHash:"+loginHash);
                    }
                }
                //formhash
                pattern=Pattern.compile("id=\"formhash\" value='\\w+'");
                matcher=pattern.matcher(s);
                if(matcher.find()){
                    String text=matcher.group(0);
                    formhash=text.split(" ")[1].substring(7,15);
                    System.out.println("formhash:"+formhash);
                }
                //refer:
                pattern=Pattern.compile("id=\"referer\" value=\".*\"");
                matcher=pattern.matcher(s);
                if(matcher.find()){
                    String text=matcher.group(0);
                    text=text.split(" ")[1];
                    text=text.substring(7,text.length()-1);
                    referer=text;
                    System.out.println("referer:"+referer);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 论坛模拟登陆
     * */
    public void LoginDiscuz(){
        //组成login地址
        loginDisuzpage+=loginHash;
        //post 参数 -> forhash, referer , loginfield=username, username, password,questionid=0,answer="", loginsubmit=true
        List<NameValuePair> lists=new ArrayList<NameValuePair>();
        //实际上不能发送formhash
        lists.add(new BasicNameValuePair("forhash",formhash));
        lists.add(new BasicNameValuePair("referer",referer));
        lists.add(new BasicNameValuePair("loginfield","username"));
        lists.add(new BasicNameValuePair("username",username));
        lists.add(new BasicNameValuePair("password",password));
        lists.add(new BasicNameValuePair("questionid","0"));
        lists.add(new BasicNameValuePair("answer",""));
        lists.add(new BasicNameValuePair("loginsubmit","true"));

        InputStream resonse=utils.Post_InputStream(loginDisuzpage,lists);

    }
    /**
     * 签到页面
     * @Return
     *
     * */
    public  int QianDaoPage(){
        //0 表示未签到，1表示已签到，-1表示错误
        int returnResult=0;
        InputStream response=utils.Get_InputStream(QianDaopage);
        String s="";
        try {
            s=IOUtils.toString(response);
            Document docs= Jsoup.parse(s);
            Elements divs=docs.getElementsByClass("qdleft");
            Element qdleftDiv=divs.get(0);
            System.out.println(qdleftDiv);
            if(qdleftDiv.select("a").size()==0){returnResult=1;}
            else {
                returnResult=0;
                Element qiandaoHref = qdleftDiv.select("a").get(0);
                System.out.println(qiandaoHref.toString());
                String href = qiandaoHref.attr("href");
                System.out.println(href);
                String getUrl = "http://v.m.zjxuexi.com/a/" + href;

                InputStream doQiandao = utils.Get_InputStream(getUrl);
                System.out.println(IOUtils.toString(doQiandao));
            }
        } catch (IOException e) {
            returnResult=-1;
            e.printStackTrace();
        }
        return returnResult;
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello ZHZJ!" );
        int count=0;
        for(;;){
            try {
                count++;
                App app = new App();
                //先获取formhash和loginhash等值,这个可以省略
//              app.getDiscuzBeforeLoginPage();
                System.out.println("==========count:"+count+" Time:"+ MyTimer.getShangHaiTime()+"==============");
                app.LoginDiscuz();
                System.out.println("==========登陆成功================");
                int result = app.QianDaoPage();
                System.out.println("签到情况："+result);
                System.out.println("===========================");
                Thread.sleep(1000*60*60*1);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
//        new Thread(new Runnable() {
//            public void run() {
//                while(true) {
//                    App app = new App();
//                    boolean islogin = app.login();
//                    if (islogin) {
//                        System.out.println("user json:" + app.getApi());
//                        Map<String, String> user = app.getFromUserJson();
//                        for (Map.Entry<String, String> entry : user.entrySet()) {
//                            System.out.println(entry.getKey() + " " + entry.getValue());
//                        }
//                    }
//                    try {
//                        Thread.sleep((int) (Math.random() * 2000) + 3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

    }
}
