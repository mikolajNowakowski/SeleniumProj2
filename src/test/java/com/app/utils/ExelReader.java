package com.app.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class ExelReader {

    public static Object[][] readExel(String filename) throws IOException {
        File file = new File("src/test/resources/" + filename);
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Workbook workbook = null;
        String fileExt = filename.substring(filename.indexOf("."));

        if (fileExt.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExt.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("Wrong file");
        }

        Sheet sheet = workbook.getSheetAt(0);
        var rows = new ArrayList<ArrayList<String>>();

        Iterator<Row> rowIterator = sheet.iterator();
        int i = 0;
        while (rowIterator.hasNext()) {
            rows.add(new ArrayList<String>());
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator
                    = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getCellType() == STRING) {
                    rows.get(i).add(cell.getStringCellValue());
                } else {
                    rows.get(i).add(String.valueOf(cell.getNumericCellValue()));
                }
            }
            i++;
        }
        return convertTo2DArray(rows);
    }

    private static String[][] convertTo2DArray(ArrayList<ArrayList<String>> listOfLists) {
        var list = listOfLists.stream().filter(row -> !row.isEmpty()).toList();
        int rows = list.size();
        int cols = list.get(0).size();
        String[][] result = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            ArrayList<String> list2 = list.get(i);
            for (int j = 0; j < cols; j++) {
                result[i][j] = list2.get(j);
            }
        }
        return result;
    }
}
