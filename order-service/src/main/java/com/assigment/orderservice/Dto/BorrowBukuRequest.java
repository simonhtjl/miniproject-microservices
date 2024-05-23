package com.assigment.orderservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBukuRequest {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String studentName;
}
