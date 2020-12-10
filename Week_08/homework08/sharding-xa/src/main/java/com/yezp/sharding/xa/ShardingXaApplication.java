package com.yezp.sharding.xa;

import com.yezp.sharding.xa.config.TransactionConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TransactionConfiguration.class)
public class ShardingXaApplication implements CommandLineRunner {

    @Autowired
    private XAOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(ShardingXaApplication.class, args);
    }

    @Override
    public void run(String... strings) {
        System.out.println("=================== start ===================");
        orderService.init();
        orderService.selectAll();
        orderService.cleanup();
        System.out.println("=================== end ===================");
    }
}
