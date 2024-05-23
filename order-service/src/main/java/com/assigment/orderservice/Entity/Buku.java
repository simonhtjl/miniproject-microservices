package com.assigment.orderservice.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Buku {
    @Id
    private String id;
    private String judul;
    private String penulis;
    private String tahun;
    private Integer jumlah;
}
