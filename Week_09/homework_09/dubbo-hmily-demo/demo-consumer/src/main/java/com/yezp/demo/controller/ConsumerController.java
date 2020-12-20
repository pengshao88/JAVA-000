package com.yezp.demo.controller;

import com.yezp.api.dto.AccountDTO;
import com.yezp.api.entity.AccountDO;
import com.yezp.demo.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/20 16:10.
 *
 * @author yezp
 */
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("all")
    public List<AccountDO> all() {
        return consumerService.findAllAccount();
    }

    @PostMapping("doTcc")
    public int doTcc(@RequestBody AccountDTO accountDTO) {
        return consumerService.updateAccount(accountDTO);
    }
}
