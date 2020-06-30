package nooblong.domain;

import java.io.Serializable;

public class ResultInfo implements Serializable {
    private boolean flag;//正常为true
    private Object data;//数据
    private String errorMsg;//错误信息

    public ResultInfo(){

    }
    public ResultInfo(boolean flag){
        this.flag = flag;
    }
    public ResultInfo(boolean flag, String errorMsg){
        this.flag = flag;
        this.errorMsg = errorMsg;
    }

    public ResultInfo(boolean flag, String errorMsg, Object data){
        this.errorMsg = errorMsg;
        this.flag = flag;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
