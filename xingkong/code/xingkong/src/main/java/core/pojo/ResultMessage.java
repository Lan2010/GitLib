package core.pojo;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 返回给接口调用方的提示消息
 * 
 * @author dev-teng
 * @date 2018年6月13日
 */
public class ResultMessage {
	private Integer code;
	private String msg;
	private Integer total;
	private Object data;
	private JSONObject json;

	public ResultMessage() {
	}

	public ResultMessage(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResultMessage(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultMessage(Integer code, String msg, Integer total, Object data) {
		this(code, msg, data);
		this.total = total;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toString() {
		json = new JSONObject();
		json.put("code", this.code);
		json.put("msg", this.msg);
		if (total != null) {
			json.put("total", this.total);
		}
		if (data != null) {
			if (data instanceof JSONArray) {
				if (((JSONArray) data).isEmpty()) {
					json.put("data", new JSONArray());
				}else {
					json.put("data", data);
				}
			} else if ((data instanceof List)) {
				if (((List) data).isEmpty()) {
					json.put("data", new JSONArray());
				}else {
					json.put("data", data);
				}
			} else {
				json.put("data", data);
			}
		} else if (data == null) {
			json.put("data", new JSONObject());
		}
		return json.toJSONString();
	}

}
