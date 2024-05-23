package com.assigment.adminservice.Dto.buku;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BukuResponse {
    private String judul;
    private String penulis;
    private String tahun;
    private Integer jumlah;
}
