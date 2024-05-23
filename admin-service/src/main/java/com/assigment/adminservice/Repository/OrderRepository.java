package com.assigment.adminservice.Repository;

import com.assigment.adminservice.Entity.OrderRuangan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderRuangan,String> {
}
