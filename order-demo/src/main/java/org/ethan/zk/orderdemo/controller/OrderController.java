package org.ethan.zk.orderdemo.controller;


import org.ethan.zk.orderdemo.balance.LoadBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalance loadBalance;

    @GetMapping("/demo")
    public String demo() {
        String address = loadBalance.balance();
        System.out.println("load balance address=" + address);
        return restTemplate.getForObject("http://" + address + "/testPath", String.class);
    }
}
