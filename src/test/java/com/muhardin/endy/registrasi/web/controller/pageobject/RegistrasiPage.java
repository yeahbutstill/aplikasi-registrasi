package com.muhardin.endy.registrasi.web.controller.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrasiPage {

    private static final String REGISTRASI_PAGE_TITLE = "Registrasi Peserta";
    private static final String REGISTRASI_SUKSES_PAGE_TITLE = "Registrasi Berhasil";

    private final WebDriver webDriver;

    // input elements
    @FindBy(id = "fullname")
    private WebElement textFullname;

    @FindBy(id = "email")
    private WebElement textEmail;

    @FindBy(name = "nomorHandphone")
    private WebElement textNomorHandphone;

    @FindBy(xpath = "/html/body/main/form/button")
    private WebElement btnSubmit;

    // error message
    @FindBy(id = "validationFullname")
    private WebElement errorMessageFullname;

    @FindBy(id = "validationEmail")
    private WebElement errorMessageEmail;

    @FindBy(id = "validationNomorHandphone")
    private WebElement errorMessageNomorHandphone;

    public RegistrasiPage(WebDriver webDriver, String url) {
        webDriver.get(url);
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleIs(REGISTRASI_PAGE_TITLE));
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void isiNama(String nama) {
        textFullname.sendKeys(nama);
    }

    public void isiEmail(String email) {
        textEmail.sendKeys(email);
    }

    public void isiNomorHandphone(String nomorHandphone) {
        textNomorHandphone.sendKeys(nomorHandphone);
    }

    public void submit() {
        btnSubmit.click();
    }

    public void checkSuksesRegistrasi() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleIs(REGISTRASI_SUKSES_PAGE_TITLE));
        assertTrue(Objects.requireNonNull(webDriver.getPageSource()).contains("Registrasi Berhasil"));
    }

    public void checkErrorFullname(String errorMessage) {
        assertTrue(errorMessageFullname.getText().contains(errorMessage));
    }

    public void checkErrorEmail(String errorMessage) {
        assertEquals(errorMessage, errorMessageEmail.getText());
    }

    public void checkErrorNomorHandphone(String errorMessage) {
        assertEquals(errorMessage, errorMessageNomorHandphone.getText());
    }
}
