package com.assigment.orderservice.Service.Impl;

import com.assigment.orderservice.Dto.BorrowBukuRequest;
import com.assigment.orderservice.Dto.OrderRequest;
import com.assigment.orderservice.Entity.*;
import com.assigment.orderservice.Enum.Status;
import com.assigment.orderservice.Exceptions.NotFoundException;
import com.assigment.orderservice.Repository.*;
import com.assigment.orderservice.Service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository repository;
    @Autowired
    private final RuanganRepository ruanganRepository;
    @Autowired
    private final ReportRuanganRepository reportRuanganRepository;
    @Autowired
    private final BukuRepository bukuRepository;
    @Autowired
    private final BorrowBukuRepository borrowBukuRepository;
    @Autowired
    private final ReportBukuRepository reportBukuRepository;
    private final WebClient webClient;

    @Override
    public ResponseEntity<?> serve(String id, OrderRequest request) {
        try {
            Ruangan ruangan = ruanganRepository.findById(id).orElseThrow(() -> new NotFoundException("Ruangan not found with id: " + id));

            // Check ruangan status
            if (!ruangan.getStatus().equals(Status.AVAILABLE.getCode())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Ruangan Not Available or On Reserved !");
            }

            // Set request to entity
            OrderRuangan order = new OrderRuangan();
            order.setId(UUID.randomUUID().toString());
            order.setNo(ruangan.getNo());
            order.setDateFrom(request.getDateFrom());
            order.setDateTo(request.getDateTo());
            order.setStudentName(request.getStudentName());
            order.setStatus(Status.RESERVED.getCode());

            // Update ruangan status
            ruangan.setStatus(Status.RESERVED.getCode());


            repository.save(order);
            ruanganRepository.save(ruangan);

            return ResponseEntity.ok("Order successfully placed");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to place order: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> borrow(String id, BorrowBukuRequest request) {
        try {
            Buku buku = bukuRepository.findById(id).orElseThrow(() -> new NotFoundException("Buku not found with id: " + id));

            //call admin-service using web client
            //and also implement regitry client with eureka
            //in this part i registry with name admin-service
            Boolean isStock = webClient.get().uri("http://localhost:8080/admin-service/api/buku/checkstock/"+buku.getId()).retrieve().bodyToMono(Boolean.class).block();

            log.info("isStock : " + isStock);
            if(Boolean.FALSE.equals(isStock)){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to borrow book,run out of stock!");
            }

            BorrowBuku borrow = new BorrowBuku();
            borrow.setId(UUID.randomUUID().toString());
            borrow.setJudul(buku.getJudul());
            borrow.setDateFrom(request.getDateFrom());
            borrow.setDateTo(request.getDateTo());
            borrow.setStudentName(request.getStudentName());
            borrow.setStatus(Status.BORROWED.getCode());

            // Update stock buku
            buku.setJumlah(buku.getJumlah() - 1);


            borrowBukuRepository.save(borrow);
            bukuRepository.save(buku);

            return ResponseEntity.ok("Book borrowed successfully");
        }catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to borrow book: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> returned(String id) {
        try {
            BorrowBuku borrowed = borrowBukuRepository.findById(id).orElseThrow(() -> new NotFoundException("Borrowed buku not found with id: " + id));
            borrowed.setStatus(Status.RETURNED.getCode());

            // Create report
            ReportBuku report = new ReportBuku();
            report.setId(UUID.randomUUID().toString());
            report.setJudul(borrowed.getJudul());
            report.setDateFrom(borrowed.getDateFrom());
            report.setDateTo(borrowed.getDateTo());
            report.setStudentName(borrowed.getStudentName());


            borrowBukuRepository.save(borrowed);
            reportBukuRepository.save(report);

            return ResponseEntity.ok("Book returned successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to return book: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> approve(String id) {
        try {
            OrderRuangan order = repository.findById(id).orElseThrow(() -> new NotFoundException("Order not found with id: " + id));
            order.setStatus(Status.NOT_AVAILABLE.getCode());

            Ruangan ruangan = ruanganRepository.findByNo(order.getNo()).orElseThrow(() -> new NotFoundException("Ruangan not found with no: " + order.getNo()));
            ruangan.setStatus(Status.NOT_AVAILABLE.getCode());

            ReportRuangan report = new ReportRuangan();
            report.setId(UUID.randomUUID().toString());
            report.setRuanganNo(order.getNo());
            report.setDateFrom(order.getDateFrom());
            report.setDateTo(order.getDateTo());
            report.setStudentName(order.getStudentName());

            repository.save(order);
            ruanganRepository.save(ruangan);
            reportRuanganRepository.save(report);

            return ResponseEntity.ok("Order approved successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
