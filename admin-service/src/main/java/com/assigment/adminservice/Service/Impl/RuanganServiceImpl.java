package com.assigment.adminservice.Service.Impl;


import com.assigment.adminservice.Entity.OrderRuangan;
import com.assigment.adminservice.Entity.Ruangan;
import com.assigment.adminservice.Enum.Status;
import com.assigment.adminservice.Exceptions.NotFoundException;
import com.assigment.adminservice.Repository.OrderRepository;
import com.assigment.adminservice.Repository.RuanganRepository;
import com.assigment.adminservice.Service.RuanganService;
import com.assigment.adminservice.Dto.ruangan.RuanganRequest;
import com.assigment.adminservice.Dto.ruangan.RuanganResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RuanganServiceImpl implements RuanganService {

    @Autowired
    private RuanganRepository repository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ResponseEntity<?> createRuangan(RuanganRequest request) {
        try {
            Ruangan ruangan = Ruangan.builder()
                    .id(UUID.randomUUID().toString())
                    .Status(request.getStatus())
                    .no(request.getNo())
                    .build();

            repository.save(ruangan);
            return ResponseEntity.ok("Ruangan successfully created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create ruangan: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteRuangan(String id) {
        try{
            Ruangan ruangan = repository.findById(id).orElseThrow(() -> new NotFoundException("Ruangan not found with id: " + id));

            repository.delete(ruangan);
            return ResponseEntity.ok("Ruangan successfully deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete ruangan: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateRuangan(String id, RuanganRequest request) {
        try{
            Ruangan ruangan = repository.findById(id).orElseThrow(() -> new NotFoundException("Ruangan not found with id: " + id));
            ruangan.setNo(request.getNo());
            ruangan.setStatus(request.getStatus());


            repository.save(ruangan);
            return ResponseEntity.ok("Ruangan successfully updated");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update ruangan: " + e.getMessage());
        }
    }

    @Override
    public List<RuanganResponse> getAllRuangan() {

        //because i don't develop scheduler,in this part will update status order if dateTo already less then current time now
        List<OrderRuangan> allOrder = orderRepository.findAll();
        List<Ruangan> updatedRuangan = new ArrayList<>();
        for (OrderRuangan row : allOrder){
            if((!row.getStatus().equals(Status.RESERVED.getCode())) && row.getDateTo().isBefore(LocalDateTime.now())){
                Ruangan ruangan = repository.findByNo(row.getNo()).orElseThrow(() -> new NotFoundException("Ruangan not found with no: " + row.getNo()));
                ruangan.setStatus(Status.AVAILABLE.getCode());
                updatedRuangan.add(ruangan);
            }
        }
        repository.saveAll(updatedRuangan);

        List<Ruangan> allRuangan = repository.findAll();
        return allRuangan.stream().map(ruangan -> RuanganResponse.builder().no(ruangan.getNo()).status(ruangan.getStatus()).build()).collect(Collectors.toList());
    }


}
