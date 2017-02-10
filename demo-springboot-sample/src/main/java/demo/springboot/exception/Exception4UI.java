package demo.springboot.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AnDong on 2016/11/20.
 * 自定义异常 包含错误信息
 */
public class Exception4UI extends Exception {
    String resultCode;
    String resultMsg;

    public Exception4UI(String resultCode, String resultMsg) {
        super();
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public Map<String, String> getResult4UI() {
        Map<String, String> map = new HashMap<>();
        map.put("resultCode", resultCode);
        map.put("resultMsg", resultMsg);
        return map;
    }
}
