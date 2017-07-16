package org.helloz.demo.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouk on 2017/7/16.
 */
public class ResultVO {

    private int success;
    private String msg;

    private Map<String, Object> data = new HashMap<>();

    public static ResultVO success() {
        return new ResultVO(1, "成功");
    }

    public static ResultVO fail() {
        return new ResultVO(0, "失败");
    }

    public ResultVO(int success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResultVO msg(String msg){
        setMsg(msg);
        return this;
    }

    public ResultVO add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
