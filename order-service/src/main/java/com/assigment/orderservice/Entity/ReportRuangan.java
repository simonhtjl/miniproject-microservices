package com.assigment.orderservice.Entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class ReportRuangan {
    @Id
    private String id;
    private String ruanganNo;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String studentName;
}
