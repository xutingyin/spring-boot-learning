package cn.xutingyin.mybatisplus.result;

import cn.xutingyin.mybatisplus.constant.ReturnCode;
import lombok.Data;

/**
 * @description: 全局返回公共类
 * @author: xuty
 * @date: 2020/8/18 10:47
 */

@Data
public class JsonResult<T> {
    private String code;
    private String msg;
    private T data;

    public JsonResult(ReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.msg = returnCode.getDescription();
    }

    public static JsonResult success(Object data) {
        JsonResult jsonResult = new JsonResult(ReturnCode.SUCCESS);
        jsonResult.data = data;
        return jsonResult;
    }

    public static JsonResult successWithMsg(String msg) {
        JsonResult jsonResult = new JsonResult(ReturnCode.SUCCESS);
        jsonResult.msg = msg;
        return jsonResult;
    }

    public static JsonResult failure(String msg) {
        JsonResult jsonResult = new JsonResult(ReturnCode.SYSTEM_ERROR);
        jsonResult.msg = msg;
        return jsonResult;
    }

}
