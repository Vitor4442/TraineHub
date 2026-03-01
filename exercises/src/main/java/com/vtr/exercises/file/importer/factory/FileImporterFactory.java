package com.vtr.exercises.file.importer.factory;

import com.vtr.exercises.file.importer.contract.FileImporter;
import com.vtr.exercises.file.importer.impl.CsvImporter;
import com.vtr.exercises.file.importer.impl.XlsxImporter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileImporterFactory {

    private final ApplicationContext context;

    public FileImporter getImporter(String fileName) throws Exception {

        if(fileName.endsWith(".xlsx")){
           return context.getBean(XlsxImporter.class);
        } else if(fileName.endsWith(".csv")){
          return  context.getBean(CsvImporter.class);
        } else {
            throw new BadRequestException("Invalid File Format");
        }
    }
}
