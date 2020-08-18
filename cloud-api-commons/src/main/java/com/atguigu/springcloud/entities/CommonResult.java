package com.atguigu.springcloud.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 封装返回前端的业务信息
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    // 业务编码
    private Integer code;
    // 业务信息
    private String message;
    // 数据
    private T data;
    //两个参数的构造方法
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
