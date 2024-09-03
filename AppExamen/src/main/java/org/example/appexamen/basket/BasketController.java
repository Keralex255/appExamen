package org.example.appexamen.basket;

import org.example.appexamen.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/basket")
public class BasketController {


    @Autowired
    private final BasketService basketService;
    @Autowired
    private UserService userService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping()
    public ResponseEntity<Optional<Basket>> getBasket() {
        Optional<Basket> basket = basketService.getBasket();
        return ResponseEntity.ok(basket);
    }

    @PutMapping("/add-to-cart")
    public ResponseEntity<Void> addItemToBasket(@RequestBody Basket basket, @RequestParam Long itemId) {
        basketService.addProductToBasket(itemId, basket);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeItemFromBasket(@PathVariable Long itemId, @RequestParam Long basketId) {
        basketService.removeItemFromBasket(itemId,basketService.getById(basketId));
        return ResponseEntity.ok().build();
    }

}
