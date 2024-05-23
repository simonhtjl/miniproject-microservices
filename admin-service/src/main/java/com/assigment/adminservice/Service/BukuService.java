package com.assigment.adminservice.Service;

import com.assigment.adminservice.Dto.buku.BukuRequest;
import com.assigment.adminservice.Dto.buku.BukuResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BukuService {
    ResponseEntity<?> createBuku(BukuRequest request);
    ResponseEntity<?> deleteBuku(String id);
    ResponseEntity<?> updateBuku(String id,BukuRequest request);
    Boolean checkstock(String id);
    List<BukuResponse> getAllBuku();
}
