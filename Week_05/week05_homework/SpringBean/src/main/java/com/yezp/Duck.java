package com.yezp;

import org.springframework.stereotype.Component;

/**
 * Description:
 * Created on 2020/11/15 20:26.
 *
 * @author yezp
 */
@Component("Duck")
public class Duck implements Animal {
    @Override
    public String say() {
        return "嘎嘎~ 嘎嘎~";
    }

    @Override
    public String walk() {
        return "两只脚~~~走路";
    }
}
