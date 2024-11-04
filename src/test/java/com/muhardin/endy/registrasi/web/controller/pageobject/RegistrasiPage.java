package com.muhardin.endy.registrasi.web.controller.pageobject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.StringUtils;

import java.time.Duration;

public class RegistrasiPage {

    public static final String TITLE_PAGE_REGISTRASI = "Registrasi Peserta";
    public static final String TITLE_PAGE_REGISTRASI_SUKSES = "Registrasi Berhasil";

    private WebDriver webDriver;

    // input elements
    @FindBy(id = "fullname")
    private WebElement txtFullname;
    @FindBy(id = "email")
    private WebElement txtEmail;
    @FindBy(name = "nomorHandphone")
    private WebElement txtNomorHandphone;
    @FindBy(xpath = "/html/body/main/form/button")
    private WebElement btnSubmit;

    public RegistrasiPage(WebDriver wd, String url) {
        wd.get(url);
        new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleIs(TITLE_PAGE_REGISTRASI));

        PageFactory.initElements(wd, this);
        this.webDriver = wd;
    }

    public void isiNama(String nama) {
        txtFullname.sendKeys(nama);
    }

    public void isiEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void isiNomorHandphone(String no) {
        txtNomorHandphone.sendKeys(no);
    }

    public String register() {
        btnSubmit.click();
        return webDriver.getTitle();
    }

    public void cekSuksesRegistrasi() {
        Assertions.assertTrue(webDriver.getPageSource().contains("Registrasi Sukses"));
    }

    public void cekErrorFullname(String errorMessage) {
        if (StringUtils.hasText(errorMessage)) {
            checkErrorField("validationFullname", errorMessage);
        }
    }

    public void cekErrorEmail(String errorMessage) {
        if (StringUtils.hasText(errorMessage)) {
            checkErrorField("validationEmail", errorMessage);
        }
    }

    public void cekErrorHandphone(String errorMessage) {
        if (StringUtils.hasText(errorMessage)) {
            checkErrorField("validationNomorHandphone", errorMessage);
        }
    }

    private void checkErrorField(String elementId, String error) {
        WebElement errorField = webDriver.findElement(By.id(elementId));
        Assertions.assertNotNull(errorField);
        Assertions.assertTrue(errorField.getText().contains(error));
    }
}