package com.useandsell.webstoreservices.service;

import com.useandsell.webstoreservices.dto.Order;
import com.useandsell.webstoreservices.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OrderService {
    private static final String uriPrice = "http://localhost:8081/api/order/price";
    private static final String uriQuantity = "http://localhost:8081/api/order/quantity";
    private static final Logger LOGGER =
            Logger.getLogger(OrderService.class.getName());
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Double getOrderPrice(Map<Long, Integer> orderitems) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Double result = restTemplate.postForObject(uriPrice, orderitems, Double.class);

            return result;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    public void updateOrderQuantity(Map<Long, Integer> orderitems) throws Exception {

        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(uriQuantity, orderitems);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }


    }

    public void addToOrder(Long customerid, String modeofpayment, Map<Long, Integer> map) throws Exception {
        try {


            Order order = new Order(customerid, map, modeofpayment, getOrderPrice(map));

            orderRepository.save(order);

            updateOrderQuantity(order.getOrderitems());
//            if (orderRepository.findOrderByUserId(customerid)) {
//                Order order = orderRepository.getOrderByUserId(customerid);
//                if (order.getOrderitems().containsKey(productid)) {
//                    order.getOrderitems().put(productid, order.getOrderitems().get(productid) + quantity);
//                } else {
//                    order.getOrderitems().put(productid, quantity);
//                }
//                order.setModeofpayment(modeofpayment);
//                order.setOrderprice(getOrderPrice(order.getOrderitems()));
//                orderRepository.save(order);
//                updateOrderQuantity(order.getOrderitems());
//            } else {
//                Map<Long, Integer> orderitems = new HashMap<>();
//                orderitems.put(productid, quantity);
//                Order order = new Order(customerid, orderitems, modeofpayment, getOrderPrice(orderitems));
//                orderRepository.save(order);
//                updateOrderQuantity(order.getOrderitems());
//            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    public List<Order> getCustomerOrders(Long customerid) throws Exception {

        try {
            if (!(orderRepository.findOrderByUserId(customerid))) {
                throw new Exception("No order found. Add items to order.");
            }
            return orderRepository.getOrderByUserId(customerid);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }
}
