package com.muhardin.endy.registrasi.web.controller;

import com.muhardin.endy.registrasi.web.TestcontainersConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.UseMainMethod;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.lifecycle.TestDescription;

import java.util.Optional;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(useMainMethod = UseMainMethod.WHEN_AVAILABLE,
        webEnvironment = WebEnvironment.DEFINED_PORT)
abstract class BaseControllerTests {

    @Autowired
    BrowserWebDriverContainer<?> browserContainer;
    @LocalServerPort
    Integer webappPort;
    WebDriver webDriver;

    @BeforeEach
    void openBrowser() {
        Testcontainers.exposeHostPorts(webappPort, 10000);
        webDriver = new RemoteWebDriver(browserContainer.getSeleniumAddress(), new FirefoxOptions());
    }

    @AfterEach
    void closeBrowser() {
        browserContainer.afterTest(
                new TestDescription() {
                    @Override
                    public String getTestId() {
                        return getFilesystemFriendlyName();
                    }

                    @Override
                    public String getFilesystemFriendlyName() {
                        return getTestClassName();
                    }
                },
                Optional.empty()
        );

        webDriver.quit();
    }

    private String getTestClassName() {
        return this.getClass().getSimpleName();
    }
}
