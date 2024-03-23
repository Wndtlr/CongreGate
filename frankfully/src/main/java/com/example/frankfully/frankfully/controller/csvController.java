package com.example.frankfully.frankfully.controller;
import org.springframework.stereotype.Controller;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.frankfully.frankfully.util.readcsv;
import com.example.frankfully.frankfully.util.storecsv;

@Controller
public class csvController {
    @GetMapping("/csvdata")
    public String getCSVData(Model model) {
        readcsv csvReader = new readcsv();
        List<String[]> csvData = csvReader.readCSV("path/to/your/csv/file.csv");
        storecsv dataStorage = new storecsv(csvData);
        model.addAttribute("csvData", dataStorage.getData());
        return "csvdata";
    }
}