package com.dalishen.demo.vo;



import com.dalishen.demo.util.EnumUtil;

import java.util.Arrays;

public enum VoCodeEnum {
    FAILED(0, "失败"),
    OK(1, "成功");

    /**
     * 编码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    VoCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
        EnumUtil.putEnum(code, this);
    }

    public static VoCodeEnum getByCode(String code) {
        return EnumUtil.getEnum(VoCodeEnum.class, code);
    }
    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "VoCodeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static String toDescString() {
        StringBuilder str = new StringBuilder();
        str.append("枚举名|code码|描述\n- | :-: | -: \n");
        Arrays.stream(VoCodeEnum.values())
                .forEach(voCodeEnum -> str.append(voCodeEnum.name()).append("|").append(voCodeEnum.getCode())
                        .append("|").append(voCodeEnum.getMsg()).append("\n"));
        return str.toString();
    }

}
