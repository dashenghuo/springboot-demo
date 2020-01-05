package com.dalishen.demo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装统一的响应数据VO
 * @param <T> 结构体
 * @author dasheng
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 6965671443768324878L;

    /**
     * 找不到的编码
     */
    public static final int CODE_FAILURE = 0;

    /**
     * 成功的编码
     */
    public static final int CODE_SUCCESS = 1;

    /**
     * 返回结构体的编码
     */
    private Integer code;

    /**
     * 返回结构体的消息
     */
    private String msg;

    /**
     * 返回结构体的数据内容
     */
    private T data;

    private static final ResultVO COMMON_SUCCESS_RESULT = new ResultVO<>(VoCodeEnum.OK, null);
    private static final ResultVO COMMON_FAIL_RESULT = new ResultVO<>(VoCodeEnum.FAILED, null);

    public ResultVO() {
        this(VoCodeEnum.OK, null);
    }


    public ResultVO(VoCodeEnum resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMsg();
        this.data = data;
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVO buildResult(VoCodeEnum resultStatus) {
        return new ResultVO<>(resultStatus, null);
    }

    public static <T> ResultVO buildResult(VoCodeEnum resultStatus, T data) {
        return new ResultVO<>(resultStatus, data);
    }

    public static ResultVO buildResult(VoCodeEnum resultStatus, String msg) {
        return new ResultVO<>(resultStatus, msg);
    }

    public static ResultVO buildFailResult() {
        return COMMON_FAIL_RESULT;
    }

    public static ResultVO buildFailResult(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(VoCodeEnum.FAILED.getCode());
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO buildFailResult(VoCodeEnum code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code.getCode());
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO buildFailResult(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO buildFailResult(VoCodeEnum code){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code.getCode());
        resultVO.setMsg(code.getMsg());
        return resultVO;
    }

    public static ResultVO buildSuccessResult() {
        return COMMON_SUCCESS_RESULT;
    }

    public static <T> ResultVO<T> buildSuccessResult(T data) {
        return new ResultVO<>(VoCodeEnum.OK, data);
    }

}
