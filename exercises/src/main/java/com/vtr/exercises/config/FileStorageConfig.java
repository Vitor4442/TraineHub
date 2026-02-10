package com.vtr.exercises.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "file")
@Configuration
@AllArgsConstructor
@Data
public class FileStorageConfig {

    private String uploadDir;
}
