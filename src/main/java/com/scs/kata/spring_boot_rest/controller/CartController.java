package com.scs.kata.spring_boot_rest.controller;


//import com.scs.kata.spring_boot_rest.mapper.ModelMapper;
import com.scs.kata.spring_boot_rest.model.api.*;
import com.scs.kata.spring_boot_rest.service.ICartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/carts")
public class CartController {
    @Autowired
    ICartService cartService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("getBookCart")
    public ResponseEntity<RetrieveCartResponse> getCart(@RequestParam int userId) {
        var result = cartService.getCart(userId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

   @PostMapping("createBookCart")
    public ResponseEntity<AddCartResponse> generateNewCartOrder(@RequestBody AddCartRequest addCartRequest) {
        var result = cartService.addCart(addCartRequest);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(OK).body(result);
        }
        return new ResponseEntity(result, CREATED);
    }

    @PutMapping("updateBookCart")
    public ResponseEntity<ChangeCartResponse> updateExistingBooksCart(@RequestBody ChangeCartRequest changeCartRequest) {
        var result = cartService.updateBookQuantity(changeCartRequest.getCartId(), changeCartRequest.getBookId(), changeCartRequest.getQuantity());
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return new ResponseEntity(result, OK);
    }

    @DeleteMapping("removeCartItem")
    public ResponseEntity<ChangeCartResponse> deleteItemsFromCart(@RequestParam int cartId, int bookId) throws Exception {
        var result = cartService.deleteItemFromCart(cartId, bookId);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return new ResponseEntity(result, OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ChangeCartResponse> deleteCart(@RequestParam int cartId) {
        var result = cartService.deleteCart(cartId);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return new ResponseEntity(result, OK);
    }
}
