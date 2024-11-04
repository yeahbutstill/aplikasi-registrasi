package com.muhardin.endy.registrasi.web;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat;
import org.testcontainers.utility.DockerImageName;

import java.io.File;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    public static final String TESTCONTAINER_HOST_URL = "http://host.testcontainers.internal";

    private static final File RECORDING_OUTPUT_FOLDER = new File("./target/selenium-recordings/");

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:16"));
    }

    @SuppressWarnings("resource")
    @Bean
    BrowserWebDriverContainer<?> browserContainer() {
        RECORDING_OUTPUT_FOLDER.mkdirs();
        return new BrowserWebDriverContainer<>()
                .withAccessToHost(true)
                .withCapabilities(new FirefoxOptions())
                .withRecordingMode(
                        VncRecordingMode.RECORD_ALL,
                        RECORDING_OUTPUT_FOLDER,
                        VncRecordingFormat.MP4);
    }
}
