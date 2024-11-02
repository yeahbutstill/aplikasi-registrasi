package com.yeahbutstill.web.registrasi.dao;

import com.yeahbutstill.web.registrasi.entity.Peserta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PesertaDao extends JpaRepository<Peserta, String> {
    Optional<Peserta> findByEmail(String email);
}
