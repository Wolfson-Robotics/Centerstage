package org.firstinspires.ftc.teamcode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.io.File;

public class CustomTelemetryLogger {

    private FileWriter fileWriter;

    public CustomTelemetryLogger(String filePath) throws IOException {
        File logFile = new File(filePath);
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            this.fileWriter = new FileWriter(logFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void logData(String data) {
        try {
            this.fileWriter.write(data);
            this.fileWriter.flush(); // Flush the writer to ensure data is written immediately
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}