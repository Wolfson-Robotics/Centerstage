package org.firstinspires.ftc.teamcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;

public class AutoInstructionReader {

    // .txt filereader pipeline
    private LineNumberReader instructionsReader;

    private final String[] servoNames = new String[] { "armServo", "elbowServo", "claw1" };
    private final String[] nicknames = new String[] {"bob", "joe", "marissa"};

    public AutoInstructionReader(File instructionsFile) throws FileNotFoundException {
        this.instructionsReader = new LineNumberReader(new FileReader(instructionsFile));
    }

    public AutoInstructionReader(String filePath) throws FileNotFoundException {
        this(new File(filePath));
    }


    // This function, hence the name, reads the next line in the file, then parses and validates it so that
    // it's ready for AutoJava to use accordingly
    public AutoOperation readLine() throws IOException {


        String rawLine = instructionsReader.readLine();
        if (rawLine == null) {
            return null;
        }


        // Get the filtered, actual data
        String actualData = rawLine.replaceAll("\\r|\\n", "").trim();
        // Separate the operation and the arguments
        ArrayList<String> rawOperationArgs = new ArrayList<>(Arrays.asList(actualData.split(" ")));

        String originalOperationName = rawOperationArgs.get(0);
        String operationName = originalOperationName;
        // convenience operation finders (i.e. you dont have to put a space in front of a comment indicator)
        if (operationName.startsWith("//")) {
            operationName = "//";
        }
        // After getting the operation name, remove it from the array for it to stray true to its variable name
        rawOperationArgs.remove(0);


        ArrayList<String> operationArgs = new ArrayList<>();
        switch (operationName) {

            case "moveBot": {

                if (rawOperationArgs.size() != 4) {
                    throw new IOException("Incorrect number of parameters at moveBot call at line " + this.getLineNumber());
                }
                for (String parameter : rawOperationArgs) {
                    if (!isDouble(parameter)) {
                        throw new IOException("Parameter " + parameter + " for moveBot call is an invalid double at line " + this.getLineNumber());
                    }
                    operationArgs.add(parameter);
                }
                break;

            }
            case "turnBot": {

                if (rawOperationArgs.size() != 1) {
                    throw new IOException("Incorrect number of parameters at turnBot call at line " + this.getLineNumber());
                }
                if (!isDouble(rawOperationArgs.get(0))) {
                    throw new IOException("Parameter " + rawOperationArgs.get(0) + " for turnBot call is an invalid double at line " + this.getLineNumber());
                }
                operationArgs.add(rawOperationArgs.get(0));
                break;

            }
            case "setPosition": {

                if (rawOperationArgs.size() != 2) {
                    throw new IOException("Incorrect number of parameters at setPosition call at line " + this.getLineNumber());
                }
                if (!servoExists(rawOperationArgs.get(0))) {
                    throw new IOException("Servo " + rawOperationArgs.get(0) + " for setPosition call does not exist at line " + this.getLineNumber());
                }
                if (!isDouble(rawOperationArgs.get(1))) {
                    throw new IOException("Servo pos " + rawOperationArgs.get(1) + " is not a valid double for setPosition call at line " + this.getLineNumber());
                }
                operationArgs.add(rawOperationArgs.get(0));
                operationArgs.add(rawOperationArgs.get(1));
                break;

            }
            case "sleep": {

                if (rawOperationArgs.size() != 1) {
                    throw new IOException("Incorrect number of parameters at sleep call at line " + this.getLineNumber());
                }
                if (!isInteger(rawOperationArgs.get(0))) {
                    throw new IOException("Parameter " + rawOperationArgs.get(0) + " is not a valid integer for sleep call at line " + this.getLineNumber());
                }
                operationArgs.add(rawOperationArgs.get(0));
                break;

            }

            // We still want the whole text on the line of the comment
            // So we first append the "operation name" (in this case the comment indicator that
            // may contain snippets of code without a space separating it) to the operationArgs, which is
            // used in the auto instruction writer
            case "//":
                rawOperationArgs.add(0, originalOperationName);
                operationArgs = rawOperationArgs;
                break;
            case "/*":
            case "*/":
            case "STOP":
            case "lowerArm":
            case "tapePlace":
            case "backdropPlace":
                break;
            default:
                throw new IOException("Unknown operation " + operationName);

        }

        return new AutoOperation(operationName, operationArgs);


    }


    private boolean isInteger(String integer) {
        try {
            Integer.parseInt(integer);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isDouble(String dbl) {
        try {
            Double.parseDouble(dbl);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


    private boolean servoExists(String servoName) {
        return Arrays.asList(this.servoNames).contains(servoName);
    }


    public int getLineNumber() {
        return this.instructionsReader.getLineNumber();
    }


    public void close() throws IOException {
        this.instructionsReader.close();
    }



}

class AutoOperation {
    private final String operationName;
    private final ArrayList<String> operationArgs;

    public AutoOperation(String operationName, ArrayList<String> operationArgs) {
        this.operationName = operationName;
        this.operationArgs = operationArgs;
    }

    public String getOperationName() {
        return this.operationName;
    }

    public ArrayList<String> getOperationArgs() {
        return this.operationArgs;
    }
}
