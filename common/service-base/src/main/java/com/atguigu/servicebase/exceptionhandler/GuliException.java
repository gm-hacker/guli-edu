package com.atguigu.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gm-hacker
 * @create 2020-09-01 15:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;

    @Override
    public String toString() {
        return "GuliException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}
