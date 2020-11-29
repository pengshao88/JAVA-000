package com.yezp.initsqldata.domain;

import lombok.Data;

/**
 * Description: 用户表
 * Created on 2020/11/29 22:03.
 *
 * @author yezp
 */
@Data
public class User {

    private long id;

    /** 用户id **/
    private long userId;

    /** 用户名 **/
    private String userName;

    /** 用户手机号 **/
    private int phone;

    /** 地址 **/
    private String address;

    /** 年龄 **/
    private int age;

    /** 0 女 1 男 **/
    private int sex;

    /** 创建时间 **/
    private long createTime;

    /** 更新时间 **/
    private long updateTime;

}