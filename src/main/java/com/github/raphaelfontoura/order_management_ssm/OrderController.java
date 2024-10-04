package com.github.raphaelfontoura.order_management_ssm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("new")
    public String newOrder() {
        service.newOrder();
        return "new order";
    }
    
    @PostMapping("pay")
    public String payOrder() {
        service.payOrder();
        return "pay order";
    }

    @PostMapping("ship")
    public String shipOrder() {
        service.shipOrder();
        return "ship order";
    }
    
    @PostMapping("complete")
    public String completeOrder() {
        service.completeOrder();
        return "complete order";
    }
}
