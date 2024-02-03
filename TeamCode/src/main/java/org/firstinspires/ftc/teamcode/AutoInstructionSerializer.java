package org.firstinspires.ftc.teamcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AutoInstructionSerializer {


    private static String getInput(String prompt) {
        Scanner sc = new Scanner(System.in);    //System.in is a standard input stream
        System.out.print(prompt);
        return sc.nextLine();
    }


    // READ THIS TO RUN THIS CODE:
    // TO RUN, CLICK THE GREEN TRIANGLE NEXT TO THE METHOD DECLARATION LINE,
    // THEN CLICK "RUN AUTOINSTRUCTION WRITER *WITH COVERAGE*.

    // To USE the program, you must enter a file path in the console, which can be accessed through
    // "Cover" at the bottom. You paste the file path ON YOUR COMPUTER and it will paste the code between
    // two lines of text: "BEGIN WRITING" and "END WRITING". You will most likely have to scroll up to see it.

    // Be sure to SET THE RUN CONFIGURATION BACK TO TEAMCODE WHEN UPDATING THE CODE.
    public static void main(String[] args) throws IOException {


        StringBuilder builtInstructions = new StringBuilder("");
        AutoInstructionReader reader = new AutoInstructionReader(getInput("Input path of file:"));

        System.out.println("\n\n\nBEGIN WRITING\n\n\n");

        AutoOperation autoOperation;
        while ((autoOperation = reader.readLine()) != null) {


            final String operationName = autoOperation.getOperationName();
            final ArrayList<String> operationArgs = autoOperation.getOperationArgs();


            boolean skipCurrOperation = false;
            switch (operationName) {
                case "//":
                    skipCurrOperation = true;
//                    builtInstructions.append(operationName + " " + String.join(" ", operationArgs));
                    builtInstructions.append(String.join(" ", operationArgs));
                    break;
                // It intentionally appends STOP anyway so that when the user pastes the code, they will notice
                // the error and do whatever they will to fix it (maybe they forgot to remove it after testing
                // certain parts of autonomous)
                case "/*":
                case "*/":
                case "STOP":
                    skipCurrOperation = true;
                    builtInstructions.append(operationName);
                    break;
            }
            if (skipCurrOperation) {
                builtInstructions.append("\n");
                continue;
            }
            switch (operationName) {
                case "setPosition":
                    builtInstructions.append(operationArgs.get(0) + ".setPosition(" + operationArgs.get(1) + ");");
                    break;
                default:
                    builtInstructions.append(operationName + "(" + String.join(", ", operationArgs) + ");");
                    break;
            }
            builtInstructions.append("\n");


        }
        reader.close();


        System.out.println(builtInstructions.toString());
        System.out.println("\n\n\nEND WRITING\n\n\n");
    }
}