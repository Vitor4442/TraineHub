package com.vtr.exercises.service;

import com.vtr.exercises.config.VideoExerciseStorageConfg;
import com.vtr.exercises.exception.FileNotFoundException;
import com.vtr.exercises.exception.FileStorageException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service

public class VideoStorageService {

    private final Path fileStorageLocation;

    public VideoStorageService(VideoExerciseStorageConfg videoExerciseStorageConfg) {

        Path path = Paths.get(videoExerciseStorageConfg.getVideoDir()).toAbsolutePath().normalize();

        this.fileStorageLocation = path;

        try{
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e){
            throw new FileStorageException("Could not create the directory where files ill be stored!", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("video/")) {
            throw new FileStorageException("Apenas arquivos de vídeo são permitidos! Tipo enviado: " + contentType);
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains an invalid path sequence " + fileName);
            }

            if (!isSupportedVideoExtension(fileName)) {
                throw new FileStorageException("Extensão de arquivo não suportada.");
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    private boolean isSupportedVideoExtension(String fileName) {
        String lowerCaseName = fileName.toLowerCase();
        return lowerCaseName.endsWith(".mp4") ||
                lowerCaseName.endsWith(".avi") ||
                lowerCaseName.endsWith(".mkv") ||
                lowerCaseName.endsWith(".mov");
    }

    public Resource loadFileAsResource(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()){
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (Exception e){
            throw new FileNotFoundException("File not found " + fileName, e);
        }
    }
}
