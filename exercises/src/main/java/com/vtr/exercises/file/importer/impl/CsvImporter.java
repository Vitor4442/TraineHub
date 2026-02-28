package com.vtr.exercises.file.importer.impl;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.file.importer.contract.FileImporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CsvImporter implements FileImporter {

    @Override
    public List<StudentDTO> importFile(InputStream inputStream) throws Exception {
        CSVFormat format = CSVFormat.Builder.create()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();
    
        Iterable<CSVRecord> records = format.parse(new InputStreamReader(inputStream));
        return parseRecordsToStudentDTOs(records);
    }

    private List<StudentDTO> parseRecordsToStudentDTOs(Iterable<CSVRecord> records) {
        List<StudentDTO> people = new ArrayList<>();
        for(CSVRecord record : records){
            StudentDTO student = new StudentDTO();
            student.setName(record.get("name"));
            student.setEmail(record.get("email"));
            student.setPhone(record.get("phone"));

            String birthDateStr = record.get("bith_date");
            if (birthDateStr != null && !birthDateStr.isBlank()) {
                student.setBirthDate(LocalDate.parse(birthDateStr));
            }

            String heightStr = record.get("height_cm");
            if (heightStr != null && !heightStr.isBlank()) {
                heightStr = heightStr.replace(",", ".");
                student.setHeightCm(new BigDecimal(heightStr));
            }

            student.setGender(record.get("gender"));

            String weightStr = record.get("weight_kg");
            if (weightStr != null && !weightStr.isBlank()) {
                weightStr = weightStr.replace(",", ".");
                student.setWeightKg(new BigDecimal(weightStr));
            }

            student.setGoal(record.get("goal"));
            student.setMedicalNotes(record.get("medical_Notes"));

            people.add(student);
        }
        return people;
    }
}
