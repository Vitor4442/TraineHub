package com.vtr.exercises.file.importer.impl;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.file.importer.contract.FileImporter;

import java.io.InputStream;
import java.util.List;

public class XlsxImporter implements FileImporter {

    @Override
    public List<StudentDTO> importFile(InputStream inputStream) throws Exception {
        return List.of();
    }
}
