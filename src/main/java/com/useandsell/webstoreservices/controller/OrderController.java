package com.useandsell.webstoreservices.controller;

import com.useandsell.webstoreservices.dto.Order;
import com.useandsell.webstoreservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "api/{customerid}/ordersAll")
    @GetMapping
    public List<Order> getCustomerOrderItems(
            @PathVariable("customerid") Long customerid
    ) throws Exception {

        try {
            return orderService.getCustomerOrders(customerid);
        } catch (Exception e) {

            throw new Exception("Exception Occurred: ", e);
        }
    }

    @RequestMapping(path = "api/order/price")
    @PostMapping
    public Double getOrderItemsPrice(
            @RequestBody ArrayList<Map<String, Integer>> values
    ) throws Exception {

        try {
            Map<Long, Integer> orderitems = new HashMap<>();

            values.forEach((val) -> {
                val.forEach((key, value) -> {

                    orderitems.put(Long.parseLong(key), value);
                });

            });

            return orderService.getOrderPrice(orderitems);
        } catch (Exception e) {

            throw new Exception("Exception Occurred: ", e);
        }
    }

    @RequestMapping(path = "api/{customerid}/{modeofpayment}/addToOrder")
    @PostMapping
    public ResponseEntity<String> addToOrder(
            @PathVariable("customerid") Long customerid,
            @PathVariable("modeofpayment") String modeofpayment,
            @RequestBody ArrayList<Map<String, Integer>> values
    ) {

        try {
            System.out.println("values are" + values);
            Map<Long, Integer> orderitems = new HashMap<>();
            values.forEach((val) -> {
                val.forEach((key, value) -> {

                    orderitems.put(Long.parseLong(key), value);
                });

            });
            orderService.addToOrder(

                    customerid,
                    modeofpayment,

                    orderitems);
            return new ResponseEntity<>("Order Created", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception Occurred: Product not available ", HttpStatus.OK);
        }
    }


}
