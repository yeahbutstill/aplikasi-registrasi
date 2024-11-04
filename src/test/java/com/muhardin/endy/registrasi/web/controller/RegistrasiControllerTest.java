package com.muhardin.endy.registrasi.web.controller;

import com.github.javafaker.Faker;
import com.muhardin.endy.registrasi.web.controller.pageobject.RegistrasiPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Locale;

import static com.muhardin.endy.registrasi.web.TestcontainersConfiguration.TESTCONTAINER_HOST_URL;
import static org.junit.jupiter.api.Assertions.*;

class RegistrasiControllerTest extends BaseControllerTests {

    @Test
    void displayFormRegister() throws Exception {
        Faker faker = new Faker(Locale.of("id", "id"));
        RegistrasiPage registrasiPage = new RegistrasiPage(webDriver,
                TESTCONTAINER_HOST_URL
                        + ":" + webappPort
                        + "/register/form");
        registrasiPage.isiNama(faker.name().fullName());
        registrasiPage.isiEmail(faker.internet().emailAddress());
        registrasiPage.isiNomorHandphone(faker.phoneNumber().cellPhone());
        registrasiPage.register();
        registrasiPage.cekSuksesRegistrasi();

        WebElement pesan = webDriver.findElement(By.tagName("h1"));
        assertEquals("Registrasi Sukses", pesan.getText());
        assertTrue(pesan.getText().contains("Registrasi Sukses"));
        assertNotNull(pesan);
        assertTrue(pesan.isDisplayed());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/registrasi-test.csv", numLinesToSkip = 1)
    void displayFormRegisterValidationError(String fullname, String email, String noHp,
                                            Boolean sukses, String errorName, String errorEmail,
                                            String errorNoHp) throws Exception {
        RegistrasiPage registrasiPage = new RegistrasiPage(webDriver,
                TESTCONTAINER_HOST_URL
                        + ":" + webappPort
                        + "/register/form");
        registrasiPage.isiNama(fullname != null ? fullname : "");
        registrasiPage.isiEmail(email != null ? email : "");
        registrasiPage.isiNomorHandphone(noHp != null ? noHp : "");
        registrasiPage.register();
        if (sukses) {
            registrasiPage.cekSuksesRegistrasi();
        } else {
            registrasiPage.cekErrorFullname(errorName);
            registrasiPage.cekErrorEmail(errorEmail);
            registrasiPage.cekErrorHandphone(errorNoHp);
        }
    }

    @Test
    @Disabled
    void processFormRegister() {
    }

    @Test
    @Disabled
    void registrationSuccessful() {
    }
}