package com.scs.kata.spring_boot_rest.serviceImpl;

import com.scs.kata.spring_boot_rest.exception.InvalidInputException;
import com.scs.kata.spring_boot_rest.model.MyCart;
import com.scs.kata.spring_boot_rest.model.Order;
import com.scs.kata.spring_boot_rest.model.User;
import com.scs.kata.spring_boot_rest.model.api.PlaceOrderRequest;
import com.scs.kata.spring_boot_rest.model.api.PlaceOrderResponse;
import com.scs.kata.spring_boot_rest.repository.ICartRepository;
import com.scs.kata.spring_boot_rest.repository.IOrderRepository;
import com.scs.kata.spring_boot_rest.service.IOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;

//    @Autowired
//    IUserRepository userRepository;

    @Autowired
    ICartRepository cartRepository;

    /* Places order with given cart items, deletes existing cart */
  @Override
    @Transactional
    public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {
        //

        PlaceOrderResponse placeOrderResponse = new PlaceOrderResponse();
        try {
            MyCart myCart = cartRepository.findById(placeOrderRequest.getCartId())
                    .orElseThrow(() -> new InvalidInputException("Cart not found"));
            User user = new User();
            user.setId(123);
            Order order = myCart.createOrder(user);
            orderRepository.save(order);
            cartRepository.delete(myCart);
            return placeOrderResponse;
        } catch (InvalidInputException ex) {
            placeOrderResponse.setErrorMessage(ex.getMessage());
            return placeOrderResponse;
        } catch (Exception ex) {
            placeOrderResponse.setErrorMessage("Error while placing order");
            return placeOrderResponse;
        }
    }
}
