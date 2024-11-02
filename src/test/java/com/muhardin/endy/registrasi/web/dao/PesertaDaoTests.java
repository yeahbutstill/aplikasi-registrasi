package com.muhardin.endy.registrasi.web.dao;

import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.UseMainMethod;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SimplePropertyRowMapper;
import org.springframework.test.context.jdbc.Sql;


import com.github.javafaker.Faker;
import com.muhardin.endy.registrasi.web.TestcontainersConfiguration;
import com.muhardin.endy.registrasi.web.RegistrasiWebApplication;
import com.muhardin.endy.registrasi.web.entity.Peserta;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = RegistrasiWebApplication.class, useMainMethod = UseMainMethod.WHEN_AVAILABLE)
@Sql(
    scripts = {
        "classpath:/sql/clear-sample-data.sql",
        "classpath:/sql/sample-data-peserta.sql"
    }
)
class PesertaDaoTests {

    private static final String SQL_SELECT_PESERTA_BY_ID = """
            select * 
            from peserta 
            where id = ?
            """;

    @Autowired private PesertaDao pesertaDao;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Test
    void testInsertPeserta(){
        Faker faker = new Faker(Locale.of("id", "id"));
        String fullname = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().cellPhone();

        Peserta p = new Peserta();
        p.setEmail(email);
        p.setFullname(fullname);
        p.setNomorHandphone(phone);

        Assertions.assertNull(p.getId());
        
        Peserta px = pesertaDao.save(p);
        
        Assertions.assertNotNull(px.getId());

        Peserta pz = jdbcTemplate.queryForObject(SQL_SELECT_PESERTA_BY_ID, 
                        new SimplePropertyRowMapper<>(Peserta.class), 
                        px.getId());

        Assertions.assertNotNull(pz);
        Assertions.assertEquals(email, pz.getEmail());
        Assertions.assertEquals(fullname, pz.getFullname());
        Assertions.assertEquals(phone, pz.getNomorHandphone());
    }

    @Test
    void testCariPesertaByEmail(){
        Optional<Peserta> peserta001 = pesertaDao.findByEmail("p001@yopmail.com");
        Assertions.assertTrue(peserta001.isPresent());
        Assertions.assertEquals("Peserta 001", peserta001.get().getFullname());
        Assertions.assertEquals("081234567890", peserta001.get().getNomorHandphone());
    }
}
