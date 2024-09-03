package org.example.appexamen.basket;

import org.example.appexamen.product.Product;
import org.example.appexamen.product.ProductRepository;
import org.example.appexamen.user.User;
import org.example.appexamen.user.UserRepository;
import org.example.appexamen.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public BasketService(BasketRepository basketRepository, UserRepository userRepository, ProductRepository productRepository, UserService userService) {
        this.basketRepository = basketRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public Optional<Basket> getBasket() {
        Long currentUserId=userService.getCurrentUserId();
        return basketRepository.findByUserId(currentUserId);
    }

    public void addProductToBasket(Long productId, Basket basket) {

        User activeUser = userRepository.findByIsActive(true)
                .orElseThrow(()-> new IllegalStateException("No active user"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product not found with ID: " + productId));
        if(basket.getUser().equals(activeUser))
        {
            basket.addProduct(product);
        }
        else
        {
            basket=createNewBasket(activeUser);
            basket.addProduct(product);
        }
        basketRepository.save(basket);
    }

    private Basket createNewBasket(User user) {
        Basket newBasket = new Basket();
        newBasket.setUser(user);
        return basketRepository.save(newBasket);
    }

    public void removeItemFromBasket(Long productId, Basket basket) {
        User activeUser = userRepository.findByIsActive(true)
                .orElseThrow(()-> new IllegalStateException("No active user"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product not found with ID: " + productId));
        if(basket.getUser().equals(activeUser))
        {
            if(basket.getProducts().contains(product))
                basket.removeProduct(product);
            else
                System.out.println("Product is not in basket");
        }
        else
        {
            throw new RuntimeException("Basket/User mismatch error");
        }
        basketRepository.save(basket);
    }


    public Basket getById(Long id) {
        Optional<Basket> basketOptional = basketRepository.findById(id);

        if (basketOptional.isPresent()) {
            return basketOptional.get();
        } else {
            throw new IllegalStateException("Basket not found with ID: " + id);
        }
    }
}
