package com.vtr.exercises.file.exporter.impl;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.file.exporter.contract.FileExporter;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvExporter implements FileExporter {
    @Override
    public Resource exportFile(List<StudentDTO> student) throws Exception {
        return null;
    }
}
