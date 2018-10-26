/**
 * Copyright(c) 2010-2013 by XiangShang Inc.
 * All Rights Reserved
 */

package com.tianzhixing.common.auth.utils;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * json util
 * 
 * @author jinkai
 */
public class JSONUtil {

	/**
	 * bean to json
	 * 
	 */
	public static String beanToJson(Object obj, boolean serializeNullValue) {

		if (obj == null) {
			return null;
		}
		// Bean -> Json
		Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls().create() : new Gson();
		String json = gson.toJson(obj);
		return json;
	}
	
	
	/**
	 * beantoMap
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> beantoMap(Object obj) {  
		  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
  
        return map;  
  
    }  

	/**
	 * Convert bean to json with jackson(jackson has better performance than
	 * gson)
	 * 
	 * @param obj
	 * @return
	 * @author liuzhenkun
	 * @Date 2015年6月26日
	 */
	public static String beanToJsonWithJackson(Object obj) {

		if (obj == null) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = ow.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * bean to json 适用于毫秒
	 * 
	 */
	public static String beanWithDateToJson(Object obj, boolean serializeNullValue) {

		if (obj == null) {
			return null;
		}
		// date serializable
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = serializeNullValue ? gsonBuilder.serializeNulls().registerTypeAdapter(Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create() : gsonBuilder
				.registerTypeAdapter(Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
		// Bean -> Json
		String json = gson.toJson(obj);
		return json;
	}

	/**
	 * json to bean
	 *
	 */
	public static <T> T jsonToBean(String json, Class<T> clazz) {

		if (json == null || "".equals(json.trim())) {
			return null;
		}
		StringReader strReader = new StringReader(json);
		JsonReader jsonReader = new JsonReader(strReader);
		return (T) jsonToBean(jsonReader, clazz);
	}

	private static <T> T jsonToBean(JsonReader json, Class<T> clazz) {

		if (json == null) {
			return null;
		}
		// Json -> Bean
		Gson gson = new Gson();
		T bean = gson.fromJson(json, clazz);
		return (T) bean;
	}

	/**
	 * json to bean 适用于毫秒
	 *
	 */
	public static <T> T jsonWithDateToBean(String json, Class<T> clazz) {

		if (json == null || "".equals(json.trim())) {
			return null;
		}
		StringReader strReader = new StringReader(json);
		JsonReader jsonReader = new JsonReader(strReader);
		return jsonWithDateToBean(jsonReader, clazz);
	}

	private static <T> T jsonWithDateToBean(JsonReader json, Class<T> clazz) {

		if (json == null) {
			return null;
		}
		// date deserializable
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializerUtils()).setDateFormat(DateFormat.LONG).create();
		// Json -> Bean
		T b = gson.fromJson(json, clazz);
		return b;
	}

	/**
	 * list to json
	 *
	 */
	public static <T> String listToJson(List<T> list, boolean serializeNullValue) {

		if (list == null) {
			return null;
		}
		// List -> Json
		Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls().create() : new Gson();
		String json = gson.toJson(list);
		return json;
	}

	/**
	 * list to json
	 *
	 * @seee with date type
	 */
	public static <T> String listWithDateToJson(List<T> list, boolean serializeNullValue) {

		if (list == null) {
			return null;
		}
		// date serializable
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = serializeNullValue ? gsonBuilder.serializeNulls().registerTypeAdapter(Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create() : gsonBuilder
				.registerTypeAdapter(Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
		// List -> Json
		String json = gson.toJson(list);
		return json;
	}

	/**
	 * json to list
	 *
	 */
	public static <T> List<T> jsonToList(String json, Class<T> clazz) {

		if (json == null || "".equals(json.trim())) {
			return null;
		}
		// json -> List
		StringReader strReader = new StringReader(json);
		List<T> list = null;
		try {
			list = readForList(strReader, false, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * json to list
	 *
	 */
	public static <T> List<T> jsonWithDateToList(String json, Class<T> clazz) {

		if (json == null || "".equals(json.trim())) {
			return null;
		}

		// json -> List
		StringReader strReader = new StringReader(json);
		List<T> list = null;
		try {
			list = readForList(strReader, true, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static <T> List<T> readForList(Reader reader, boolean hasDate, Class<T> clazz) throws IOException {

		JsonReader jsonReader = new JsonReader(reader);
		List<T> objs = new ArrayList<T>();
		jsonReader.beginArray();
		while (jsonReader.hasNext()) {
			T obj = null;
			if (hasDate) {
				obj = jsonWithDateToBean(jsonReader, clazz);
			} else {
				obj = jsonToBean(jsonReader, clazz);
			}
			if (obj != null)
				objs.add(obj);
		}
		jsonReader.endArray();
		jsonReader.close();
		return objs;
	}

	/**
	 * set to json
	 *
	 */
	public static <T> String setToJson(Set<T> set, boolean serializeNullValue) {

		if (set == null) {
			return null;
		}
		// set -> Json
		Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls().create() : new Gson();
		String json = gson.toJson(set);
		return json;
	}

	/**
	 * set to json
	 *
	 * @seee with date type
	 */
	public static <T> String setWithDateToJson(Set<T> set, boolean serializeNullValue) {

		if (set == null) {
			return null;
		}
		// date serializable
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = serializeNullValue ? gsonBuilder.serializeNulls().registerTypeAdapter(Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create() : gsonBuilder
				.registerTypeAdapter(Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
		// List -> Json
		String json = gson.toJson(set);
		return json;
	}

	/**
	 * json to set
	 *
	 */
	public static <T> Set<T> jsonToSet(String json, Class<T> clazz) {

		if (json == null || "".equals(json.trim())) {
			return null;
		}
		// json -> set
		StringReader strReader = new StringReader(json);
		Set<T> set = null;
		try {
			set = readForSet(strReader, false, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}

	/**
	 * json to set
	 *
	 */
	public static <T> Set<T> jsonWithDateToSet(String json, Class<T> clazz) {

		if (json == null || "".equals(json.trim())) {
			return null;
		}

		// json -> set
		StringReader strReader = new StringReader(json);
		Set<T> set = null;
		try {
			set = readForSet(strReader, true, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}

	private static <T> Set<T> readForSet(Reader reader, boolean hasDate, Class<T> clazz) throws IOException {

		JsonReader jsonReader = new JsonReader(reader);
		Set<T> objs = new HashSet<T>();
		jsonReader.beginArray();
		while (jsonReader.hasNext()) {
			T obj = null;
			if (hasDate) {
				obj = jsonWithDateToBean(jsonReader, clazz);
			} else {
				obj = jsonToBean(jsonReader, clazz);
			}
			if (obj != null)
				objs.add(obj);
		}
		jsonReader.endArray();
		jsonReader.close();
		return objs;
	}

	/**
	 * map to json
	 *
	 */
	public static <T> String mapToJson(Map<String, T> map, boolean serializeNullValue) {

		if (map == null) {
			return null;
		}
		// Map -> Json
		Gson gson = serializeNullValue ? new GsonBuilder().serializeNulls().create() : new Gson();
		String json = gson.toJson(map);
		return json;
	}

	/**
	 * map to json
	 *
	 */
	public static <T> String mapWithDateToJson(Map<String, T> map, boolean serializeNullValue) {

		if (map == null) {
			return null;
		}
		// date serializable
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = serializeNullValue ? gsonBuilder.serializeNulls().registerTypeAdapter(Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create() : gsonBuilder
				.registerTypeAdapter(Date.class, new DateSerializerUtils()).setDateFormat(DateFormat.LONG).create();
		// map -> json
		String json = gson.toJson(map);
		return json;
	}

	/**
	 * 日期解序列实用工具类
	 */
	static class DateSerializerUtils implements JsonSerializer<Date> {

		@Override
		public JsonElement serialize(Date date, Type type, JsonSerializationContext content) {

			return new JsonPrimitive(date.getTime());
		}

	}

	/**
	 * 日期序列化实用工具类
	 */
	static class DateDeserializerUtils implements JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

			return new Date(json.getAsJsonPrimitive().getAsLong());
		}
	}

	/**
	 * 根据json字符串返回Map对象
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String json, Class<?> clas) {

		return JSONUtil.toMap(JSONUtil.toJsonObject(json, null), clas);
	}

	/**
	 * 将JSONObjec对象转换成Map-List集合
	 * 
	 * @param json
	 * @return
	 */
	private static Map<String, Object> toMap(JsonObject json, Class<?> clas) {

		Map<String, Object> map = new HashMap<String, Object>();
		Set<Entry<String, JsonElement>> entrySet = json.entrySet();
		for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext();) {
			Entry<String, JsonElement> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof JsonArray)
				map.put((String) key, toList((JsonArray) value, clas));
			else if (value instanceof JsonObject)
				map.put((String) key, jsonToBean(value.toString(), clas));
			else
				map.put((String) key, value);
		}
		return map;
	}

	/**
	 * 将JSONArray对象转换成List集合
	 * 
	 * @param json
	 * @return
	 */
	public static List<?> toList(JsonArray json, Class<?> clas) {

		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < json.size(); i++) {
			Object value = json.get(i);
			if (value instanceof JsonObject) {
				list.add(jsonToBean(value.toString(), clas));
			} else {
				list.add(value);
			}
		}
		return list;
	}

	public static Gson createGson() {

		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = builder.create();
		return gson;
	}

	/**
	 * 获取JsonObject
	 * 
	 * @param json
	 * @return
	 */
	public static JsonObject toJsonObject(String json, String jsonObject) {

		JsonParser parser = new JsonParser();
		if (json.contains(jsonObject)) {
			JsonObject jsonObj = (JsonObject) parser.parse(json).getAsJsonObject().get("responseBody");
			return jsonObj;
		} else {
			JsonObject jsonObj = parser.parse(json).getAsJsonObject();
			return jsonObj;
		}
	}


	public static void main(String[] args) {
		String response = "{" +
				"\"errorDesc\": \"与贷款系统网络连接超时\"," +
				"\"retCode\": \"500\"" +
				"}";
		String json = JSONUtil.toJsonObject(response, "responseBody").toString();
		System.out.print(json);

	}

}
