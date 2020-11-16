package com.yezp;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:属性配置类
 * Created on 2020/11/16 22:24.
 *
 * @author yezp
 */
@ConfigurationProperties(prefix = "com.school")
public class SchoolProperties {

    private int stuId;

    private String stuName;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}