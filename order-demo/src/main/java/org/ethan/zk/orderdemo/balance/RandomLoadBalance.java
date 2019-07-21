package org.ethan.zk.orderdemo.balance;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

@Component
public class RandomLoadBalance implements LoadBalance {

    private List<String> address;

    @Override
    public void setServerList(List<String> address) {
        this.address = address;
    }

    @Override
    public String balance() {
        if (!CollectionUtils.isEmpty(address)) {
            int index = new Random().nextInt(address.size());
            return address.get(index);
        }
        return "";
    }
}
