package com.useandsell.webstoreservices.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "cart")
public class Cart {

    @Id
    String id;
    Map<Long, Integer> cartitems; //product id and quantity of item added in cart
    @Indexed(unique = true)
    private Long customerid;

    public Cart() {
    }

    public Cart(Long customerid, Map<Long, Integer> cartitems) {
        this.customerid = customerid;
        this.cartitems = cartitems;
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

    public Map<Long, Integer> getCartitems() {
        return cartitems;
    }

    public void setCartitems(Map<Long, Integer> cartitems) {
        this.cartitems = cartitems;
    }
}


