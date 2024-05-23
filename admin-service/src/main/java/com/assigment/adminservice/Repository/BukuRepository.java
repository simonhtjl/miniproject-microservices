package com.assigment.adminservice.Repository;

import com.assigment.adminservice.Entity.Buku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BukuRepository extends JpaRepository<Buku, String> {
}
