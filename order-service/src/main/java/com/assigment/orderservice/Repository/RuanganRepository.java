package com.assigment.orderservice.Repository;


import com.assigment.orderservice.Entity.Ruangan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuanganRepository extends JpaRepository<Ruangan, String> {
    Optional<Ruangan> findByNo(String no);
}
