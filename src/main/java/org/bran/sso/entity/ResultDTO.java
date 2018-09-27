package org.bran.sso.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: BranSummer
 * @Date: 2018-9-24 21:37
 * @Description:
 */
@Getter
@Setter
@ToString
public class ResultDTO {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    /**
     * success
     * @param data
     * @param msg
     * @return
     */
    public static ResultDTO buildSuccessResult(Object data, String msg){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setData(data);
        resultDTO.setMsg(msg);
        return resultDTO;
    }

    /**
     * error
     * @param data
     * @param msg
     * @return
     */
    public static ResultDTO buildErrorResult(Object data, String msg){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(500);
        resultDTO.setData(data);
        resultDTO.setMsg(msg);
        return resultDTO;
    }
}
