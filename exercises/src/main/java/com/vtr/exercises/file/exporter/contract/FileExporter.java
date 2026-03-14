package com.vtr.exercises.file.exporter.contract;

import com.vtr.exercises.dto.StudentDTO;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.List;

public interface FileExporter {
    Resource exportFile(List<StudentDTO> student) throws Exception;
}
