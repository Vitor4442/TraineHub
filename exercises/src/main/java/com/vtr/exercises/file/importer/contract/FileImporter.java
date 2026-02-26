package com.vtr.exercises.file.importer.contract;

import com.vtr.exercises.dto.StudentDTO;

import java.io.InputStream;
import java.util.List;

public interface FileImporter {
    List<StudentDTO> importFile(InputStream inputStream) throws Exception;
}
