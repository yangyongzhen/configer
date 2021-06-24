package com.yangyongzhen.configer.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

/**
 *
 */
public class GsonUtil {

	public static String GsonString(Object object){
		if (object!=null) {
			Gson gson = new Gson();
			return gson.toJson(object);
		}
		return "";
	}

	/**
	 * 序列化
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> JSON serializer(T t){
		return (JSON)JSONObject.toJSON(t);
	}


	public static <T> T deserializer(JSON json, Class<T> c) {
		return JSONObject.parseObject(json.toJSONString(),c);
	}


}
