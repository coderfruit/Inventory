package com.stee.inventory.enums;

public enum  ResponseCode {
    SUCCESS("000000"),
    FAILED("999999"),
    NO_SUCH_OBJECT("900102"),
    ERROR_PARAM("900101"),
    SERVER_ERROR("900008");

    private String code;

    private ResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
