package org.example.appexamen.basket;

import jakarta.persistence.*;
import org.example.appexamen.product.Product;
import org.example.appexamen.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @SequenceGenerator(
            name="basket_sequence",
            sequenceName="basket_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="basket_sequence"
    )
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "basket_products",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public Basket() {
    }

    public Basket(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", user=" + user +
                ", products=" + products +
                '}';
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.removeIf(p -> p.equals(product));
    }

}
