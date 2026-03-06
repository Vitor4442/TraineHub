package com.vtr.exercises.file.importer.implStudent;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.file.importer.contract.FileImporter;
import com.vtr.exercises.model.Gender;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class XlsxImporter implements FileImporter {

    @Override
    public List<StudentDTO> importFile(InputStream inputStream) throws Exception {
        try(XSSFWorkbook workbook = new XSSFWorkbook(inputStream)){
            XSSFSheet sheet = workbook.getSheetAt(0); //aqui ele pega por paginas da planilha
            Iterator<Row> rowIterator = sheet.iterator();

            if(rowIterator.hasNext()) rowIterator.next();

            return parseRowsToPersonDtoList(rowIterator);
        }
    }

    private List<StudentDTO> parseRowsToPersonDtoList(Iterator<Row> rowIterator) {
        List<StudentDTO> student = new ArrayList<>();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            if(isRowValid(row)){
                student.add(perseRowToPersonDto(row));
            }
        }
        return student;
    }

    private StudentDTO perseRowToPersonDto(Row row) {
        StudentDTO student = new StudentDTO();
        student.setName(row.getCell(0).getStringCellValue());
        student.setEmail(row.getCell(1).getStringCellValue());
        student.setPhone(row.getCell(2).getStringCellValue());
        Date date = row.getCell(3).getDateCellValue();
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        student.setBirthDate(localDate);
        student.setHeightCm(BigDecimal.valueOf(row.getCell(4).getNumericCellValue()));
        String genderStr = row.getCell(5).getStringCellValue();
        if (genderStr != null && !genderStr.isBlank()) {
            student.setGender(String.valueOf(Gender.valueOf(genderStr.trim().toUpperCase())));
        }
        student.setWeightKg(BigDecimal.valueOf(row.getCell(6).getNumericCellValue()));
        student.setMedicalNotes(row.getCell(7).getCellFormula());
        student.setGoal(row.getCell(8).getCellFormula());


        return student;
    }

    private static boolean isRowValid(Row row) {
        return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK;
    }
}
