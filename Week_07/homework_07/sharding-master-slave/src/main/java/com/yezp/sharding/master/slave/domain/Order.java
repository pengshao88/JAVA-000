package com.yezp.sharding.master.slave.domain;

/**
 * Description: 订单表
 * Created on 2020/12/4 0:13.
 *
 * @author yezp
 */
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", status=" + status +
                ", price=" + price +
                ", decimalPlaces=" + decimalPlaces +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
