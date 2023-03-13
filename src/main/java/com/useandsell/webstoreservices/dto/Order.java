package com.useandsell.webstoreservices.dto;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "order")
public class Order {

    @Id
    String id;
    Map<Long, Integer> orderitems; //product id and quantity of item added in cart

    private Long customerid;
    @NonNull
    private String modeofpayment;
    @NonNull
    private Double orderprice;

    public Order() {
    }


    public Order(Long customerid,
                 Map<Long, Integer> orderitems,
                 String modeofpayment,
                 Double orderprice) {
        this.customerid = customerid;
        this.orderitems = orderitems;
        this.modeofpayment = modeofpayment;
        this.orderprice = orderprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public Map<Long, Integer> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(Map<Long, Integer> orderitems) {
        this.orderitems = orderitems;
    }

    public String getModeofpayment() {
        return modeofpayment;
    }

    public void setModeofpayment(String modeofpayment) {
        this.modeofpayment = modeofpayment;
    }

    public Double getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Double orderprice) {
        this.orderprice = orderprice;
    }
}


