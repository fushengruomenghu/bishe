package com.hyl.bishe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;


@SpringBootApplication
public class BisheApplication {
    public static void main(String[] args) {
        SpringApplication.run(BisheApplication.class, args);
        System.out.println("欢迎使用");
    }
//   @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("应用已经准备就绪 ... 启动浏览器");
        String url = "http://localhost:8081/";
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    /**
//     * 获取当前机器的端口号
//     *
//     * @return
//     * @throws MalformedObjectNameException
//     */
//    public static String getLocalPort() {
//        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
//        Set<ObjectName> objectNames = null;
//        try {
//            objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
//                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
//            String port = objectNames.iterator().next().getKeyProperty("port");
//            return port;
//        } catch (MalformedObjectNameException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    String url = "http://localhost:" + getLocalPort();
}
