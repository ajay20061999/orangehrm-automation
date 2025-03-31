package com.orangehrm.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelWriter {

    private static final String FILE_PATH = "test-output/EmployeeData.xlsx";

    public static void writeToExcel(String firstName, String lastName, String username, String employeeId, String password) {
        File file = new File(FILE_PATH);
        Workbook workbook;
        Sheet sheet;

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        try {
            if (file.exists()) {
                FileInputStream fileIn = new FileInputStream(file);
                workbook = new XSSFWorkbook(fileIn);
                sheet = workbook.getSheetAt(0);
                fileIn.close();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Employee Data");
                createHeaderRow(sheet);
            }

            int rowCount = sheet.getPhysicalNumberOfRows();
            Row row = sheet.createRow(rowCount);

            row.createCell(0).setCellValue(firstName);
            row.createCell(1).setCellValue(lastName);
            row.createCell(2).setCellValue(username);
            row.createCell(3).setCellValue(employeeId);
            row.createCell(4).setCellValue(password);
            row.createCell(5).setCellValue(timestamp);

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                workbook.close();
                System.out.println("Data saved to " + FILE_PATH);
            }
        } catch (IOException e) {
            System.err.println("Error writing to Excel file: " + e.getMessage());
        }
    }

    private static void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("First Name");
        headerRow.createCell(1).setCellValue("Last Name");
        headerRow.createCell(2).setCellValue("Username");
        headerRow.createCell(3).setCellValue("Employee ID");
        headerRow.createCell(4).setCellValue("Password");
        headerRow.createCell(5).setCellValue("Timestamp");
    }
}

