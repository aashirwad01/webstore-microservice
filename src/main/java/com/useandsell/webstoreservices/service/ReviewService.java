package com.useandsell.webstoreservices.service;

import com.useandsell.webstoreservices.dto.Review;
import com.useandsell.webstoreservices.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ReviewService {
    private static final Logger LOGGER =
            Logger.getLogger(ReviewService.class.getName());
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {

        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews() throws Exception {
        try {
            return reviewRepository.findAll();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    public List<Review> getProductReviews(Long productid) throws Exception {
        try {
            return reviewRepository.findReviewByProductId(productid);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    public void addNewReview(Long productid, Long userid, String rating) throws Exception {
        try {

            Review review = new Review(productid, userid, rating);
            reviewRepository.save(review);

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }

    }
}
