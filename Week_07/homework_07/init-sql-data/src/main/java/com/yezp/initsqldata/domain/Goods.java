package com.yezp.initsqldata.domain;

import lombok.Data;

/**
 * Description:商品表
 * Created on 2020/11/29 22:22.
 *
 * @author yezp
 */
@Data
public class Goods {

    private long id;

    /** 商品id **/
    private long goodsId;

    /** 商品名称 **/
    private String goodsName;

    /** 商品描述 **/
    private String description;

    /** 商品图片 **/
    private String images;

    /** 商品价格 **/
    private int price;

    /** 价格 小数位 -100 即price除以100 **/
    private int decimalPlaces;

    private long createTime;

    private long updateTime;

}