package com.yezp.gateway;

import com.yezp.gateway.inbound.HttpInboundServer;
import com.yezp.gateway.router.GatewayRouter;

/**
 * 网关服务器 v1.0.0
 */
public class GatewayApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        String gatewayPort = System.getProperty("gatewayPort", "8888");
        int port = Integer.parseInt(gatewayPort);

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port
                + " for server:" + GatewayRouter.proxyServerUrl());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
