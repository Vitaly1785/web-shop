package ru.geekbrains.webshop.services.orderService;

import org.springframework.stereotype.Service;
import ru.geekbrains.webshop.dao.OrderDao;
import ru.geekbrains.webshop.dto.OrderDto;
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
    public Order createOrder(Person person, OrderDto orderDto) {
        Order order = new Order();
        order.setPerson(person);
        order.setProduct(orderDto.getProduct());
        order.setQuantity(orderDto.getQuantity());
        orderDao.save(order);
        return order;
    }

}
