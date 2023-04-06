package guru.qa.lesson_10.parsing;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFileParsingTest {
    private ClassLoader cl = ReadFileParsingTest.class.getClassLoader();

    @Test
    void zipPdfTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("File_qaguru.zip");
             ZipInputStream zi = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zi.getNextEntry()) != null) {
                if (entry.getName().equals("java_book.pdf")) {
                    PDF pdf = new PDF(zi);
                    assertEquals("Adobe InDesign CS6 (Windows)", pdf.creator);
                }
            }
        }
    }

    @Test

    void zipXlsTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("File_qaguru.zip");
             ZipInputStream xl = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = xl.getNextEntry()) != null) {
                if (entry.getName().equals("sample.xlsx")) {
                    XLS xls = new XLS(xl);
                    Assertions.assertTrue(
                            xls.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue()
                                    .startsWith("mouse"));
                    assertEquals("100.0", xls.excel.getSheetAt(0).getRow(1).getCell(1).toString());
                    assertEquals("camera", xls.excel.getSheetAt(0).getRow(2).getCell(0).toString());
                    assertEquals("250.0", xls.excel.getSheetAt(0).getRow(2).getCell(1).toString());

                }
            }
        }
    }

    @Test
    void zipCsvTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("File_qaguru.zip");
             ZipInputStream zi = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zi.getNextEntry()) != null) {
                if (entry.getName().equals("qaguru.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zi));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[] {"Student", "Lesson"}, content.get(0));
                }
            }
        }
    }
}
