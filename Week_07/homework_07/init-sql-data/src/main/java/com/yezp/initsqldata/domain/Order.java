package com.yezp.initsqldata.domain;

import lombok.Data;

/**
 * Description: 订单表
 * Created on 2020/11/29 22:06.
 *
 * @author yezp
 */
@Data
public class Order {

    private long id;

    /** 订单id **/
    private long orderId;

    /** 用户id **/
    private long userId;

    /** 订单状态 0 未支付 1 已支付 3 取消订单 4 删除订单 **/
    private int status;

    /** 订单总价 **/
    private int price;

    /** 小数位 -100 即price除以100 **/
    private int decimalPlaces;

    private long createTime;

    private long updateTime;

}