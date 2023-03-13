package com.useandsell.webstoreservices.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("review")
public class Review {
    @Id
    String id;
    private Long productid;
    private Long customerid;

    private String rating;

    public Review() {
    }

    public Review(Long productid,
                  Long customerid,
                  String rating) {
        this.productid = productid;
        this.customerid = customerid;
        this.rating = rating;
    }

    public Review(Long customerid, String rating) {
        this.customerid = customerid;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {

        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "productid=" + productid +
                ", customerid=" + customerid +
                ", rating=" + rating +
                '}';
    }
}
