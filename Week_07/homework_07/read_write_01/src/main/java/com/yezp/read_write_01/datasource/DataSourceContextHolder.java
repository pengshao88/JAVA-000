package com.yezp.read_write_01.datasource;

import com.yezp.read_write_01.common.DataSourceType;
import org.apache.log4j.Logger;

/**
 * Description:本地全局变量
 * Created on 2020/12/3 0:19.
 *
 * @author yezp
 */
public class DataSourceContextHolder {

    private static  Logger logger = Logger.getLogger(DataSourceContextHolder.class);
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        logger.debug("读操作-----");
        local.set(DataSourceType.read.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        logger.debug("写操作-----");
        local.set(DataSourceType.write.getType());
    }

    public static void clearDB(){
        local.remove();
    }

    public static String getJdbcType() {
        return local.get();
    }

}
