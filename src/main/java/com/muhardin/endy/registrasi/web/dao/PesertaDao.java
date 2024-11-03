package com.muhardin.endy.registrasi.web.dao;

import com.muhardin.endy.registrasi.web.entity.Peserta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PesertaDao extends JpaRepository<Peserta, String> {
    Optional<Peserta> findByEmail(String email);
}
