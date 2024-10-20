package com.scs.kata.spring_boot_rest.controller;


import com.scs.kata.spring_boot_rest.model.api.PlaceOrderRequest;
import com.scs.kata.spring_boot_rest.model.api.PlaceOrderResponse;
import com.scs.kata.spring_boot_rest.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin
@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    @Autowired
    IOrderService orderService;

 @PostMapping("placeOrder")
   public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest) {
        var result = orderService.placeOrder(placeOrderRequest);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return new ResponseEntity(result, CREATED);
    }

}
