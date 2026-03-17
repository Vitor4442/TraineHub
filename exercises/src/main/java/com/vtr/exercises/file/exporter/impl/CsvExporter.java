package com.vtr.exercises.file.exporter.impl;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.file.exporter.contract.FileExporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CsvExporter implements FileExporter {
    @Override
    public Resource exportFile(List<StudentDTO> student) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        CSVFormat csvFormat = CSVFormat.Builder.create()
                .setHeader( "ID", "NAME", "EMAIL", "PHONE", "GENERO")
                .setSkipHeaderRecord(false)
                .build();

    try(CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)){
        for (StudentDTO studentDTO: student){
            csvPrinter.printRecord(
                    studentDTO.getId(),
                    studentDTO.getName(),
                    studentDTO.getEmail(),
                    studentDTO.getPhone(),
                    studentDTO.getGender()
            );
        }
        }
        return new ByteArrayResource(outputStream.toByteArray());
    }
}
