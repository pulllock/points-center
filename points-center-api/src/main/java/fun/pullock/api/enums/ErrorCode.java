package fun.pullock.api.enums;

import fun.pullock.general.model.BaseErrorCode;

public enum ErrorCode implements BaseErrorCode {

    SYSTEM_ERROR(1, "系统错误"),
    PARAM_ERROR(2, "参数错误"),
    CONCURRENCY_ERROR(3, "并发错误"),



    ;

    ErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private final int errorCode;

    private final String errorMsg;

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
