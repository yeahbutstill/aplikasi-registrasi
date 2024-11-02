package com.yeahbutstill.web.registrasi.dao;

import com.github.javafaker.Faker;
import com.yeahbutstill.web.registrasi.TestcontainersConfiguration;
import com.yeahbutstill.web.registrasi.entity.Peserta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SimplePropertyRowMapper;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@Sql(scripts = {"classpath:/sql/clear-sample-data.sql", "classpath:/sql/sample-data-peserta.sql"})
class PesertaDaoTest {
    private static final String SQL_SELECT_PESERTA = """
            select * from peserta where id_peserta = ?
            """;

    @Autowired
    private PesertaDao pesertaDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testInsertPeserta() {
        Peserta peserta = new Peserta();
        peserta.setIdPeserta("9b5a9564-ec3d-493c-b045-019aad61bfdc");
        peserta.setNama("Yehezkiel");
        peserta.setJenisKelamin("Laki-laki");
        peserta.setTanggalLahir(LocalDate.of(1993, 11, 26));
        peserta.setAlamat("Jl. Kebon Jeruk");
        peserta.setNoHp("081234567890");
        peserta.setEmail("yehezkiel@gmail.com");
        peserta.setFoto("foto.jpg");
        pesertaDao.save(peserta);

        assertNotNull(peserta.getIdPeserta());
        assertEquals("Yehezkiel", peserta.getNama());
        assertEquals("Laki-laki", peserta.getJenisKelamin());
        assertEquals("yehezkiel@gmail.com", peserta.getEmail());
        assertEquals("081234567890", peserta.getNoHp());
        assertEquals("foto.jpg", peserta.getFoto());
        assertEquals(LocalDate.of(1993, 11, 26), peserta.getTanggalLahir());
        assertEquals("Jl. Kebon Jeruk", peserta.getAlamat());

        assertNotNull(peserta.getTanggalLahir());
        assertNotNull(peserta.getIdPeserta());
        assertNotNull(peserta.getNama());
        assertNotNull(peserta.getAlamat());
        assertNotNull(peserta.getNoHp());
        assertNotNull(peserta.getEmail());
        assertNotNull(peserta.getFoto());
        assertNotNull(peserta.getJenisKelamin());
    }

    @Test
    void testJavaFacker() {
        Faker faker = new Faker(Locale.of("id", "id"));
        String nama = faker.name().fullName();
        String jenisKelamin = faker.demographic().sex();
        LocalDate tanggalLahir = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String alamat = faker.address().fullAddress();
        String noHp = faker.phoneNumber().phoneNumber();
        String email = faker.internet().emailAddress();
        String foto = faker.avatar().image();

        Peserta peserta = new Peserta();
        peserta.setNama(nama);
        peserta.setJenisKelamin(jenisKelamin);
        peserta.setTanggalLahir(tanggalLahir);
        peserta.setAlamat(alamat);
        peserta.setNoHp(noHp);
        peserta.setEmail(email);
        peserta.setFoto(foto);

        assertNull(peserta.getIdPeserta());

        Peserta savePeserta = pesertaDao.save(peserta);
        assertNotNull(savePeserta);

        Peserta selectPesertaId = jdbcTemplate.queryForObject(SQL_SELECT_PESERTA,
                new SimplePropertyRowMapper<>(Peserta.class),
                savePeserta.getIdPeserta());

        assertNotNull(selectPesertaId);

        assertEquals(savePeserta.getIdPeserta(), selectPesertaId.getIdPeserta());
        assertEquals(savePeserta.getNama(), selectPesertaId.getNama());
        assertEquals(savePeserta.getJenisKelamin(), selectPesertaId.getJenisKelamin());
        assertEquals(savePeserta.getAlamat(), selectPesertaId.getAlamat());
        assertEquals(savePeserta.getNoHp(), selectPesertaId.getNoHp());
        assertEquals(savePeserta.getEmail(), selectPesertaId.getEmail());
        assertEquals(savePeserta.getFoto(), selectPesertaId.getFoto());
        assertEquals(savePeserta.getTanggalLahir(), selectPesertaId.getTanggalLahir());
    }

    @Test
    void testFindAll() {
        pesertaDao.findAll();
        assertNotNull(pesertaDao.findAll());
        assertFalse(pesertaDao.findAll().isEmpty());
        assertTrue(pesertaDao.findAll().stream().findFirst().isPresent());
        assertTrue(pesertaDao.findAll().stream().findAny().isPresent());
        assertTrue(pesertaDao.findAll().stream()
                .anyMatch(peserta -> peserta.getIdPeserta().equals("d0dcd42d-d47c-4b13-8bf5-f96412e6f58f")));
    }

    @Test
    void testFindById() {
        Peserta peserta = pesertaDao.findById("d0dcd42d-d47c-4b13-8bf5-f96412e6f58f").orElse(null);
        assertNotNull(peserta);
        assertEquals("Dimas Maryanto", peserta.getNama());
    }

    @Test
    void testFindByEmail() {
        Optional<Peserta> peserta = pesertaDao.findByEmail("dimas@mail.com");
        assertTrue(peserta.isPresent());
        assertEquals("dimas@mail.com", peserta.get().getEmail());
    }

    @Test
    void testDeletePeserta() {
        Peserta peserta = pesertaDao.findById("d0dcd42d-d47c-4b13-8bf5-f96412e6f58f").orElse(null);
        assert peserta != null;
        pesertaDao.delete(peserta);
        assertNull(pesertaDao.findById("d0dcd42d-d47c-4b13-8bf5-f96412e6f58f").orElse(null));
    }
}