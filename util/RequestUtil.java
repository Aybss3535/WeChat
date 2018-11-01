package com.tencent.util;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.tencent.po.Access_Token;
import com.tencent.po.Login;

import net.sf.json.JSONObject;

public class RequestUtil {
	public static final String LOGIN_URL="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	public static final String TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String SEND_MESSAGE_URL="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
	/**
	 * Get«Î«Û
	 * @param url
	 * @return
	 */
	
	
	public static JSONObject doGetStr(String url){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity!=null){
				String result = EntityUtils.toString(entity,"utf-8");
				jsonObject = JSONObject.fromObject(result);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return jsonObject;
	}
	
	/**
	 * Post«Î«Û
	 * @param url
	 * @param outStr
	 * @return
	 */
	
	public static JSONObject dopostStr(String url,String outStr){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost();
		JSONObject jsonObject = null;
		URI uri = null;
		try {
			uri=new URI(url);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		httpPost.setURI(uri);
		try {
			httpPost.setEntity(new StringEntity(outStr,"utf-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"utf-8");
			jsonObject = JSONObject.fromObject(result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return jsonObject;
	}
	
	public static Login loginRequset(String code){
		Login login = new Login();
		String url = LOGIN_URL.replace("APPID", "wx9c5ba7ac93ab7d2a").replace("SECRET", "0314e380d77c3198ecf1b169b3967ac3").replace("JSCODE", code);
		JSONObject jsonObject=doGetStr(url);
		if(jsonObject!=null){
			System.out.println(jsonObject.toString());
			login.setSession_key(jsonObject.getString("session_key"));
			login.setOpenid(jsonObject.getString("openid"));
			
		}
		return login;
		
	}
	
	public static Access_Token accessTokenRequest(){
		Access_Token token =new Access_Token();
		String url =TOKEN_URL.replace("APPID", "wx9c5ba7ac93ab7d2a").replace("APPSECRET","0314e380d77c3198ecf1b169b3967ac3");
		JSONObject json = doGetStr(url);
		if(json!=null){
			token.setAccess_token(json.getString("access_token"));
			token.setExpires_in(json.getString("expires_in"));
		}
		return token;
	}
	
	public static JSONObject sendMessage(String token,String openid,String formId,String price){
		JSONObject obj = new JSONObject();
		String url = SEND_MESSAGE_URL.replace("ACCESS_TOKEN", token);
		String outStr = JsonUtil.MessageJson(openid, formId, price);
		obj = dopostStr(url,outStr);
		return obj;
	}
	
	
	
}
