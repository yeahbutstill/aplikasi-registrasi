package com.yeahbutstill.web.registrasi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity @Data
public class Peserta {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @NotEmpty @NotNull @Size(min = 36, max = 255)
    private String idPeserta;

    @NotEmpty @NotNull @Size(min = 3, max = 100)
    private String fullname;

    @NotEmpty @NotNull @Size(min = 4, max = 50)
    private String jenisKelamin;

    @NotNull
    private LocalDate tanggalLahir;

    @NotEmpty @NotNull @Size(min = 10, max = 255)
    private String alamat;

    @NotEmpty @NotNull @Size(min = 10, max = 15)
    private String nomorHandphone;

    @NotEmpty @NotNull @Email @Size(min = 10, max = 255)
    private String email;

    @NotEmpty @NotNull
    private String foto;
}
