package com.yezp.demo.service;

import com.yezp.api.dto.AccountDTO;
import com.yezp.api.entity.AccountDO;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/20 15:12.
 *
 * @author yezp
 */
public interface ConsumerService {

    List<AccountDO> findAllAccount();

    AccountDO findAccountByUserId(long userId);

    int updateAccount(AccountDTO accountDTO);
}
