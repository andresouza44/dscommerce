package com.projetos.dscommerce.servicies;

import com.projetos.dscommerce.dto.OrderDTO;
import com.projetos.dscommerce.dto.OrderItemDTO;
import com.projetos.dscommerce.entities.Order;
import com.projetos.dscommerce.entities.OrderItem;
import com.projetos.dscommerce.entities.OrderStatus;
import com.projetos.dscommerce.entities.Product;
import com.projetos.dscommerce.repositories.OrderItemRepository;
import com.projetos.dscommerce.repositories.OrderRepository;
import com.projetos.dscommerce.repositories.ProductRepository;
import com.projetos.dscommerce.servicies.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    AuthService authService;


    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);

    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        order.setClient(userService.authenticated());

        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        ;

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());


        return new OrderDTO(order);


    }

}
