package ru.geekbrains.webshop.services.productService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.webshop.dao.ProductDao;
import ru.geekbrains.webshop.dto.ProductDto;
import ru.geekbrains.webshop.entity.Product;
import ru.geekbrains.webshop.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public Iterable<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    @Transactional
    public Iterable<Product> findAllById(List<Long> ids) {
        return productDao.findAllById(ids);
    }

    @Override
    @Transactional
    public Product findById(Long id) {
        return productDao.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %s not found", id)));
    }

    @Override
    @Transactional
    public Product createProduct(ProductDto productDto) {
        Product createdProduct = new Product();
        Optional<Product> product = productDao.findProductByTitle(productDto.getTitle());
        if (product.isPresent()) {
            return updateProduct(productDto);
        } else {
            createdProduct.setTitle(productDto.getTitle());
            createdProduct.setPrice(productDto.getPrice());
            productDao.save(createdProduct);
            return createdProduct;
        }
    }

    @Override
    @Transactional
    public Product updateProduct(ProductDto productDto) {
        Product product = productDao.findProductByTitle(productDto.getTitle()).orElseThrow(() -> new ProductNotFoundException(String.format("Product with title %s not found", productDto.getTitle())));
        product.setPrice(productDto.getPrice());
        productDao.save(product);
        return product;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productDao.delete(productDao.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format("Product was not deleted. Product with id %s not found", id))));
    }

    @Override
    @Transactional
    public Page<Product> getProductsMinPrice(Pageable pageable){
        return productDao.findAllByOrderByPriceDesc(pageable);
    }

    @Override
    public Page<Product> showAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public void updateProductById(Product product, Long id) {
        Optional<Product> updatableProduct = productDao.findById(id);
        if (updatableProduct.isPresent()) {
            updatableProduct.get().setPrice(product.getPrice());
            updatableProduct.get().setTitle(product.getTitle());
            productDao.save(updatableProduct.get());
        }
    }

    @Override
    @Transactional
    public Page<Product> getProductMaxPrice(Pageable pageable){
        return productDao.findAllByOrderByPriceAsc(pageable);
    }
}

