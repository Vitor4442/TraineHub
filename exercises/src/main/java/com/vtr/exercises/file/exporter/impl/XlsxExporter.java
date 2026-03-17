package com.vtr.exercises.file.exporter.impl;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.file.exporter.contract.FileExporter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;
@Component
public class XlsxExporter implements FileExporter {

    @Override
    public Resource exportFile(List<StudentDTO> student) throws Exception {
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Estudantes");

            Row headerRow = sheet.createRow(0);
            String[] headers = { "ID", "NAME", "EMAIL", "PHONE", "GENERO"};
            for (int i = 0; i < headers.length; i++) {

                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            int rowIndex = 1;

            for(StudentDTO studentDTO : student){
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(studentDTO.getId());
                row.createCell(1).setCellValue(studentDTO.getName());
                row.createCell(2).setCellValue(studentDTO.getEmail());
                row.createCell(3).setCellValue(studentDTO.getPhone());
                row.createCell(4).setCellValue(studentDTO.getGender());
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        }

    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}
