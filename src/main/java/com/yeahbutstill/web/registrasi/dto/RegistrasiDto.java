package com.yeahbutstill.web.registrasi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrasiDto {
    @NotEmpty @NotNull @Size(min = 3, max = 100)
    private String fullname;

    @NotEmpty @NotNull @Size(min = 10, max = 15)
    private String nomorHandphone;

    @NotEmpty @NotNull @Email
    @Size(min = 10, max = 255)
    private String email;
}
