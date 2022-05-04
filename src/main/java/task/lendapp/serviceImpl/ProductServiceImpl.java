package task.lendapp.serviceImpl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import task.lendapp.entity.Product;
import task.lendapp.service.ProductService;

import java.util.HashMap;
import java.util.Optional;

@Service
@Slf4j
@ApplicationScope
public class ProductServiceImpl implements ProductService {


    @Override
    public HashMap listProducts(){
        HashMap<Integer, Product> productStore = new HashMap();
        Product product = new Product(100.0,1,12.5,30,"yearly",12.5,"$");
        productStore.put(product.getId(), product);
        product = new Product(50.0,2,5.0,15,"monthly",10.0,"$");
        productStore.put(product.getId(), product);
        product = new Product(25.0,3,1.88,4,"yearly",7.5,"$");
        productStore.put(product.getId(), product);


        return productStore;
    }

    @Override
    public Optional<Product> findProductById(Integer productId) {
        HashMap<Integer,Product> productHashMap=listProducts();
        if(productHashMap.containsKey(productId))
            return Optional.of(productHashMap.get(productId));
        return Optional.empty();
    }

}
