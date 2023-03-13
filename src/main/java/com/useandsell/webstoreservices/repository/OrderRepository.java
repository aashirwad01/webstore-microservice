package com.useandsell.webstoreservices.repository;

import com.useandsell.webstoreservices.dto.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query(value = "{'customerid': ?0} ")
    List<Order> getOrderByUserId(Long customerid);

    @Query(value = "{'customerid': ?0} ", exists = true)
    boolean findOrderByUserId(Long customerid);
}
