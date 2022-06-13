package cn.paopao.zuchebackend.entity;

public class SystemResults {
    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    private int total;

    /**
     * 成功
     * @param msg 返回消息
     * @return SystemResults
     */
    public static SystemResults ok(String msg){
        return new SystemResults(200,msg,null);
    }

    /**
     * 成功
     * @param msg 返回消息
     * @param obj 返回数据
     * @return SystemResults
     */
    public static SystemResults ok(String msg,Object obj){
        return new SystemResults(200,msg,obj);
    }


    /**
     * 失败
     * @param msg 返回消息
     * @return SystemResults
     */
    public static SystemResults error(String msg){
        return new SystemResults(500,msg,null);
    }

    /**
     * 成功
     * @param msg 返回消息
     * @param obj 返回数据
     * @return 失败
     */
    public static SystemResults error(String msg,Object obj){
        return new SystemResults(500,msg,obj);
    }

    private SystemResults() {
    }

    private SystemResults(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SystemResults{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
