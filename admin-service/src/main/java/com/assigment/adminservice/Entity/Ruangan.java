package com.assigment.adminservice.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Ruangan {
    @Id
    private String id;
    private String no;
    private String Status;
}
