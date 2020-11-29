package com.yezp.initsqldata.domain;

import lombok.Data;

/**
 * Description:商品快照表
 * Created on 2020/11/29 22:30.
 *
 * @author yezp
 */
@Data
public class GoodsSnapshot {

    private long id;

    private long goodsSnapshotId;

    private long goodsId;

    private int price;

    private int decimalPlaces;

    private long createTime;

    private long updateTime;

}
