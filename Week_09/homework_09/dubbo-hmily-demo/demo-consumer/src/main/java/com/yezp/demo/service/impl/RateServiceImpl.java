package com.yezp.demo.service.impl;

import com.yezp.demo.service.RateService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created on 2020/12/20 15:16.
 *
 * @author yezp
 */
@Service
public class RateServiceImpl implements RateService {

    @Override
    public double getRateViaCurrencyPair(String currencyPair) {
        double rate;
        if ("USD|CNH".equals(currencyPair)) {
            rate = 6.666;
        } else if ("CNH|USD".equals(currencyPair)) {
            rate = 0.15;
        } else rate = 1;
        return rate;
    }

}
