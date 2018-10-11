package parser;

import data.Record;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class XlsParser implements Parser {
    private Map<ColumnAnchor, String> anchorsMap = new EnumMap<>(ColumnAnchor.class);
    private Map<ColumnAnchor, Integer> columnsMap = new EnumMap<>(ColumnAnchor.class);

    {
        anchorsMap.put(ColumnAnchor.FIELD, "field");
        anchorsMap.put(ColumnAnchor.TYPE, "type");
        anchorsMap.put(ColumnAnchor.COMMENT, "comment");
    }

    public LinkedHashSet<Record> parse(String filepath) {
        try (FileInputStream fin = new FileInputStream(filepath)) {
            Workbook excelBook = new XSSFWorkbook(fin); // TODO find out how to know about which Workbook use
            Sheet sheetAt = excelBook.getSheetAt(0);
            initColumnsMap(sheetAt.getRow(sheetAt.getTopRow()));

            LinkedHashSet<Record> records = new LinkedHashSet<>();
            sheetAt.forEach(row -> {
                if (sheetAt.getTopRow() != row.getRowNum()) {
                    String field = row.getCell(columnsMap.get(ColumnAnchor.FIELD)).getStringCellValue();
                    String type = row.getCell(columnsMap.get(ColumnAnchor.TYPE)).getStringCellValue();
                    String comment = row.getCell(columnsMap.get(ColumnAnchor.COMMENT)).getStringCellValue();
                    records.add(new Record(field, type, comment));
                }
            });
            excelBook.close();
            return records;
        } catch (IOException e) {
            throw new ParserException(e);
        }
    }

    private void initColumnsMap(Row topRow) {
        for (Cell cell : topRow) {
            String stringCellValue = cell.getStringCellValue();
            for (Map.Entry<ColumnAnchor, String> entry : anchorsMap.entrySet()) {
                if (entry.getValue().equals(stringCellValue.toLowerCase())) {
                    columnsMap.put(entry.getKey(), cell.getColumnIndex());
                }
            }
        }
    }

    private enum ColumnAnchor {
        FIELD,
        TYPE,
        COMMENT;
    }
}
