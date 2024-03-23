package com.example.frankfully.frankfully.util;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.List;

public class readcsv {
    public List<String[]> readCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
