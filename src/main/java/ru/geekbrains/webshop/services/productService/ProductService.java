package ru.geekbrains.webshop.services.productService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.geekbrains.webshop.dto.ProductDto;
import ru.geekbrains.webshop.entity.Product;

import java.util.List;


public interface ProductService {
    Iterable<Product> findAll();

    Iterable<Product> findAllById(List<Long> ids);

    Product findById(Long id);

    Product createProduct(ProductDto productDto);

    Product updateProduct(ProductDto productDto);

    void deleteProduct(Long id);

    Page<Product> getProductMaxPrice(Pageable pageable);

    Page<Product> getProductsMinPrice(Pageable pageable);

    Page<Product> showAll(Pageable pageable);

    void updateProductById(Product product, Long id);

}
