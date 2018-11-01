package com.tencent.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

public class JsonUtil {
	public static JSONObject  toJson(String session_key,String openid){
		JSONObject login = new JSONObject();
		login.put("session_key", session_key);
		login.put("openid",openid);
		return login;
	}
	
	public static String MessageJson(String openid,String formId,String price){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = sdf.format(date);
		JSONObject obj = new JSONObject();
		obj.put("touser", openid);
		obj.put("template_id","xH0XrOgMfn910Uy6hsnWxtbEa5mP8Mn3kNdmLotnVAs");
		obj.put("form_id",formId);
		JSONObject obj11 = new JSONObject();
		obj11.put("value", d);
		obj11.put("color", "#173177");
		JSONObject obj1 = new JSONObject();
		obj1.put("keyword1", obj11);
		JSONObject obj12 = new JSONObject();
		obj12.put("value", "…Ã∆∑1");
		obj12.put("color", "#173177");
		JSONObject obj2 = new JSONObject();
		obj2.put("keyword2", obj12);
		JSONObject obj13 = new JSONObject();
		obj13.put("value", price);
		obj13.put("color", "#173177");
		JSONObject obj3 = new JSONObject();
		obj3.put("keyword3", obj13);
		JSONObject obj14 = new JSONObject();
		obj14.put("value","Q1330145984");
		obj14.put("color", "#173177");
		JSONObject obj4 = new JSONObject();
		obj4.put("keyword4", obj14);
		JSONObject obj5 = new JSONObject();
		obj5.putAll(obj1);
		obj5.putAll(obj2);
		obj5.putAll(obj3);
		obj5.putAll(obj4);
		obj.put("data", obj5);
		obj.put("emphasis_keyword", "keyword3.DATA");
		System.out.println(obj.toString());
		return obj.toString();
	}
	
}
