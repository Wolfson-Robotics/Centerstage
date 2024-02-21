package org.firstinspires.ftc.teamcode.auto;

import static org.firstinspires.ftc.teamcode.auto.instruct.AutoInstructionConstants.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.auto.instruct.AutoInstructionConstants;
import org.firstinspires.ftc.teamcode.auto.instruct.AutoInstructionReader;
import org.firstinspires.ftc.teamcode.auto.instruct.AutoOperation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Autonomous(name = "AutoInstructionReaderJava", group = "Auto")
public class AutoInstructionReaderJava extends AutoJava {

    private final ArrayList<?> dummyArr = new ArrayList<>();

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

            AutoInstructionReader reader = new AutoInstructionReader(AutoInstructionConstants.autoInstructPath);

            AutoOperation autoOperation;
            boolean skipOperations = false;
            while ((autoOperation = reader.readLine()) != null) {


                final String operationName = autoOperation.getOperationName();
                final ArrayList<String> operationArgs = autoOperation.getOperationArgs();

                boolean skipCurrOperation = false;
                switch(operationName) {
                    case singleCommentMarker:
                        skipCurrOperation = true;
                        break;
                    case multiCommentBegin:
                        skipOperations = true;
                        break;
                    case multiCommentEnd:
                        skipOperations = false;
                        skipCurrOperation = true;
                        break;
                    case stopMarker:
                        return;
                }
                if (skipCurrOperation || skipOperations) {
                    continue;
                }



                ArrayList<?> finalOperationArgs = new ArrayList<>();
                switch (operationName) {

                    case "moveBot":
                        ArrayList<Double> moveBotArgs = operationArgs.stream()
                                .map(String::valueOf)
                                .map(Double::parseDouble)
                                .collect(Collectors.toCollection(ArrayList::new));
                        moveBot(moveBotArgs.get(0), moveBotArgs.get(1), moveBotArgs.get(2), moveBotArgs.get(3));
                        break;
                    case "turnBot":
                        turnBot(Double.parseDouble(operationArgs.get(0)));
                        break;
                    case "sleep":
                        sleep(Long.parseLong(operationArgs.get(0)));
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

                    case "setPower":
                        Double powerFac = Double.parseDouble(operationArgs.get(1));
                        switch(operationArgs.get(0)) {
                            case "right_drive1":
                                right_drive1.setPower(powerFac);
                                break;
                            case "right_drive2":
                                right_drive2.setPower(powerFac);
                                break;
                            case "left_drive1":
                                left_drive1.setPower(powerFac);
                                break;
                            case "left_drive2":
                                left_drive2.setPower(powerFac);
                                break;
                        }
                        break;


                    case "restArm":
                        restArm();
                        break;
                    case "trussArm":
                        trussArm();
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

                    default:
                        finalOperationArgs = operationArgs;
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
