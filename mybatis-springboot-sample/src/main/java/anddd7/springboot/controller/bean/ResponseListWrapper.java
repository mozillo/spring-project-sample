package anddd7.springboot.controller.bean;


import java.util.List;

public class ResponseListWrapper<T> {
    //Integer pageNum;
    //Integer pageSize;
    Integer totalCount;
    Integer currentCount;
    List<T> objs;

    public ResponseListWrapper(Integer totalCount,List<T> objs) {
        this.totalCount=totalCount;
        this.objs=objs;
        this.currentCount=objs.size();
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    public List<T> getObjs() {
        return objs;
    }

    public void setObjs(List<T> objs) {
        this.objs = objs;
    }
}
