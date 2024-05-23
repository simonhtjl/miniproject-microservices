package com.assigment.adminservice.Service;

import com.assigment.adminservice.Dto.ruangan.RuanganRequest;
import com.assigment.adminservice.Dto.ruangan.RuanganResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RuanganService {
    ResponseEntity<?> createRuangan(RuanganRequest request);
    ResponseEntity<?> deleteRuangan(String id);
    ResponseEntity<?> updateRuangan(String id,RuanganRequest request);
    List<RuanganResponse> getAllRuangan();
}
