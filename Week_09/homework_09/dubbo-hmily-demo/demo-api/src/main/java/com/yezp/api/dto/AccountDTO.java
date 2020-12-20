package com.yezp.api.dto;

import lombok.Data;

/**
 * Description:
 * Created on 2020/12/20 11:39.
 *
 * @author yezp
 */
@Data
public class AccountDTO {

    private long userId;

    // 交易对方
    private long counterParty;

    private double amount;

    /** true is buy USD and false is sell CNH **/
    private boolean buySellFlag;

}