package com.yezp.read_write_01.datasource;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description:拦截设置本地线程变量 ,切换数据源
 * Created on 2020/12/3 0:26.
 *
 * @author yezp
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DataSourceAop {

    private static Logger logger = Logger.getLogger(DataSourceAop.class);
    @Before("execution(* com.yezp.*.mapper..*.select*(..)) || " +
            "execution(* com.yezp.*.mapper..*.get*(..))|| execution(* com.yezp.*.mapper..*.query*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        logger.info("dataSource 切换到：Read");
    }

    @Before("execution(* com.yezp.*.mapper..*.insert*(..)) || execution(* com.yezp.*.mapper..*.update*(..)) || " +
            "execution(* com.yezp.*.mapper..*.delete*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        logger.info("dataSource 切换到：Write");
    }

    @After("execution(* com.yezp.*.mapper..*.*(..))")
    public void remove(){
        DataSourceContextHolder.clearDB();
        logger.info("dataSource clear");
    }

}
