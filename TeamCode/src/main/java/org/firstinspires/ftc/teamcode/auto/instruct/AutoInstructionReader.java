package org.firstinspires.ftc.teamcode.auto.instruct;

import static org.firstinspires.ftc.teamcode.auto.instruct.AutoInstructionConstants.*;

import org.firstinspires.ftc.teamcode.auto.AutoJava;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


        String actualData = rawLine.replaceAll("\\r|\\n", "").trim();
        ArrayList<String> rawOperationArgs = new ArrayList<>(Arrays.asList(actualData.split(argJoinerRegex)));

        String originalOperationName = rawOperationArgs.get(0);
        String operationName = originalOperationName;
        // convenience operation finders (i.e. now you dont have to put a space in front of a comment indicator)
        if (operationName.startsWith(singleCommentMarker)) {
            operationName = singleCommentMarker;
        }
        // After getting the operation name, remove it from the array for it to stay true to its variable name
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
            case singleCommentMarker:
                rawOperationArgs.add(0, originalOperationName);
                operationArgs = rawOperationArgs;
                break;
            case multiCommentBegin:
            case multiCommentEnd:
            case stopMarker:
                break;
            default:
                if (!
                    (
                        Stream.of(AutoJava.class.getDeclaredMethods())
                            .map(Method::getName)
                            .collect(Collectors.toCollection(ArrayList::new))
                    ).contains(operationName))
                {
                    throw new IOException("Unknown operation " + operationName);
                }
                break;

        }
        operationArgs = new ArrayList<>(operationArgs.stream()
                .map(arg -> arg.trim())
                .collect(Collectors.toCollection(ArrayList::new)));

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