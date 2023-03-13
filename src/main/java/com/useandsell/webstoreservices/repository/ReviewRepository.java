package com.useandsell.webstoreservices.repository;

import com.useandsell.webstoreservices.dto.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    @Query(value = "{'productid': ?0}")
    List<Review> findReviewByProductId(Long productid);

}
