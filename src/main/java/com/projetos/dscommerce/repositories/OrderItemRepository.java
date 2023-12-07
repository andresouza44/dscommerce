package com.projetos.dscommerce.repositories;

import com.projetos.dscommerce.entities.OrderItem;
import com.projetos.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem , OrderItemPK> {
}
