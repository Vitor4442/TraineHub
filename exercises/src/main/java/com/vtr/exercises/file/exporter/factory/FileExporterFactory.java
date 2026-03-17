package com.vtr.exercises.file.exporter.factory;

import com.vtr.exercises.file.exporter.MediaTypes;
import com.vtr.exercises.file.exporter.contract.FileExporter;
import com.vtr.exercises.file.exporter.impl.CsvExporter;
import com.vtr.exercises.file.exporter.impl.XlsxExporter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileExporterFactory {

    private final ApplicationContext context;

    public FileExporter getExport(String acceptHeader) throws Exception {

        if(acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_XLSX_VALUE)){
           return context.getBean(XlsxExporter.class);
        } else if(acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_CSV_VALUE)){
          return  context.getBean(CsvExporter.class);
        } else {
            throw new BadRequestException("Invalid File Format");
        }
    }
}
