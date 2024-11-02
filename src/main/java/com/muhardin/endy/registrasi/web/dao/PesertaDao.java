package com.muhardin.endy.registrasi.web.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muhardin.endy.registrasi.web.entity.Peserta;

public interface PesertaDao extends JpaRepository<Peserta, String> {
    Optional<Peserta> findByEmail(String email);
}
