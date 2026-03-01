package com.vtr.exercises.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "file")
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileStorageConfig {

    private String uploadDir;
}
