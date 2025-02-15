package com.jw.clushtest.calendar.exception;

public enum AppErrorCode {
    INVALID_REQUEST(400, "잘못된 요청입니다."),
    NOT_FOUND(404, "리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류입니다.");

    private final int statusNum;
    private final String message;

    AppErrorCode(int statusNum, String message) {
        this.statusNum = statusNum;
        this.message = message;
    }

    public int getStatusNum() {
        return statusNum;
    }

    public String getMessage() {
        return message;
    }
}
