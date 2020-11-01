package com.yezp.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * Description: 网关路由器 随机策略
 * Created on 2020/11/1 15:47.
 *
 * @author yezp
 */
public class GatewayRouter implements HttpEndpointRouter {

    @Override
    public String route(List<String> endpoints) {
        Random random = new Random();
        int num = random.nextInt(endpoints.size());
        return endpoints.get(num);
    }

    public String randomUrl(String uri) {
        if (uri == null || uri.length() == 0 || "/".equals(uri)) {
            Random random = new Random();
            return "http://localhost:880" + (random.nextInt(3) + 1);
        }

        return "http://localhost:8804/test";
    }

    public static String proxyServerUrl() {
        return "http://localhost:8801; http://localhost:8802; http://localhost:8803; http://localhost:8804/test";
    }
}
