package task.lendapp.service;


import task.lendapp.entity.Product;

import java.util.HashMap;
import java.util.Optional;

public interface ProductService {
    HashMap listProducts();
    Optional<Product> findProductById(Integer productId);
}
