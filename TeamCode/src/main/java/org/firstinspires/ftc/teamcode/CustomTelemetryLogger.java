package org.firstinspires.ftc.teamcode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class CustomTelemetryLogger {
    private static FileWriter fileWriter;

    public CustomTelemetryLogger(String filePath) throws IOException {
        if (fileWriter == null)
            fileWriter = new FileWriter(filePath, true); // true for append mode
    }

    public synchronized void logData(String data) {
        try {
            fileWriter.write(new Date() + " " + data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}