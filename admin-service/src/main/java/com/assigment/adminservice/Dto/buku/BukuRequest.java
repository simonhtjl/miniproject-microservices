package com.assigment.adminservice.Dto.buku;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BukuRequest {
    private String judul;
    private String penulis;
    private String tahun;
    private Integer jumlah;
}
