package com.muhardin.endy.registrasi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrasiDto {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String fullname;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String nomorHandphone;
}
