package com.yezp.api.mapper;

import com.yezp.api.entity.AccountDO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/20 11:43.
 *
 * @author yezp
 */
public interface AccountMapper {

    @Select("select * from account")
    List<AccountDO> findAll();

    @Select("select * from account where user_id = #{userId}")
    AccountDO findByUserId(long userId);

    @Update("update account set currency_USD = currency_USD + #{currencyUSD}, " +
            "currency_CNH = currency_CNH + #{currencyCNH}, " +
            "freeze_amount = #{freezeAmount}, " +
            "freeze_currency = #{freezeCurrency} " +
            "where user_id = #{userId}")
    int update(AccountDO accountDO);
}
