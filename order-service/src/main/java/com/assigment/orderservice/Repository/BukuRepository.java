package com.assigment.orderservice.Repository;


import com.assigment.orderservice.Entity.Buku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BukuRepository extends JpaRepository<Buku, String> {
}
