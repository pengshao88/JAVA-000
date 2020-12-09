package com.yezp.shardingdemo;

import com.yezp.shardingdemo.domain.Order;
import com.yezp.shardingdemo.service.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class ShardingDemoApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(ShardingDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("-------------- Process Success Begin ---------------");
        orderService.insertData();
        orderService.printData();
        for (Order order : orderService.selectAll()) {
            if (order.getOrderId() % 2 == 0) {
                order.setStatus(3);
                orderService.updateData(order);
            }
        }
        orderService.printData();
//        orderService.deleteData(orderIds);
        orderService.printData();
        System.out.println("-------------- Process Success Finish --------------");
    }
}
