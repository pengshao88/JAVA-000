package com.yezp;

import org.springframework.stereotype.Component;

/**
 * Description:
 * Created on 2020/11/15 20:27.
 *
 * @author yezp
 */
@Component("dog")
public class Dog implements Animal {
    @Override
    public String say() {
        return "汪汪汪~~";
    }

    @Override
    public String walk() {
        return "四只脚~~~~走路";
    }
}
