package com.muhardin.endy.registrasi.web.controller;

import com.github.javafaker.Faker;
import com.muhardin.endy.registrasi.web.RegistrasiWebApplication;
import com.muhardin.endy.registrasi.web.controller.pageobject.RegistrasiPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RegistrasiWebApplication.class, useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE)
class RegistrasiControllerTest {

    private WebDriver webDriver;

    @BeforeEach
    void setUp() {
        webDriver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Test
    void displayFormRegister() throws Exception {
        Faker faker = new Faker(Locale.of("id", "id"));
        RegistrasiPage registrasiPage = new RegistrasiPage(webDriver, "http://localhost:8080/register/form");
        registrasiPage.isiNama(faker.name().fullName());
        registrasiPage.isiEmail(faker.internet().emailAddress());
        registrasiPage.isiNomorHandphone(faker.phoneNumber().cellPhone());
        registrasiPage.submit();
        registrasiPage.checkSuksesRegistrasi();

        assertEquals("http://localhost:8080/register/success", webDriver.getCurrentUrl());
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
        RegistrasiPage registrasiPage = new RegistrasiPage(webDriver, "http://localhost:8080/register/form");
        registrasiPage.isiNama(fullname != null ? fullname : "");
        registrasiPage.isiEmail(email != null ? email : "");
        registrasiPage.isiNomorHandphone(noHp != null ? noHp : "");
        registrasiPage.submit();
        if (sukses) {
            registrasiPage.checkSuksesRegistrasi();
        } else {
            registrasiPage.checkErrorFullname(errorName);
            registrasiPage.checkErrorEmail(errorEmail);
            registrasiPage.checkErrorNomorHandphone(errorNoHp);
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