package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    private final String filePath;

    public ExcelReader(String filePath) {
        this.filePath = filePath;
    }

    public List<Map<String, String>> getDataFromSheet(String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return dataList;
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                return dataList;
            }

            int lastRowNum = sheet.getLastRowNum();
            int lastCellNum = headerRow.getLastCellNum();

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                Map<String, String> rowData = new LinkedHashMap<>();
                boolean isEmptyRow = true;

                for (int j = 0; j < lastCellNum; j++) {
                    String header = getCellValue(headerRow.getCell(j));
                    String value = getCellValue(row.getCell(j));

                    if (!value.isEmpty()) {
                        isEmptyRow = false;
                    }

                    rowData.put(header, value);
                }

                if (!isEmptyRow) {
                    dataList.add(rowData);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Cannot read Excel file: " + filePath, e);
        }

        return dataList;
    }

    public String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                double numericValue = cell.getNumericCellValue();
                if (numericValue == (long) numericValue) {
                    return String.valueOf((long) numericValue);
                }
                return String.valueOf(numericValue);

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                FormulaEvaluator evaluator = cell.getSheet()
                        .getWorkbook()
                        .getCreationHelper()
                        .createFormulaEvaluator();

                CellValue cellValue = evaluator.evaluate(cell);

                switch (cellValue.getCellType()) {
                    case STRING:
                        return cellValue.getStringValue().trim();
                    case NUMERIC:
                        double formulaNumeric = cellValue.getNumberValue();
                        if (formulaNumeric == (long) formulaNumeric) {
                            return String.valueOf((long) formulaNumeric);
                        }
                        return String.valueOf(formulaNumeric);
                    case BOOLEAN:
                        return String.valueOf(cellValue.getBooleanValue());
                    default:
                        return "";
                }

            case BLANK:
            default:
                return "";
        }
    }
}