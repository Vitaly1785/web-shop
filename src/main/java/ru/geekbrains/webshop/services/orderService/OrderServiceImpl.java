package ru.geekbrains.webshop.services.orderService;

import org.springframework.stereotype.Service;
import ru.geekbrains.webshop.dao.OrderDao;
import ru.geekbrains.webshop.entity.Order;
import ru.geekbrains.webshop.entity.Person;
import ru.geekbrains.webshop.entity.Product;
import ru.geekbrains.webshop.services.productService.ProductService;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderDao orderDao;
    private final ProductService productService;

    public OrderServiceImpl(OrderDao orderDao, ProductService productService) {
        this.orderDao = orderDao;
        this.productService = productService;
    }


    @Override
    public Iterable<Order> showOrdersPerson(Person person) {
        return orderDao.findByPerson(person);
    }

    @Override
    public Integer addProductQuantity(Long productId, Integer quantity, Person person) {
        Integer addedQuantity = quantity;
        Product product = productService.findById(productId);
        Order order = orderDao.findByPersonAndProduct(person, product);
        if (order != null){
            addedQuantity = order.getQuantity() + quantity;
            order.setQuantity(addedQuantity);
        } else{
            order = new Order();
            order.setQuantity(quantity);
            order.setProduct(product);
            order.setPerson(person);
        }

        orderDao.save(order);
        return addedQuantity;
    }


}
