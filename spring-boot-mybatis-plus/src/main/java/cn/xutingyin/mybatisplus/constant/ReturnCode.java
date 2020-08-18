package cn.xutingyin.mybatisplus.constant;

/**
 * @description: 全局状态码
 * @author: xuty
 * @date: 2020/8/18 10:45
 */

public enum ReturnCode {
    SUCCESS("200", "SUCCESS"), SYSTEM_ERROR("9999", "系统错误");

    private String code;
    private String description;

    private ReturnCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ReturnCode getReturnCodeByValue(String code) {
        for (ReturnCode returnCode : ReturnCode.values()) {
            if (returnCode.code.equals(code)) {
                return returnCode;
            }
        }
        return null;
    }
}
