package com.vtr.exercises.file.importer.implStudent;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.file.importer.contract.FileImporter;
import com.vtr.exercises.model.Gender;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        List<StudentDTO> students = new ArrayList<>();

        for (CSVRecord record : records) {
            StudentDTO student = new StudentDTO();

            student.setName(getValue(record, "name"));
            student.setEmail(getValue(record, "email"));
            student.setPhone(getValue(record, "phone"));

            String birthDateStr = getValue(record, "birthDate");
            if (birthDateStr != null && !birthDateStr.isBlank()) {
                student.setBirthDate(parseDate(birthDateStr));
            }

            String heightStr = getValue(record, "heightCm");
            if (heightStr != null && !heightStr.isBlank()) {
                student.setHeightCm(parseBigDecimal(heightStr));
            }

            String genderStr = getValue(record, "gender");
            if (genderStr != null && !genderStr.isBlank()) {
                student.setGender(genderStr);
            }

            String weightStr = getValue(record, "weightKg");
            if (weightStr != null && !weightStr.isBlank()) {
                student.setWeightKg(parseBigDecimal(weightStr));
            }

            student.setMedicalNotes(getValue(record, "medicalNotes"));
            student.setGoal(getValue(record, "goal"));

            students.add(student);
        }

        return students;
    }

    private String getValue(CSVRecord record, String column) {
        return record.isMapped(column) ? record.get(column) : null;
    }

    private BigDecimal parseBigDecimal(String value) {
        return new BigDecimal(value.replace(",", ".").trim());
    }

    private LocalDate parseDate(String value) {
        String date = value.trim();

        List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ISO_LOCAL_DATE
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (Exception ignored) {
            }
        }

        throw new IllegalArgumentException("Data inválida: " + value);
    }

   }