package com.congregate.congregate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Controller
public class CSVController {
    
    @GetMapping("/display")
   public String displayTable(Model model) {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/df.csv"))) {
            List<String[]> rows = reader.readAll();
            model.addAttribute("data", rows);
        } catch (IOException | CsvException e) { 
            e.printStackTrace();
        }

        return "table";
    }
}
