package com.yezp.xa_2pc.example;

import java.io.IOException;
import java.sql.SQLException;

public class Application {

    public static void main(final String[] args) throws IOException, SQLException {
        XAOrderService orderService = new XAOrderService("/META-INF/sharding-databases-tables.yaml");
        orderService.init();
        orderService.insert();
        orderService.cleanup();
    }

}
