package demo.springboot.controller.bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AnDong on 2016/12/12.
 */
public class QueryConditon {
    Map<String, List> condMap = new HashMap<>();

    public void addCond(String type, Object... value) {
        addCond(type, Arrays.asList(value));
    }

    public void addCond(String type, List value) {
        condMap.put(type, value);
    }

    public List getCond(String type) {
        return condMap.containsKey(type) ? condMap.get(type) : null;
    }

    public Map<String, List> getCondMap() {
        return condMap;
    }

    public void setCondMap(Map<String, List> condMap) {
        this.condMap = condMap;
    }
}
