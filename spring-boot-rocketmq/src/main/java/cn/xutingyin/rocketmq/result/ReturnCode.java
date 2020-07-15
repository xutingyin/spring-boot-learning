package cn.xutingyin.rocketmq.result;

/**
 * @description: 全局统一返回状态码
 * @author: xuty
 * @date: 2020/7/15 14:04
 */

public enum ReturnCode {
    SUCCESS("200000", "SUCCESS"), SYSTEM_ERROR("209999", "后台处理异常");

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

    public ReturnCode getReturnCode(String code) {
        for (ReturnCode returnCode : ReturnCode.values()) {
            if (returnCode.getCode().equals(code)) {
                return returnCode;
            }
        }
        return null;
    }
}
