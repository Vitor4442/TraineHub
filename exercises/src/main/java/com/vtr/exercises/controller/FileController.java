package com.vtr.exercises.controller;

import com.vtr.exercises.controller.docs.FileControllerDocs;
import com.vtr.exercises.dto.UploadFileResponseDTO;
import com.vtr.exercises.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/fichas ")
@RequiredArgsConstructor
public class FileController implements FileControllerDocs {

    private final FileStorageService service;

    @Override
    public UploadFileResponseDTO uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public List<UploadFileResponseDTO> uploadMultipleFile(MultipartFile[] file) {
        return List.of();
    }

    @Override
    public ResponseEntity<ResponseEntity> downloadFile(String fileName, HttpServletRequest request) {
        return null;
    }
}
