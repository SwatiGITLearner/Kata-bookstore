package com.scs.kata.spring_boot_rest.serviceImpl;

import com.scs.kata.spring_boot_rest.exception.InvalidInputException;
import com.scs.kata.spring_boot_rest.model.Book;
import com.scs.kata.spring_boot_rest.model.Cart;
import com.scs.kata.spring_boot_rest.model.User;
import com.scs.kata.spring_boot_rest.model.api.CreateCartRequest;
import com.scs.kata.spring_boot_rest.model.api.CreateCartResponse;
import com.scs.kata.spring_boot_rest.model.api.GetShoppingCartResponse;
import com.scs.kata.spring_boot_rest.repository.IBookRepository;
import com.scs.kata.spring_boot_rest.repository.ICartRepository;
import com.scs.kata.spring_boot_rest.service.ICartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    ICartRepository cartRepository;

    @Autowired
    IBookRepository bookRepository;
//
//    @Autowired
//    IUserRepository userRepository;

    @Autowired
    private org.modelmapper.ModelMapper modelMapper;

    @Override
    /* Returns existing cart of user */
  public GetShoppingCartResponse getCart(int userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            return null;
        }
        return modelMapper.map(cart, GetShoppingCartResponse.class);
    }

//    /* Creates new cart for user, returns cartId */
    @Override
    @Transactional
    public CreateCartResponse createCart(CreateCartRequest createCartRequest) {
        CreateCartResponse createCartResponse = new CreateCartResponse();
        try {

            User user = new User();
            user.setId(123);

            Cart optionalShoppingCart = cartRepository.findByUserId(createCartRequest.getUserId());
            if (optionalShoppingCart != null) {
                throw new InvalidInputException("Shopping cart already exists");
            }
            Book book = bookRepository.findById(createCartRequest.getShoppingCartItems().get(0).getBookId())
                    .orElseThrow(() -> new InvalidInputException("book not found"));



            Cart cart = new Cart(user);
            cart.addBook(book, 1);
            var result = cartRepository.save(cart);
            createCartResponse.setCartId(result.getCartId());
            return createCartResponse;
        } catch (InvalidInputException ex) {
            createCartResponse.setErrorMessage(ex.getMessage());
            return createCartResponse;
        } catch (Exception ex) {
            throw ex;
        }
    }

//    /* Update book quantity of the cart item, calculates new price */
//    /*@Override
//    public UpdateShoppingCartResponse updateBookQuantity(int cartId, int bookId, int quantity) {
//        //
//        UpdateShoppingCartResponse updateShoppingCartResponse = new UpdateShoppingCartResponse();
//        try {
//            Cart existingCart = cartRepository.findById(cartId)
//                    .orElseThrow(() -> new InvalidInputException("Cart not found"));
//            Book book = bookRepository.findById(bookId)
//                    .orElseThrow(() -> new InvalidInputException("book not found"));
//
//            var modifiedCartPrice =  existingCart.addBook(book,quantity);
//            cartRepository.save(existingCart);
//            updateShoppingCartResponse.setTotalPrice(modifiedCartPrice);
//            return updateShoppingCartResponse;
//        } catch (InvalidInputException ex) {
//            updateShoppingCartResponse.setErrorMessage(ex.getMessage());
//            return updateShoppingCartResponse;
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }

//    /* Remove book from cart items table */
//    /*@Override
//    public UpdateShoppingCartResponse removeCartItem(int cartId, int bookId) throws Exception{
//        UpdateShoppingCartResponse updateShoppingCartResponse = new UpdateShoppingCartResponse();
//        try {
//            Cart cart = cartRepository.findById(cartId)
//                    .orElseThrow(() -> new InvalidInputException("Cart not found"));
//            Book book = bookRepository.findById(bookId)
//                    .orElseThrow(() -> new InvalidInputException("book not found"));
//
//            var modifiedCartPrice = cart.removeBook(book);
//            updateShoppingCartResponse.setTotalPrice(modifiedCartPrice);
//            return updateShoppingCartResponse;
//        } catch (InvalidInputException ex) {
//            updateShoppingCartResponse.setErrorMessage(ex.getMessage());
//            return updateShoppingCartResponse;
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }

//    /* Deletes cart */
//   /* @Override
//    public UpdateShoppingCartResponse deleteCart(int cartId) {
//        UpdateShoppingCartResponse updateShoppingCartResponse = new UpdateShoppingCartResponse();
//        try {
//            Cart cart = cartRepository.findById(cartId)
//                    .orElseThrow(() -> new InvalidInputException("Cart not found"));
//
//            cartRepository.delete(cart);
//            return updateShoppingCartResponse;
//        } catch (InvalidInputException ex) {
//            updateShoppingCartResponse.setErrorMessage(ex.getMessage());
//            return updateShoppingCartResponse;
//        } catch (Exception ex) {
//            throw ex;
//        }
//    }
}