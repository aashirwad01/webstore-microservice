package com.useandsell.webstoreservices.repository;

import com.useandsell.webstoreservices.dto.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {


    @Query(value = "{'customerid': ?0} ")
    Cart getCartByUserId(Long customerid);

    @Query(value = "{'customerid': ?0} ", exists = true)
    boolean findCartByUserId(Long customerid);


}
