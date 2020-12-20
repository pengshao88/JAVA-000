package com.yezp.api.service;

import com.yezp.api.entity.AccountDO;
import org.dromara.hmily.annotation.Hmily;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/20 11:51.
 *
 * @author yezp
 */
public interface AccountService {

    List<AccountDO> findAllAccount();

    AccountDO findAccountByUserId(long userId);

    /**
     * 使用Hmily 事务
     * @param accountDO
     * @return
     */
    @Hmily
    boolean updateAccount(AccountDO accountDO);

}