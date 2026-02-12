package com.vtr.exercises.service;

import com.vtr.exercises.config.FileStorageConfig;
import com.vtr.exercises.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service

public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(FileStorageConfig fileStorageConfig) {

        Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

        this.fileStorageLocation = path;

        try{
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e){
            throw new FileStorageException("Could not create the directory where files ill be stored!", e);
        }
    }

    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename Contain a invalid path Sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }
}
