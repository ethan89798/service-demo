package org.ethan.zk.orderdemo.balance;

import java.util.List;

public interface LoadBalance {
    void setServerList(List<String> address);
    String balance();
}
