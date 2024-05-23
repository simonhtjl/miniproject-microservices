package com.assigment.orderservice.Controller;

import com.assigment.orderservice.Dto.BorrowBukuRequest;
import com.assigment.orderservice.Dto.OrderRequest;
import com.assigment.orderservice.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/ruangan/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> order(@PathVariable String id, @RequestBody OrderRequest request){
        return orderService.serve(id,request);
    }

    @PostMapping("/ruangan/approve/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> approve(@PathVariable String id) {
        return orderService.approve(id);
    }

    @PostMapping("/buku/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> borrowBuku(@PathVariable String id, @RequestBody BorrowBukuRequest request){
        return orderService.borrow(id,request);
    }

    @PostMapping("buku/returned/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> returnedBuku(@PathVariable String id){
        return orderService.returned(id);
    }

}
