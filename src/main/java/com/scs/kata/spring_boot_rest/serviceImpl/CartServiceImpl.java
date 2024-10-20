package com.scs.kata.spring_boot_rest.serviceImpl;

import com.scs.kata.spring_boot_rest.exception.InvalidInputException;
import com.scs.kata.spring_boot_rest.model.Book;
import com.scs.kata.spring_boot_rest.model.MyCart;
import com.scs.kata.spring_boot_rest.model.User;
import com.scs.kata.spring_boot_rest.model.api.AddCartRequest;
import com.scs.kata.spring_boot_rest.model.api.AddCartResponse;
import com.scs.kata.spring_boot_rest.model.api.ChangeCartResponse;
import com.scs.kata.spring_boot_rest.model.api.RetrieveCartResponse;
import com.scs.kata.spring_boot_rest.repository.IBookRepository;
import com.scs.kata.spring_boot_rest.repository.ICartRepository;
import com.scs.kata.spring_boot_rest.service.ICartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
    public RetrieveCartResponse getCart(int userId) {
        MyCart myCart = cartRepository.findByUserId(userId);
        if (myCart == null) {
            return null; // No cart found for the specified user
        }
        return modelMapper.map(myCart, RetrieveCartResponse.class);
    }

    @Override
    @Transactional
    public AddCartResponse addCart(AddCartRequest addCartRequest) {
        AddCartResponse addCartResponse = new AddCartResponse();
        try {
            User user = new User();
            user.setId(1);

            MyCart existingMyCart = cartRepository.findByUserId(addCartRequest.getUserId());
            if (existingMyCart != null) {
                throw new InvalidInputException("A shopping cart already exists for this user.");
            }

            Book book = bookRepository.findById(addCartRequest.getShoppingCartItems().get(0).getBookId())
                    .orElseThrow(() -> new InvalidInputException("The specified book could not be found."));

            MyCart myCart = new MyCart(user);
            myCart.addBook(book, 1);
            MyCart savedMyCart = cartRepository.save(myCart);
            addCartResponse.setCartId(savedMyCart.getCartId());
            return addCartResponse;
        } catch (InvalidInputException ex) {
            addCartResponse.setErrorMessage(ex.getMessage());
            return addCartResponse;
        } catch (Exception ex) {
            throw ex; // Rethrow unexpected exceptions
        }
    }

    @Override
    public ChangeCartResponse updateBookQuantity(int cartId, int bookId, int quantity) {
        ChangeCartResponse changeCartResponse = new ChangeCartResponse();
        try {
            MyCart existingMyCart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new InvalidInputException("The specified cart was not found."));
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new InvalidInputException("The specified book was not found."));

            BigDecimal modifiedCartPrice = existingMyCart.addBook(book, quantity);
            cartRepository.save(existingMyCart);
            changeCartResponse.setTotalPrice(modifiedCartPrice);
            return changeCartResponse;
        } catch (InvalidInputException ex) {
            changeCartResponse.setErrorMessage(ex.getMessage());
            return changeCartResponse;
        } catch (Exception ex) {
            throw ex; // Rethrow unexpected exceptions
        }
    }

    @Override
    public ChangeCartResponse deleteItemFromCart(int cartId, int bookId) throws Exception {
        ChangeCartResponse changeCartResponse = new ChangeCartResponse();
        try {
            MyCart myCart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new InvalidInputException("The specified cart was not found."));
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new InvalidInputException("The specified book was not found."));

            BigDecimal modifiedCartPrice = myCart.removeBook(book);
            changeCartResponse.setTotalPrice(modifiedCartPrice);
            cartRepository.save(myCart);
            return changeCartResponse;
        } catch (InvalidInputException ex) {
            changeCartResponse.setErrorMessage(ex.getMessage());
            return changeCartResponse;
        } catch (Exception ex) {
            throw ex; // Rethrow unexpected exceptions
        }
    }

    public ChangeCartResponse deleteCart(int cartId) {
        ChangeCartResponse changeCartResponse = new ChangeCartResponse();
        try {
            MyCart myCart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new InvalidInputException("The specified cart was not found."));

            cartRepository.delete(myCart);
            return changeCartResponse;
        } catch (InvalidInputException ex) {
            changeCartResponse.setErrorMessage(ex.getMessage());
            return changeCartResponse;
        } catch (Exception ex) {
            throw ex; // Rethrow unexpected exceptions
        }
    }
}