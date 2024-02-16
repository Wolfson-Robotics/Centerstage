package org.firstinspires.ftc.teamcode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.io.File;

public class CustomTelemetryLogger {
    private static FileWriter fileWriter;

    public CustomTelemetryLogger(String filePath) throws IOException {
        File file = new File(filePath);
        if (fileWriter == null)
            fileWriter = new FileWriter(filePath, true); // true for append mode
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            // Create a FileWriter to write to the file
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void logData(String data) {
        try {
            // Write data to the file
            fileWriter.write(data);
            fileWriter.flush(); // Flush the writer to ensure data is written immediately
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            // Close the FileWriter when done
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}