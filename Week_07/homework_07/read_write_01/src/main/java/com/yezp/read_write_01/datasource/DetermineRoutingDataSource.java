package com.yezp.read_write_01.datasource;

import com.yezp.read_write_01.common.DataSourceType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 动态切管数据源
 * Created on 2020/12/3 0:14.
 *
 * @author yezp
 */
public class DetermineRoutingDataSource extends AbstractRoutingDataSource {

    private final int dataSourceNumber;
    private AtomicInteger count = new AtomicInteger(0);

    public DetermineRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        // 写
        if (typeKey.equals(DataSourceType.write.getType())) {
            return DataSourceType.write.getType();
        }

        // 简单负载均衡  -- 读
        int number = count.getAndAdd(1);
        int lookupKey = number % dataSourceNumber;
        return new Integer(lookupKey);
    }
}
