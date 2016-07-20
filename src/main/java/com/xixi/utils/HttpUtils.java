package com.xixi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HttpUtils {
	private  BasicCookieStore cookieStore = null;
	private   CloseableHttpClient httpclient=null;
	boolean showHeaders=false;
	public HttpUtils() {
		cookieStore = new BasicCookieStore();
		httpclient=HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	}
	public void showHeaders(CloseableHttpResponse response){
		for (Header header:response.getAllHeaders()){
			System.out.println(header.getName()+"-人从众-"+header.getValue());
		}
	}
	public   CloseableHttpResponse Get(String url) {
		CloseableHttpResponse response = null;
		HttpGet httpGet = new HttpGet(url);
		try {
			response = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	public CloseableHttpResponse Get(Header[] headers,String url){
		HttpGet httpGet = new HttpGet(url);
		for (int i = 0; i < headers.length; i++) {
			httpGet.setHeader(headers[i]);
		}
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	public  CloseableHttpResponse Get(String url, Cookie cookie) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.6,en;q=0.4,ja;q=0.2");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36");
		httpGet.setHeader("Cookie", cookie.getName() + "=" + cookie.getValue());
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public   InputStream Get_InputStream(String uri) {
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.6,en;q=0.4,ja;q=0.2");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36");
		InputStream input = null;
		try {
			CloseableHttpResponse response = httpclient.execute(httpGet);
			if(showHeaders){showHeaders(response);}
			HttpEntity entity = response.getEntity();
			input = entity.getContent();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;
	}

	public   InputStream Get_InputStream(String url, Cookie cookie) {
		// Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
		// Accept-Encoding:gzip, deflate, sdch
		// Accept-Language:zh-CN,zh;q=0.8,zh-TW;q=0.6,en;q=0.4,ja;q=0.2
		// Cache-Control:max-age=0
		// Connection:keep-alive
		// Cookie:safedog-flow-item=9CCBECB0A069DC3E03346A732D48B2BD;
		// PHPSESSID=5gitft7nh6ft71ijp7at7r25t1
		// Host:huiwen.ujs.edu.cn:8080
		// Referer:http://huiwen.ujs.edu.cn:8080/reader/redr_info.php
		// Upgrade-Insecure-Requests:1
		// User-Agent:Mozilla/5.0 (Windows NT 6.1; Win64; x64)
		// AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84
		// Safari/537.36
		try {
			return Get(url,cookie).getEntity().getContent();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public  CloseableHttpResponse Post(String uri, List<NameValuePair> list){
		HttpPost httpPost = new HttpPost(uri);
		CloseableHttpResponse response2 =null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(list));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response2 = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response2;
	}
	
	public  InputStream Post_InputStream(String uri, List<NameValuePair> list) {
		try {
			return Post(uri, list).getEntity().getContent();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		HttpUtils utils=new HttpUtils();
		try {
			System.out.println(IOUtils.toString(utils.Get_InputStream("http://v.m.zjxuexi.com/a/plugin.php?id=singcere_sign")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
