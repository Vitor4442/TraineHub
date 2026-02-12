package com.vtr.exercises.controller.docs;

import com.vtr.exercises.dto.UploadFileResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name= "File Endpoint")
public interface FileControllerDocs {
    UploadFileResponseDTO uploadFile(MultipartFile file);
    List <UploadFileResponseDTO> uploadMultipleFile(MultipartFile[] file);

}
