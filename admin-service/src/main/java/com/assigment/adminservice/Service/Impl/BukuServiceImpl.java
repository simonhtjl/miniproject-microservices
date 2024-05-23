package com.assigment.adminservice.Service.Impl;

import com.assigment.adminservice.Entity.Buku;
import com.assigment.adminservice.Exceptions.NotFoundException;
import com.assigment.adminservice.Repository.BukuRepository;
import com.assigment.adminservice.Service.BukuService;
import com.assigment.adminservice.Dto.buku.BukuRequest;
import com.assigment.adminservice.Dto.buku.BukuResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BukuServiceImpl implements BukuService {

    @Autowired
    private BukuRepository repository;

    @Override
    public ResponseEntity<?> createBuku(BukuRequest request) {
        try {
            Buku buku = Buku.builder()
                    .id(UUID.randomUUID().toString())
                    .judul(request.getJudul())
                    .penulis(request.getPenulis())
                    .tahun(request.getTahun())
                    .jumlah(request.getJumlah())
                    .build();

            repository.save(buku);
            return ResponseEntity.ok("Buku successfully created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create buku: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteBuku(String id) {
        try{
            Buku buku = repository.findById(id).orElseThrow(() -> new NotFoundException("Buku not found with id: " + id));

            repository.delete(buku);
            return ResponseEntity.ok("Buku successfully deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete buku: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateBuku(String id, BukuRequest request) {
        try{
            Buku buku = repository.findById(id).orElseThrow(() -> new NotFoundException("Buku not found with id: " + id));
            buku.setJudul(request.getJudul());
            buku.setPenulis(request.getPenulis());
            buku.setTahun(request.getTahun());
            buku.setJumlah(request.getJumlah());


            repository.save(buku);
            return ResponseEntity.ok("Buku successfully updated");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update buku: " + e.getMessage());
        }
    }

    @Override
    public Boolean checkstock(String id) {
        Buku buku = repository.findById(id).orElseThrow(() -> new NotFoundException("Buku not found with id: " + id));
        if(buku.getJumlah() <= 0){
            return false;
        }
        return true;
    }

    @Override
    public List<BukuResponse> getAllBuku() {
        List<Buku> allBuku = repository.findAll();

        return allBuku.stream()
                .map(buku -> BukuResponse.builder()
                        .judul(buku.getJudul())
                        .penulis(buku.getPenulis())
                        .tahun(buku.getTahun())
                        .jumlah(buku.getJumlah())
                        .build())
                .collect(Collectors.toList());
    }
}
