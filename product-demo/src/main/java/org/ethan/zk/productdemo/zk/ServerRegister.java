package org.ethan.zk.productdemo.zk;

import org.I0Itec.zkclient.ZkClient;

public final class ServerRegister {

    private static final String SERVICE_NODE_NAME = "/services/products";
    private static final String SERVICE_NAME = "/node";

    /**
     * 向zookeeper注册服务地址
     * @param address
     * @param port
     */
    public static void register(String address, int port) {
        String serverPath = address + ":" + port;
        ZkClient client = new ZkClient("192.168.1.210:2181", 5000);
        if (!client.exists(SERVICE_NODE_NAME)) {
            client.createPersistent(SERVICE_NODE_NAME, true);
        }
        client.createEphemeralSequential(SERVICE_NODE_NAME + SERVICE_NAME, serverPath);
    }
}
