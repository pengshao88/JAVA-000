package com.yezp.sharding.master.slave;

import com.yezp.sharding.master.slave.domain.Order;
import com.yezp.sharding.master.slave.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ShardingMasterSlaveApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(ShardingMasterSlaveApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("============== > write" );
        orderService.createOrder();

        System.out.println("============== > read" );
        List<Order> orderList = orderService.getOrderList();
        orderList.forEach(o -> System.out.println(o));
    }
}
