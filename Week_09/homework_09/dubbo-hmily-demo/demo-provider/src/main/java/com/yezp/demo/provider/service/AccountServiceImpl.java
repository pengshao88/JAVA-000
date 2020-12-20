package com.yezp.demo.provider.service;

import com.yezp.api.entity.AccountDO;
import com.yezp.api.mapper.AccountMapper;
import com.yezp.api.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/20 14:33.
 *
 * @author yezp
 */
@Slf4j
@Service
@DubboService(retries = 0)
public class AccountServiceImpl implements AccountService {

    /** 表示忽略当前要注入的bean，如果有直接注入，没有跳过，不会报错。 **/
    @Autowired(required = false)
    private AccountMapper accountMapper;

    @Override
    public List<AccountDO> findAllAccount() {
        return accountMapper.findAll();
    }

    @Override
    public AccountDO findAccountByUserId(long userId) {
        return accountMapper.findByUserId(userId);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean updateAccount(AccountDO accountDO) {
        log.info("provider update Account called");
        AccountDO updateDO;
        if ("USD".equals(accountDO.getFreezeCurrency())){
            updateDO = new AccountDO(accountDO.getUserId(), accountDO.getCurrencyUSD(),
                    0, accountDO.getFreezeAmount(), accountDO.getFreezeCurrency());
        } else {
            updateDO = new AccountDO(accountDO.getUserId(), 0,
                    accountDO.getCurrencyCNH(), accountDO.getFreezeAmount(), accountDO.getFreezeCurrency());
        }

        log.info("{}", updateDO.toString());
        return accountMapper.update(updateDO) > 0;
    }

    public boolean confirm(AccountDO accountDO) {
        log.info("provider update FXAccount confirmed");
        AccountDO updateDO ;
        if ("USD".equals(accountDO.getFreezeCurrency())) {
            updateDO = new AccountDO(accountDO.getUserId(), 0,
                    accountDO.getCurrencyCNH(), 0, null);
        } else {
            updateDO = new AccountDO(accountDO.getUserId(), accountDO.getCurrencyUSD(),
                    0, 0, null);
        }
        log.info("{}",updateDO.toString());
        return accountMapper.update(updateDO) > 0;
    }

    public boolean cancel(AccountDO fxAccountDO) {
        log.info("provider update FXAccount cancelled");
        AccountDO updateDO ;
        if ("USD".equals(fxAccountDO.getFreezeCurrency())) {
            updateDO = new AccountDO(fxAccountDO.getUserId(), fxAccountDO.getFreezeAmount(),
                    0,0, null);
        } else {
            updateDO = new AccountDO(fxAccountDO.getUserId(), 0,
                    fxAccountDO.getFreezeAmount(), 0, null);
        }
        log.info("{}",updateDO.toString());
        return accountMapper.update(updateDO) > 0;
    }


}
