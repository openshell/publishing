package com.cqz.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author openshell
 * @date 2019/4/16
 */

public class Msg {
    /**
     * 状态码   100-成功    200-失败
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;

    /**
     * 用户要返回给浏览器的数据
     */
    private Map<String, Object> extend = new HashMap<String, Object>();

    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param []
     * @return com.cqz.taskpublishing.pojo.Msg
     */
    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功！");
        return result;
    }
    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param []
     * @return com.cqz.taskpublishing.pojo.Msg
     */
    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败！");
        return result;
    }
    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param [key, value]
     * @return com.cqz.taskpublishing.pojo.Msg
     */
    public Msg add(String key,Object value){
        this.getExtend().put(key, value);
        return this;
    }
    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param []
     * @return int
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param [code]
     * @return void
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param []
     * @return java.lang.String
     */
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param [msg]
     * @return void
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> getExtend() {
        return extend;
    }

    /**
     *
     * @author openshell
     * @date 2019/4/16
     * @param [extend]
     * @return void
     */
    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
