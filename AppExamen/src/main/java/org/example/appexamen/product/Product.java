package org.example.appexamen.product;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(
            name="product_sequence",
            sequenceName="product_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator="product_sequence"
    )
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price", nullable = false)
    private Double price;

    @Column(name="category", nullable = false)
    private String category;

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Product(String name, String description, Double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
