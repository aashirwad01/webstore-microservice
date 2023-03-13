package com.useandsell.webstoreservices.service;

import com.useandsell.webstoreservices.dto.Cart;
import com.useandsell.webstoreservices.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CartService {
    private static final Logger LOGGER =
            Logger.getLogger(CartService.class.getName());
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getCartItems() throws Exception {
        try {
            return cartRepository.findAll();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    public void addToCart(Long productid, Long customerid) throws Exception {
        try {

            if (cartRepository.findCartByUserId(customerid)) {
                Cart cart = cartRepository.getCartByUserId(customerid);

                if (cart.getCartitems().containsKey(productid)) {
                    cart.getCartitems().put(productid, cart.getCartitems().get(productid) + 1);
                } else {
                    cart.getCartitems().put(productid, 1);
                }
                cartRepository.save(cart);
            } else {
                Map<Long, Integer> cartitems = new HashMap<>();
                cartitems.put(productid, 1);
                Cart cart = new Cart(customerid, cartitems);
                cartRepository.save(cart);
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred:", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }

    public void deleteProductFromCart(Long productid, Long customerid) throws Exception {
        try {
            if (cartRepository.findCartByUserId(customerid)) {
                Cart cart = cartRepository.getCartByUserId(customerid);
                if (cart.getCartitems().containsKey(productid)) {
                    if (cart.getCartitems().get(productid) == 1) {
                        cart.getCartitems().remove(productid);
                    } else {
                        cart.getCartitems().put(productid, cart.getCartitems().get(productid) - 1);
                    }
                    cartRepository.save(cart);
                } else {
                    throw new Exception("No such item in cart");
                }
            } else {
                throw new Exception("No cart found. Add items to cart to create cart.");

            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }


    public Cart getCustomerCartItems(Long customerid) throws Exception {
        try {
            if (!(cartRepository.findCartByUserId(customerid))) {
                throw new Exception("No cart found. Add items to cart to create cart.");
            }
            return cartRepository.getCartByUserId(customerid);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }


    public void deleteCart(Long customerid) throws Exception {
        try {
            if (!(cartRepository.findCartByUserId(customerid))) {
                throw new Exception("No cart found. Add items to cart to create cart.");
            }
            String cartid = cartRepository.getCartByUserId(customerid).getId();

            cartRepository.deleteById(cartid);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Exception Occurred: ", e.getMessage());
            throw new Exception("Exception Occurred: ", e);
        }
    }
}

