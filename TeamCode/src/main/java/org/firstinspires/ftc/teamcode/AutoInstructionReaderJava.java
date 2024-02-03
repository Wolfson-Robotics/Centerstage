package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Autonomous(name = "AutoInstructionReaderJava", group = "Auto")
public class AutoInstructionReaderJava extends AutoJava {

    public AutoInstructionReaderJava() { super(true); }

    @Override
    public void runOpMode() {

        initMotors();
        telemetry.addLine("Waiting for start");
        telemetry.update();
        waitForStart();


        telemetry.addLine("Parsing instructions...");
        telemetry.update();
        try {

            AutoInstructionReader reader = new AutoInstructionReader("/sdcard/autonomous/auto_instruct.txt");

            AutoOperation autoOperation;
            boolean skipOperations = false;
            while ((autoOperation = reader.readLine()) != null) {


                final String operationName = autoOperation.getOperationName();
                final ArrayList<String> operationArgs = autoOperation.getOperationArgs();

                boolean skipCurrOperation = false;
                switch(operationName) {
                    case "//":
                        skipCurrOperation = true;
                        break;
                    case "/*":
                        skipOperations = true;
                        break;
                    case "*/":
                        skipOperations = false;
                        skipCurrOperation = true;
                        break;
                    case "STOP":
                        return;
                }
                if (skipCurrOperation || skipOperations) {
                    continue;
                }
                switch (operationName) {
                    case "moveBot":
                        ArrayList<Double> moveBotArgs = new ArrayList<Double>(operationArgs.stream().map(Double::parseDouble).collect(Collectors.toCollection(ArrayList::new)));
                        moveBot(moveBotArgs.get(0), moveBotArgs.get(1), moveBotArgs.get(2), moveBotArgs.get(3));
                        break;
                    case "turnBot":
                        turnBot(Double.parseDouble(operationArgs.get(0)));
                        break;
                    case "setPosition":
                        Double servoPos = Double.parseDouble(operationArgs.get(1));
                        switch(operationArgs.get(0)) {
                            case "armServo":
                                armServo.setPosition(servoPos);
                                break;
                            case "elbowServo":
                                elbowServo.setPosition(servoPos);
                                break;
                            case "claw1":
                                claw1.setPosition(servoPos);
                                break;
                        }
                        break;
                    case "lowerArm":
                        lowerArm();
                        break;
                    case "tapePlace":
                        tapePlace();
                        break;
                    case "backdropPlace":
                        backdropPlace();
                        break;
                    case "sleep":
                        sleep(Long.parseLong(operationArgs.get(0)));
                        break;

                }
            }
            reader.close();


        } catch (IOException e) {
            telemetry.addLine("An error occurred:");
            telemetry.addLine(Stream.of(e.getStackTrace())
                            .map(String::valueOf)
                            .collect(Collectors.joining("\n")));
            telemetry.update();
            sleep(60000);
        }





    }


}
