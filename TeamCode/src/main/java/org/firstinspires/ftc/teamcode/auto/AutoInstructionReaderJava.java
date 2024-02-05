package org.firstinspires.ftc.teamcode.auto;

import static org.firstinspires.ftc.teamcode.auto.instruct.AutoInstructionConstants.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

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

            AutoInstructionReader reader = new AutoInstructionReader("/sdcard/autonomous/auto_instruct.txt");

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
                    case "turnBot":
                        finalOperationArgs = operationArgs.stream()
                                .map(Double::parseDouble)
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;

                    case "sleep":
                        finalOperationArgs = operationArgs.stream()
                                .map(Long::parseLong)
                                .collect(Collectors.toCollection(ArrayList::new));
                        break;


                    case "setPosition":
                        Double servoPos = Double.parseDouble(operationArgs.get(1));
                        try {
                            execHardwareMethod(operationArgs.get(0), operationName, servoPos);
                        } catch(Exception e) {
                            throw new IOException(e);
                        }
                        break;

                    default:
                        finalOperationArgs = operationArgs;
                        break;

                }


                try {
                    if (finalOperationArgs.size() > 0) {
                        execAutoMethod(operationName, finalOperationArgs);
                    } else {
                        execAutoMethod(operationName);
                    }
                } catch (Exception e) {
                    throw new IOException(e);
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









    private <T> void execMethod(Class object, String methodName, T... arguments) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ArrayList<?> args = new ArrayList<>(Arrays.asList(arguments));
        if (args.size() <= 0) {
            object.getDeclaredMethod(methodName).invoke(this);
            return;
        }

        object.getDeclaredMethod(methodName, args.stream()
                .map(param -> param.getClass())
                .toArray(Class[]::new)).invoke(this, args);
    }


    private void execMethod(Class object, String methodName) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        execMethod(object, methodName, dummyArr);
    }


    private <T> void execAutoMethod(String methodName, T... args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        execMethod(this.getClass(), methodName, args);
    }

    private void execAutoMethod(String methodName) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        execAutoMethod(methodName, dummyArr);
    }

    private <T> void execHardwareMethod(String deviceName, String methodName, T... args) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        execMethod(this.getClass().getDeclaredField(deviceName).getDeclaringClass(), methodName, args);
    }

    private void execHardwareMethod(String deviceName, String methodName) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        execHardwareMethod(deviceName, methodName, dummyArr);
    }






}
