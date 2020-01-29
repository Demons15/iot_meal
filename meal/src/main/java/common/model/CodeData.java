package common.model;

import java.io.Serializable;

/**
 * Created by cxsz-luyong on 2017/12/6.
 */

public class CodeData<T> implements Serializable {


    /**
     * status : 0
     * msg : ok
     * data : []
     */

    private int status ;
    private String msg ;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CodeData{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
