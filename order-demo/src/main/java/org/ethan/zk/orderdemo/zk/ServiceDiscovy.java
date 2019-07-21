package org.ethan.zk.orderdemo.zk;

import org.I0Itec.zkclient.ZkClient;
import org.ethan.zk.orderdemo.balance.LoadBalance;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServiceDiscovy {

    private static final String SERVICE_NODE_NAME = "/services/products";

    private static ZkClient client;

    public static void discovy(LoadBalance loadBalance) {
        if (client == null) {
            client = new ZkClient("192.168.1.210:2181");
        }
        client.subscribeChildChanges(SERVICE_NODE_NAME, (parentPath, childPaths) -> {
            updateList(childPaths, loadBalance);
        });
        List<String> children = client.getChildren(SERVICE_NODE_NAME);
        updateList(children, loadBalance);
    }

    private static void updateList(List<String> childPaths, LoadBalance loadBalance) {
        System.out.println("childpath=" + childPaths);
        if (!CollectionUtils.isEmpty(childPaths)) {
            List<String> serverAddress = new ArrayList<>();
            childPaths.forEach(path -> serverAddress.add(client.readData(SERVICE_NODE_NAME + File.separator + path)));
            System.out.println("serverAddress=" + serverAddress);
            loadBalance.setServerList(serverAddress);
        }
    }
}
