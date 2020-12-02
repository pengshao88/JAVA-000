package com.yezp.read_write_01.common;

/**
 * Description: 读写 枚举类型
 * Created on 2020/12/3 0:18.
 *
 * @author yezp
 */
public enum DataSourceType {

    read("read", "从库"), write("write", "主库");

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    private String type;
    private String name;


    public String getType() {
        return type;
    }

}
