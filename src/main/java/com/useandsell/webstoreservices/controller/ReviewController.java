package com.useandsell.webstoreservices.controller;

import com.useandsell.webstoreservices.dto.Review;
import com.useandsell.webstoreservices.service.ReviewService;
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

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReviewController {
    private static final Logger LOGGER =
            Logger.getLogger(ReviewController.class.getName());
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(path = "api/reviewsAll")
    @GetMapping
    public List<Review> getReviews() throws Exception {
        try {
            return reviewService.getReviews();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    @RequestMapping(path = "api/{productid}/reviewsAll")
    @GetMapping
    public List<Review> getProductReviews(
            @PathVariable("productid") Long productid
    ) throws Exception {

        try {
            return reviewService.getProductReviews(productid);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    @RequestMapping(path = "api/{productid}/{userid}/addReview")
    @PostMapping
    public ResponseEntity<String> addNewReview(
            @PathVariable("productid") Long productid,
            @PathVariable("userid") Long userid,
            @RequestBody Map<String, Object> rating
    ) throws Exception {
        try {
            System.out.println(rating);

            reviewService.addNewReview(productid, userid, rating.get("rating").toString());
            return new ResponseEntity<>("Review Added", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }
}
