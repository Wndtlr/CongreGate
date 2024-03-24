package com.congregate.congregate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


@Controller
public class CSVController {
    
    @GetMapping("/display")
   public String displayTable(Model model) {
        CSVReader reader = null;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "/Users/dankim/Documents/CodeGate2024/Frankfully/congregate/src/main/resources/scripts/dataframe.py");

            // Start the process    
            Process process = processBuilder.start();

            // Capture the output stream of the process
            InputStream inputStream = process.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));

            // Read and print each line of output from the Python script
            String line;
            while ((line = read.readLine()) != null) {
                System.out.println(line);
            }

            // Capture error output stream
            InputStream errorStream = process.getErrorStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

            // Read and print error messages, if any
            while ((line = errorReader.readLine()) != null) {
                System.err.println("Error: " + line);
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                model.addAttribute("message", "Python script executed successfully!");
                System.out.println("ran");
            } else {
                model.addAttribute("message", "Failed to execute Python script.");
                System.out.println("help");
            }

            // reader = new CSVReader(new FileReader("src/main/resources/data/df.csv"));
            reader = new CSVReader(new FileReader("/Users/dankim/Documents/CodeGate2024/Frankfully/congregate/src/main/resources/data/df.csv"));
            List<String[]> rows = reader.readAll();
            model.addAttribute("data", rows);
        } catch (IOException | CsvException | InterruptedException  e) { 
            e.printStackTrace();
        }
        finally {
            // Close the CSVReader in the finally block
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "table";
    }
}
