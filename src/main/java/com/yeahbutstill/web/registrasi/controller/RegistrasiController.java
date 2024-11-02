package com.yeahbutstill.web.registrasi.controller;

import com.yeahbutstill.web.registrasi.dao.PesertaDao;
import com.yeahbutstill.web.registrasi.entity.Peserta;
import org.springframework.web.bind.annotation.GetMapping;

public class RegistrasiController {
    private final PesertaDao pesertaDao;

    public RegistrasiController(PesertaDao pesertaDao) {
        this.pesertaDao = pesertaDao;
    }

    @GetMapping("/registrasi")
    public void registrasi(Peserta peserta) {
        pesertaDao.save(peserta);
    }
}
