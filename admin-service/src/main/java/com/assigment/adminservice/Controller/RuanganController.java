package com.assigment.adminservice.Controller;

import com.assigment.adminservice.Service.RuanganService;
import com.assigment.adminservice.Dto.ruangan.RuanganRequest;
import com.assigment.adminservice.Dto.ruangan.RuanganResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RuanganController {

    @Autowired
    private RuanganService ruanganService;

    @GetMapping("/ruangan/all")
    @ResponseStatus(HttpStatus.OK)
    public List<RuanganResponse> getAllRuangan(){
        return ruanganService.getAllRuangan();
    }

    @PostMapping("/ruangan/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createRuangan(@RequestBody RuanganRequest request){
        return ruanganService.createRuangan(request);

    }

    @DeleteMapping("/ruangan/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteRuangan(@PathVariable String id){
        return ruanganService.deleteRuangan(id);

    }

    @PutMapping("/ruangan/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateRuangan(@PathVariable String id,@RequestBody RuanganRequest request){
        return ruanganService.updateRuangan(id,request);

    }
}
