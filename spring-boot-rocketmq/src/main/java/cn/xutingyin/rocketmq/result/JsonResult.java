package cn.xutingyin.rocketmq.result;

import java.io.Serializable;

import lombok.Data;

@Data
public class JsonResult<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public JsonResult(ReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.msg = returnCode.getDescription();

    }

    /**
     * 成功返回
     * 
     * @param data
     * @return
     */
    public static JsonResult success(Object data) {
        JsonResult jsonResult = new JsonResult(ReturnCode.SUCCESS);
        jsonResult.data = data;
        return jsonResult;
    }

    /**
     * 成功返回-自定义返回消息
     * 
     * @param msg
     * @return
     */
    public static JsonResult successWithMsg(String msg) {
        JsonResult jsonResult = new JsonResult(ReturnCode.SUCCESS);
        jsonResult.msg = msg;
        return jsonResult;
    }

    /**
     * 失败返回
     * 
     * @param msg
     * @return
     */
    public static JsonResult failure(String msg) {
        JsonResult jsonResult = new JsonResult(ReturnCode.SYSTEM_ERROR);
        jsonResult.msg = msg;
        return jsonResult;
    }
}
