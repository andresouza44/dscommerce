package com.projetos.dscommerce.servicies;

import com.projetos.dscommerce.dto.OrderDTO;
import com.projetos.dscommerce.entities.Order;
import com.projetos.dscommerce.repositories.OrderRepository;
import com.projetos.dscommerce.servicies.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderDTO findById (Long id){
        Order order = repository.findById(id).
                orElseThrow( () -> new ResourceNotFoundException("Order no found"));
        return  new OrderDTO(order);

    }

}
