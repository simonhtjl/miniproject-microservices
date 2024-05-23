package com.assigment.orderservice.Service;

import com.assigment.orderservice.Dto.BorrowBukuRequest;
import com.assigment.orderservice.Dto.OrderRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<?> serve(String id, OrderRequest request);
    ResponseEntity<?> borrow(String id, BorrowBukuRequest request);
    ResponseEntity<?> returned(String id);
    ResponseEntity<?> approve(String id);
}
