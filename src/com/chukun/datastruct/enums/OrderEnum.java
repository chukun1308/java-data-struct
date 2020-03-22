package com.chukun.datastruct.enums;

public enum OrderEnum {

    ASC(0,"升序"),
    DESC(1,"降序");

    private int code;
    private String desc;

    OrderEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
