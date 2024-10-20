package com.scs.kata.spring_boot_rest.controller;


//import com.scs.kata.spring_boot_rest.mapper.ModelMapper;
import com.scs.kata.spring_boot_rest.model.api.CreateCartRequest;
import com.scs.kata.spring_boot_rest.model.api.CreateCartResponse;
import com.scs.kata.spring_boot_rest.model.api.GetShoppingCartResponse;
import com.scs.kata.spring_boot_rest.service.ICartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/carts")
public class CartController {
    @Autowired
    ICartService cartService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("getShoppingCart")
    public ResponseEntity<GetShoppingCartResponse> getCart(@RequestParam int userId) {
        var result = cartService.getCart(userId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

   @PostMapping("create")
    public ResponseEntity<CreateCartResponse> generateNewCartOrder(@RequestBody CreateCartRequest createCartRequest) {
        var result = cartService.createCart(createCartRequest);
        if (result.getErrorMessage() != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return new ResponseEntity(result, CREATED);
    }
//
//    @PostMapping("update")
//    public ResponseEntity<UpdateShoppingCartResponse> updateShoppingCart(@RequestBody UpdateShoppingCartRequest updateShoppingCartRequest) {
//        var result = cartService.updateBookQuantity(updateShoppingCartRequest.getCartId(), updateShoppingCartRequest.getBookId(), updateShoppingCartRequest.getQuantity());
//        if (result.getErrorMessage() != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//        }
//        return new ResponseEntity(result, CREATED);
//    }
//
//    @DeleteMapping("removeCartItem")
//    public ResponseEntity<UpdateShoppingCartResponse> removeCartItem(@RequestParam int cartId, int bookId) throws Exception {
//        var result = cartService.removeCartItem(cartId, bookId);
//        if (result.getErrorMessage() != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//        }
//        return new ResponseEntity(result, OK);
//    }
//
//    @DeleteMapping("delete")
//    public ResponseEntity<UpdateShoppingCartResponse> deleteCart(@RequestParam int cartId) {
//        var result = cartService.deleteCart(cartId);
//        if (result.getErrorMessage() != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//        }
//        return new ResponseEntity(result, OK);
//    }
}
