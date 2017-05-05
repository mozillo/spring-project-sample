package anddd7.springboot.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by edliao on 2017/5/5.
 */
public class LogFormatter {

    private Logger log;

    private LogFormatter(Class target) {
        log = LoggerFactory.getLogger(target);
    }

    public static LogFormatter getLog(Class target) {
        return new LogFormatter(target);
    }

    public void debugMethod(String methodName, String describe, Object... objs) {
        StringBuilder sb = new StringBuilder();
        sb.append("Enter ").append(methodName).append(" : ").append(describe).append("\n");
        for (Object param : objs) {
            sb.append("Param : |" + JSON.toJSONString(param) + "|").append("\n");
        }
        log.debug(sb.toString());
    }
}
