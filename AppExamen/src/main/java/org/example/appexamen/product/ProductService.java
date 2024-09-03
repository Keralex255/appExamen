package org.example.appexamen.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());

        productRepository.save(product);
    }
}
