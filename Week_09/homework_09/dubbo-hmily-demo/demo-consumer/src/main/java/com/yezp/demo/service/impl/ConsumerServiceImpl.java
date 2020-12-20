package com.yezp.demo.service.impl;

import com.yezp.api.dto.AccountDTO;
import com.yezp.api.entity.AccountDO;
import com.yezp.api.mapper.AccountMapper;
import com.yezp.api.service.AccountService;
import com.yezp.demo.service.ConsumerService;
import com.yezp.demo.service.RateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/20 15:16.
 *
 * @author yezp
 */
@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @DubboReference
    private AccountService accountService;

    private final AccountMapper accountMapper;

    private final RateService rateService;

    @Autowired(required = false)
    public ConsumerServiceImpl(AccountMapper accountMapper, RateService rateService) {
        this.accountMapper = accountMapper;
        this.rateService = rateService;
    }



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
    public int updateAccount(AccountDTO accountDTO) {
        log.info("consumer update Account called");
        accountMapper.update(buildUpdateAccount(accountDTO));
        accountService.updateAccount(buildCounterPartyAccount(accountDTO));
        return 1;
    }

    public void confirm(AccountDTO accountDTO) {
        log.info("consumer update Account confirmed");
        AccountDO accountDO = buildConfirmAccount(accountDTO);
        log.info("{}", accountDO.toString());
        accountMapper.update(accountDO);
    }

    public void cancel(AccountDTO accountDTO) {
        log.info("consumer update Account cancelled");
        AccountDO accountDO = buildCancelAccount(accountDTO);
        log.info("{}", accountDO.toString());
        accountMapper.update(accountDO);
    }

    private AccountDO buildUpdateAccount(AccountDTO accountDTO) {
        double amount = accountDTO.getAmount();
        AccountDO accountDO;

        if (accountDTO.isBuySellFlag()) { // 买美金
            double rate = rateService.getRateViaCurrencyPair("USD|CNH");
            accountDO = new AccountDO(accountDTO.getUserId(), 0, rate * amount * -1,
                    rate * amount, "CNH");
        } else {
            double rate = rateService.getRateViaCurrencyPair("CNH|USD");
            accountDO = new AccountDO(accountDTO.getUserId(), amount * rate * -1,
                    0, amount * rate, "USD");
        }
        return accountDO;
    }

    private AccountDO buildConfirmAccount(AccountDTO accountDTO) {
        double amount = accountDTO.getAmount();
        AccountDO accountDO;
        if (accountDTO.isBuySellFlag()) { // 买美金
            accountDO = new AccountDO(accountDTO.getUserId(), amount, 0, 0, null);
        } else {
            accountDO = new AccountDO(accountDTO.getUserId(), 0, amount, 0, null);
        }
        return accountDO;
    }

    private AccountDO buildCancelAccount(AccountDTO accountDTO) {
        AccountDO accountDO;
        double amount = accountDTO.getAmount();

        if (accountDTO.isBuySellFlag()) { // 买美金
            double rate = rateService.getRateViaCurrencyPair("USD|CNH");
            accountDO = new AccountDO(accountDTO.getUserId(), 0, amount * rate, 0, null);
        } else {
            double rate = rateService.getRateViaCurrencyPair("CNH|USD");
            accountDO = new AccountDO(accountDTO.getUserId(), amount * rate, 0, 0, null);
        }
        return accountDO;
    }

    private AccountDO buildCounterPartyAccount(AccountDTO accountDTO) {
        double amount = accountDTO.getAmount();
        AccountDO accountDO;

        if (accountDTO.isBuySellFlag()) { // 买美金， 交易方： 卖美金，收人民币
            double rate = rateService.getRateViaCurrencyPair("USD|CNH");
            accountDO = new AccountDO(accountDTO.getUserId(), amount * -1,
                    amount * rate, amount, "USD");
        } else { // 买人民币，   交易方： 卖人民币，收美金
            double rate = rateService.getRateViaCurrencyPair("CNH|USD");
            accountDO = new AccountDO(accountDTO.getUserId(), amount * rate,
                    amount * -1, amount, "CNH");
        }
        return accountDO;
    }
}
