package cn.xutingyin.springdatajpa.common;

/*
 *
 * 功能描述：全局前端返回
 * @author TingYin.Xu
 * @date 2021/2/4 15:07
 *
 */

public class Result<T> {
    private String msg;

    private int code;

    private T data;
    public Result(){

    }

    public static <T> Result<T> success(T data) {
        Result result = new Result();
        result.code = 0;
        result.msg = "成功";
        result.data = data;
        return result;
    }
    public static <T> Result<T> failure(T data) {
        Result result = new Result();
        result.code = 500;
        result.msg = "失败";
        result.data = data;
        return result;
    }

    public static <T> Result<T> success(T data, String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static <T> Result<T> failure(T data, String msg) {
        Result result = new Result();
        result.code = 500;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
