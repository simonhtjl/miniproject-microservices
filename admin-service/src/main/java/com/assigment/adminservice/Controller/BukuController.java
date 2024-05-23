package com.assigment.adminservice.Controller;

import com.assigment.adminservice.Service.BukuService;
import com.assigment.adminservice.Dto.buku.BukuRequest;
import com.assigment.adminservice.Dto.buku.BukuResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BukuController {

    @Autowired
    private BukuService bukuService;

    @GetMapping("/buku/all")
    @ResponseStatus(HttpStatus.OK)
    public List<BukuResponse> getAllBuku(){
        return bukuService.getAllBuku();
    }
    @PostMapping("/buku/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBuku(@RequestBody BukuRequest bukuRequest){
        return bukuService.createBuku(bukuRequest);
    }

    @DeleteMapping("/buku/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteBuku(@PathVariable String id){
        return bukuService.deleteBuku(id);
    }

    @PutMapping("/buku/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateBuku(@PathVariable String id,@RequestBody BukuRequest request){
        return bukuService.updateBuku(id,request);
    }

    @GetMapping("/buku/checkstock/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean checkStock(@PathVariable String id){
        return bukuService.checkstock(id);
    }

}
