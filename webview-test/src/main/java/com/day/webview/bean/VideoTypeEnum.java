package com.day.webview.bean;

/**
 * @author xupei
 */

public enum VideoTypeEnum {

    BINARY(0,"二值化后的指纹"),
    ORIGIN(1,"原始的指纹");


    private Integer code;

    private String desc;

    VideoTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
