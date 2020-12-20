package com.yezp.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description: 账户表
 * Created on 2020/12/20 11:22.
 *
 * @author yezp
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDO implements Serializable {

    /** 用户id **/
    private long userId;

    /** 美元账户 **/
    private double currencyUSD;

    /** 人民币账户 **/
    private double currencyCNH;

    /** 冻结金额 **/
    private double freezeAmount;

    /** 冻结币种 **/
    private String freezeCurrency;

}
