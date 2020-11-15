package com.yezp;

/**
 * Description:
 * Created on 2020/11/15 21:23.
 *
 * @author yezp
 */
public class Sheep implements Animal {

    @Override
    public String say() {
        return "咩咩咩~~~~~";
    }

    @Override
    public String walk() {
        return "四只脚~~~~走路";
    }
}
