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

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import com.fazecast.jSerialComm.*;


@Controller
public class CSVController implements Runnable {
    private Thread pythonThread; 

    @PostConstruct
    public void init() {
        // Start the Python script execution thread
        pythonThread = new Thread(this);
        pythonThread.start();
    }

    @PreDestroy
    public void destroy() {
        // Stop the Python script execution thread 
        if (pythonThread != null && pythonThread.isAlive()) {
            pythonThread.interrupt();
            try {
                pythonThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /* 
    @Override
    public void run() {
        try {
            // Execute Python script continuously
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("threading");
                ProcessBuilder processBuilder = new ProcessBuilder("python", "/Users/dankim/Documents/CodeGate2024/Frankfully/congregate/src/main/resources/scripts/dataframe.py");
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                if ((line = reader.readLine()) == null){
                    System.out.println("no lines");
                }
                while ((line = reader.readLine()) != null) {
                    System.out.println("Output from Python: " + line);
                }

                // Wait for Python script to finish
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    System.err.println("Failed to execute Python script.");
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    while ((line = errorReader.readLine()) != null) {
                        // Print error message from Python script
                        System.err.println("Error output from Python: " + line);
                    }
                    if ((line = errorReader.readLine()) == null){
                    }
                }
                else{ 
                    System.out.println("exit 0 ");
                }

                // Add a delay before restarting the script 
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }
*/
    @Override
    public void run() {
    try {
        while (!Thread.currentThread().isInterrupted()) {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "/Users/dankim/Documents/CodeGate2024/Frankfully/congregate/src/main/resources/scripts/dataframe.py");
            processBuilder.redirectErrorStream(true); // Merge error stream with output stream
            Process process = processBuilder.start();

            // Capture output from Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Process output from Python script (if needed)
                System.out.println("Output from Python: " + line);
            }

            // Wait for Python script to finish
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                // Print error message if the Python script exits with a non-zero exit code
                System.err.println("Failed to execute Python script. Exit code: " + exitCode);
            }

            // Add a delay before restarting the script (adjust as needed)
            Thread.sleep(1000);
        }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }
    @GetMapping("/display")
   public String displayTable(Model model) {
        CSVReader reader = null;
        /* 
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
        */
        try{
            // reader = new CSVReader(new FileReader("src/main/resources/data/df.csv"));
            reader = new CSVReader(new FileReader("/Users/dankim/Documents/CodeGate2024/Frankfully/congregate/src/main/resources/data/df.csv"));
            List<String[]> rows = reader.readAll();
            model.addAttribute("data", rows);
        } catch (IOException | CsvException e) { 
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
