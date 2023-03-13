package com.useandsell.webstoreservices.controller;

import com.useandsell.webstoreservices.dto.Cart;
import com.useandsell.webstoreservices.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class CartController {
    private static final Logger LOGGER =
            Logger.getLogger(CartController.class.getName());
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(path = "api/cartsAll")
    @GetMapping
    public ResponseEntity<List<Cart>> getCartItems() throws Exception {
        try {
            return new ResponseEntity<>(cartService.getCartItems(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    @RequestMapping(path = "api/{customerid}/cartsAll")
    @GetMapping
    public Cart getCustomerCartItems(
            @PathVariable("customerid") Long customerid
    ) throws Exception {

        try {
            return cartService.getCustomerCartItems(customerid);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    @RequestMapping(path = "api/{customerid}/{productid}/addToCart")
    @PostMapping
    public ResponseEntity<String> addToCart(
            @PathVariable("customerid") Long customerid,
            @PathVariable("productid") Long productid
    ) {
        try {
            cartService.addToCart(productid, customerid);
            return new ResponseEntity<>("Product added to cart successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception Occurred: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "api/{customerid}/{productid}/deletefromCart")
    public ResponseEntity<String> deletefromCart(
            @PathVariable("customerid") Long customerid,
            @PathVariable("productid") Long productid
    ) {
        try {
            cartService.deleteProductFromCart(productid, customerid);
            return new ResponseEntity<>("Product deleted from cart successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Product not found in cart ", HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "api/{customerid}/deleteCart")
    public ResponseEntity<String> deletefromCart(
            @PathVariable("customerid") Long customerid

    ) {
        try {
            cartService.deleteCart(customerid);
            return new ResponseEntity<>("Cart deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cart not found ", HttpStatus.OK);
        }
    }

}
