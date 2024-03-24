package com.example.dockerizespring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;



@Service
public class  DataAnalysisService{

    public void analyzeAndPlotData(List<Object> dataObjects) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\liban\\OneDrive\\Skrivbord\\pupeteer\\plot.py");

        try {
            // Start the process
            Process process = processBuilder.start();

            // Convert data objects list to JSON string
            String jsonData = objectMapper.writeValueAsString(dataObjects);

            // Write JSON data to the stdin of the process
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write(jsonData);
                writer.flush();
            }

            // Wait for the process to finish and check the exit value
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                System.err.println("Error executing the Python script. Exit code: " + exitValue);
                // Handle errors here
            }

            // Optionally, read the process's output (stdout and stderr)
            // ...

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }
}