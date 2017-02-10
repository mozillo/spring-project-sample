package demo.springboot.controller.bean;

import demo.springboot.common.TypeUtil;

/**
 * Created by AnDong on 2016/12/2.
 */
public class Result4UI {
    public static final String code_success = "S";
    public static final String code_failed = "F";
    public static final String code_exception = "E";

    private String resultCode;
    private String resultMsg;
    private Object resultObj;

    public Result4UI(String resultCode, String resultMsg, Object resultObj) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultObj = resultObj;
    }

    public static String toSuccessJsonString() {
        return toJsonString(code_success, null);
    }

    public static String toSuccessJsonString(String resultMsg) {
        return toJsonString(code_success, resultMsg);
    }

    public static String toFailedJsonString() {
        return toJsonString(code_failed, null);
    }

    public static String toFailedJsonString(String resultMsg) {
        return toJsonString(code_failed, resultMsg);
    }

    public static String toJsonString(String resultCode, String resultMsg) {
        return toJsonString(resultCode, resultMsg, null);
    }

    public static String toSuccessJsonString(String resultMsg, Object resultObj) {
        return toJsonString(code_success, resultMsg, resultObj);
    }

    public static String toFailedJsonString(String resultMsg, Object resultObj) {
        return toJsonString(code_failed, resultMsg, resultObj);
    }

    public static String toJsonString(String resultCode, String resultMsg, Object resultObj) {
        return TypeUtil.convertObj2JsonString(new Result4UI(resultCode, resultMsg, resultObj));
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public Object getResultObj() {
        return resultObj;
    }
}
