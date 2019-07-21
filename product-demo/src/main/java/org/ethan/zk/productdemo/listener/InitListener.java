package org.ethan.zk.productdemo.listener;

import org.ethan.zk.productdemo.zk.ServerRegister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class InitListener implements ServletContextListener {

    @Value("${server.port}")
    private int port;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            String address = InetAddress.getLocalHost().getHostAddress();
            //将IP与端口注册写入zk节点
            ServerRegister.register(address, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
