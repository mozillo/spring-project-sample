package demo.springboot.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AnDong on 2016/11/18.
 */
public class TypeUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * @param string 输入字符串
     * @return 返回Long型 如果字串为空或Null 返回Null
     */
    public static Long convertString2Long(String string) {
        return StringUtils.isEmpty(string) ? null : Long.valueOf(string);
    }

    /**
     * @param date 输入日期
     * @return 返回字符串形式
     */
    public static String convertDate2String(Date date) {
        return simpleDateFormat.format(date);
    }

    /**
     * 通过jackson序列化对象形成json字符串
     *
     * @param obj
     * @return
     */
    public static String convertObj2JsonString(Object obj) {
        String result = null;
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 反序列化json字符串 返回对象
     *
     * @param jsonString
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T convertJsonString2Obj(String jsonString, Class<T> target) {
        T result = null;
        try {
            result = mapper.readValue(jsonString, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

