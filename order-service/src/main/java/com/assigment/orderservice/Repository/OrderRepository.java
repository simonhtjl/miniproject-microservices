package com.assigment.orderservice.Repository;

import com.assigment.orderservice.Entity.OrderRuangan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderRuangan,String> {
}
