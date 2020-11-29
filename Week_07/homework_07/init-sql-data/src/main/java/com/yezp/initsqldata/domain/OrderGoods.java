package com.yezp.initsqldata.domain;

import lombok.Data;

/**
 * Description:订单商品列表
 * Created on 2020/11/29 22:19.
 *
 * @author yezp
 */
@Data
public class OrderGoods {

    private long id;

    /** 商品快照id **/
    private long goodsSnapshotId;

    /** 价格 **/
    private int price;

    /** 价格 小数位 -100 即price除以100 **/
    private int decimalPlaces;

    private long createTime;

    private long updateTime;

}
