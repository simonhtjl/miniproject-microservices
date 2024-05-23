package com.assigment.adminservice.Dto.ruangan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuanganRequest {
    private String no;
    private String status;
}
