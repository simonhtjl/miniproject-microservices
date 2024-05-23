package com.assigment.adminservice.Entity;

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
public class OrderRuangan {
    @Id
    private String id;
    private String no;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String studentName;
    private String status;
}
