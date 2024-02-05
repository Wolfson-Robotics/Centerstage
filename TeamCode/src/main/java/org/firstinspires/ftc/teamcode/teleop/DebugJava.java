package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CustomTelemetryLogger;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Gamepad 1 drive trains
 * Gamepad2 Arm
 * author: WolfsonRobotics
 */
@TeleOp(name = "debugjava")
public class DebugJava extends LinearOpMode {
    private DcMotor right_drive1;
    private DcMotor right_drive2;
    private DcMotor left_drive1;
    private DcMotor left_drive2;
    private DcMotor lift;
    public Servo plane;
    public Servo claw1;
    public Servo elbowServo;
    public Servo armServo;
    double powerFactor = 1.25;
    private ServoSettings servoSettings = new ServoSettings();
    private Map<String, ServoSettings> servoPositions = new HashMap<>();
    private CustomTelemetryLogger logger;


    private boolean buttonPressed = false;

    private final double intCon = 8.727272;


    public void initMotors() {

        right_drive1 = hardwareMap.get(DcMotor.class, "right_drive1");
        right_drive2 = hardwareMap.get(DcMotor.class, "right_drive2");
        left_drive1 = hardwareMap.get(DcMotor.class, "left_drive1");
        left_drive2 = hardwareMap.get(DcMotor.class, "left_drive2");

        lift = hardwareMap.get(DcMotor.class, "lift");
        plane = hardwareMap.get(Servo.class, "plane");

        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        right_drive1.setDirection(DcMotorSimple.Direction.REVERSE);
        right_drive2.setDirection(DcMotorSimple.Direction.REVERSE);

        right_drive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_drive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_drive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_drive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        claw1 = hardwareMap.get(Servo.class, "claw");
        elbowServo = hardwareMap.get(Servo.class, "elbow");
        armServo = hardwareMap.get(Servo.class, "arm");
        claw1.setPosition(0.0);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.7);
        servoPositions.put("right_bumper", new ServoSettings().setArmPos(0.55).setElbowPos(0.388));
        servoPositions.put("X", new ServoSettings().setArmPos(0.55).setElbowPos(0.3225));
        servoPositions.put("A", new ServoSettings().setArmPos(0.55).setElbowPos(0.31));
        servoPositions.put("B", new ServoSettings().setArmPos(0.55).setElbowPos(0.30));
        servoPositions.put("Y", new ServoSettings().setArmPos(0.55).setElbowPos(0.28));
        servoPositions.put("dpad_down", new ServoSettings().setArmPos(0.55).setElbowPos(0.25));
        servoPositions.put("dpad_right", new ServoSettings().setArmPos(0.005).setElbowPos(0.0622));
        servoPositions.put("dpad_up", new ServoSettings().setArmPos(0.4927).setElbowPos(0.5483));
    }

    @Override
    public void runOpMode() {
        initMotors();

        telemetry.addLine("Waiting for start");
        telemetry.update();
        double currentArmPosition = 0.55; // start position for armServo
        double currentElbowPosition = .7;
        boolean buttonPressed = false;
        //variables for debug
        String moves = "";
        double startposright = right_drive1.getCurrentPosition();
        double startposleft = left_drive1.getCurrentPosition();
        double armPastPos = 0;
        double elbowPastPos = 0;
        int numberlog = 0;
        int clawChanged = 0;
        boolean debug = false;
        boolean depadPressed = false;
        boolean turn = false;
        int allowOtherMovement = 0;
            debug = true;
            claw1.setPosition(0.12);
            telemetry.addLine("debug on");
            telemetry.update();


        /*
         * Wait for the user to press start on the Driver Station
         */
        try {
            waitForStart();
            if (debug) {
                String fileName = "/sdcard/Logs/" + new Date().getHours() + "_" + new Date().getMinutes() +"_" + new Date().getSeconds() +".txt";
                logger = new CustomTelemetryLogger(fileName);
                telemetry.addData("name of file: ", fileName);

            }
            while (opModeIsActive()) {
                if(debug)
                {

                    // Dpad up starts the logging pipeline
                    if(gamepad1.dpad_up) {
                        startposright = right_drive1.getCurrentPosition();
                        startposleft = left_drive1.getCurrentPosition();
                        armPastPos = armServo.getPosition();
                        elbowPastPos = elbowServo.getPosition();
                        telemetry.addLine("log start");
                        telemetry.update();
                    }
                    if((!gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_left)  && depadPressed) depadPressed = false;
                    // Dpad down starts logging the movement
                    if(gamepad1.dpad_down && !depadPressed)
                    {
                        depadPressed = true;
                        numberlog++;
                        double rightDif = (right_drive1.getCurrentPosition() - startposright);
                        double leftDif = (left_drive1.getCurrentPosition() - startposleft);
                        logger.logData("log num: " + numberlog);
                        logger.logData("right movement:" + rightDif);
                        logger.logData("left movement:" + leftDif);
                        logger.logData("elbow pos:" + elbowServo.getPosition());
                        logger.logData("arm pos:" + armServo.getPosition());
                        telemetry.addData("log end", numberlog);
                        telemetry.update();
                        boolean vertical = ((rightDif >= 0));
                        switch (allowOtherMovement)
                        {
                            case 1:
//                                moves += "moveBotExact(" + Math.abs(leftDif) + "," + ((leftDif < 0) ? -1 : 1) +",0,0);\nsleep(500);\n";
                                moves += "moveBot(" + ((Math.abs(leftDif))*this.intCon) + "," + ((leftDif < 0) ? -1 : 1) + ", 0, 0);\nsleep(500);\n";
                            case 2:
//                                moves += "moveBotExact(" + Math.abs(rightDif) + ",0,0," + ((rightDif < 0) ? -1 : 1) + ");\nsleep(500);\n";
                                moves += "moveBot(" + ((Math.abs(rightDif))*this.intCon) + ",0,0," + ((rightDif < 0) ? -1 : 1) + ");\nsleep(500);\n";
                            case 3:
                                moves +=  ("turnBot(" + (ticsToDegrees((int)(Math.round(leftDif))) + ");\nsleep(1000);\n"));
                                break;
                        }
                        allowOtherMovement = 0;

                        if((Math.round(elbowServo.getPosition())) == Math.round(elbowPastPos) || (Math.round(armPastPos) == Math.round(armServo.getPosition())))
                        {
                            moves += "armServo.setPosition(" + armServo.getPosition() + ");\nsleep(500);\n" + "elbowServo.setPosition(" + elbowServo.getPosition() + ");\nsleep(500);\n";
                        }
                        switch (clawChanged)
                        {
                            case 1:
                                moves += "claw1.setPosition(0.12);\nsleep(500);\n";
                                clawChanged = 0;
                                break;
                            case 2:
                                moves += "claw1.setPosition(0.00);\nsleep(500);\n";
                                clawChanged = 0;
                                break;

                        }
                        armPastPos = armServo.getPosition();
                        elbowPastPos = elbowServo.getPosition();
                        startposright = right_drive1.getCurrentPosition();
                        startposleft = left_drive1.getCurrentPosition();
                        turn = false;

                    }
                    // Dpad up actually sends the data to the FileWriter
                    if(gamepad1.dpad_right && !depadPressed)
                    {
                        depadPressed = true;
                        logger.logData(moves);
                        telemetry.addLine("logged moves");
                        telemetry.update();

                    }
                    if (gamepad1.x) {
                        armServo.setPosition(0.55);
                        elbowServo.setPosition(0.3);
                    }
                    if (gamepad1.y) {
                        claw1.setPosition(0.12);
                        armServo.setPosition(0.55);
                        elbowServo.setPosition(0.7);
                    }
                    // Dpad left places the pixel down on the tape
                    if(gamepad1.dpad_left && !depadPressed)
                    {
                        depadPressed = true;
                        sleep(1000);
                        armServo.setPosition(0.55);
                        elbowServo.setPosition(0.27);
                        sleep(1000);
                        claw1.setPosition(0.0);
                        sleep(500);
                        armServo.setPosition(0.55);
                        elbowServo.setPosition(0.35);
                        claw1.setPosition(0.12);
                        sleep(1000);
                        armServo.setPosition(0.4927);
                        elbowServo.setPosition(0.50);
                        sleep(1000);
                        moves += "sleep(500);\n" +
                                "armServo.setPosition(0.55);\n" +
                                "elbowServo.setPosition(0.27);\n" +
                                "sleep(1000);\n" +
                                "claw1.setPosition(0.0);\n" +
                                "sleep(500);\n" +
                                "armServo.setPosition(0.55);\n" +
                                "elbowServo.setPosition(0.35);\n" +
                                "claw1.setPosition(0.12);\n" +
                                "sleep(1000);\n" +
                                "armServo.setPosition(0.4927);\n" +
                                "elbowServo.setPosition(0.50);\n" +
                                "sleep(1000);\n";
                        armPastPos = armServo.getPosition();
                        elbowPastPos = elbowServo.getPosition();
                        clawChanged = 0;
                        telemetry.addLine("drop pickup");
                        telemetry.update();

                    }
                    if(gamepad1.right_stick_x != 0)
                    {
                        turn = true;
                        telemetry.addLine("turn");
                        telemetry.update();
                    }
                }
                if (isButtonPressed("A")) {
                    buttonPressed = true;
                    servoSettings = servoPositions.get("A");
                } else if (isButtonPressed("B")) {
                    buttonPressed = true;
                    servoSettings = servoPositions.get("B");
                } else if (isButtonPressed("X")) {
                    buttonPressed = true;
                    servoSettings = servoPositions.get("X");
                } else if (isButtonPressed("Y")) {
                    buttonPressed = true;
                    servoSettings = servoPositions.get("Y");
                } else if (isButtonPressed("dpad_up")) {
                    buttonPressed = true;
                    servoSettings = servoPositions.get("dpad_up");
                } else if (isButtonPressed("dpad_down")) {
                    buttonPressed = true;
                    servoSettings = servoPositions.get("dpad_down");
                } else if (isButtonPressed("dpad_right")) {
                    buttonPressed = true;
                    servoSettings = servoPositions.get("dpad_right");
                } else if (isButtonPressed("right_bumper")) {
                    buttonPressed = true;
                    servoSettings = servoPositions.get("right_bumper");
                }

                if (gamepad2.right_stick_y != 0 || gamepad2.left_stick_y != 0)
                    buttonPressed = false;
                if (gamepad1.y) {
                    telemetry.addLine("Lift move up");
                    telemetry.update();
                    lift.setPower(-.9);
                    sleep(100);
                    lift.setPower(0);
                }
                if (gamepad1.a) {
                    telemetry.addLine("Lift move down");
                    telemetry.update();
                    lift.setPower(.9);
                    sleep(100);
                    lift.setPower(0);
                }
                if (gamepad1.b) {
                    telemetry.addLine("Shoot plane");
                    telemetry.update();
                    plane.setPosition(0.6);
                    sleep(2000);
                    plane.setPosition(0.0);
                }
                if (!buttonPressed) {
                    if (gamepad2.left_stick_y > 0) {
                        currentArmPosition += 0.01; // increase by a small step
                        if (currentArmPosition > 0.55) currentArmPosition = 0.55;
                    } else if (gamepad2.left_stick_y < 0) {
                        currentArmPosition -= 0.01; // decrease by a small steps
                        if (currentArmPosition < 0) currentArmPosition = 0;
                    }
                    if (gamepad2.right_stick_y < 0) {
                        currentElbowPosition += 0.01; // increase by a small step
                        if (currentElbowPosition > 0.7) currentElbowPosition = 0.7;
                    } else if (gamepad2.right_stick_y > 0) {
                        currentElbowPosition -= 0.01; // decrease by a small steps
                        if (currentElbowPosition < 0) currentElbowPosition = 0;
                    }

                    moveServo(armServo, currentArmPosition, 20);
                    moveServo(elbowServo, currentElbowPosition, 10);
                } else {
                    moveServo(armServo, servoSettings.getArmPos(), 20);
                    moveServo(elbowServo, servoSettings.getElbowPos(), 10);
                }
                if (gamepad2.left_trigger > 0) {claw1.setPosition(0.12);  clawChanged = 1;} //grab claw
                if (gamepad2.right_trigger > 0) {claw1.setPosition(0.00); clawChanged = 2;}//drop
                switch (allowOtherMovement)
                {
                    case 1:
                        moveBot(-gamepad1.left_stick_y, 0,0);
                        break;
                    case 2:
                        moveBot(0, 0, gamepad1.left_stick_x);
                        break;
                    case 3:
                        moveBot(0, (gamepad1.right_stick_x), 0);
                        break;
                }
                if(gamepad1.left_stick_y != 0 && allowOtherMovement == 0)
                {
                    allowOtherMovement = 1;
                    telemetry.addLine("1");
                    telemetry.update();
                }
                if(gamepad1.left_stick_x != 0 && allowOtherMovement == 0)
                {
                    telemetry.addLine("2");
                    telemetry.update();
                    allowOtherMovement = 2;
                }
                if(gamepad1.right_stick_x != 0 && allowOtherMovement == 0)
                {
                    telemetry.addLine("3");
                    telemetry.update();
                    allowOtherMovement = 3;
                }



            }
        }catch (IOException e) {
            telemetry.addData("Error", "IOException: " + e.getMessage());
            telemetry.update();
        } finally {
            if (logger != null) {
                logger.close();
            }
        }
        if (debug && logger != null) {
            logger.close();
            telemetry.addLine("logger close");
            telemetry.update();
        }
    }
    private boolean isButtonPressed(String button) {
        switch (button) {
            case "A":
                return gamepad2.a;
            case "B":
                return gamepad2.b;
            case "X":
                return gamepad2.x;
            case "Y":
                return gamepad2.y;
            case "dpad_up":
                return gamepad2.dpad_up;
            case "dpad_down":
                return gamepad2.dpad_down;
            case "dpad_right":
                return gamepad2.dpad_right;
            case "right_bumper":
                return gamepad2.right_bumper;
            default:
                return false;
        }
    }
    private int ticsToDegrees(int tics)
    {
        int degrees = 0;
        double robotLength = 13.62;
        double distUnit = (robotLength) / (Math.cos(45));
        degrees = Math.round((float)(((((tics /intCon)*90)/distUnit)/1.75)));
        return degrees;
    }
    private void moveServo(Servo servo, double targetPosition, long speed) {
        if (Math.abs(servo.getPosition() - targetPosition) > 0.01) {
            // Move the servo towards the target position slowly
            if (servo.getPosition() < targetPosition) {
                servo.setPosition(servo.getPosition() + .01);
            } else {
                servo.setPosition(servo.getPosition() - .01);
            }

            // Sleep for a short duration (adjust as needed)
            sleep(speed); // Sleep for 100 milliseconds (adjust for desired speed)
        }

    }

    private void moveBot(float vertical, float pivot, float horizontal) {
        pivot *= 0.5;
        right_drive1.setPower(powerFactor * (-pivot + (vertical - horizontal)));
        right_drive2.setPower(powerFactor * (-pivot + vertical + horizontal));
        left_drive1.setPower(powerFactor * (pivot + vertical + horizontal));
        left_drive2.setPower(powerFactor * (pivot + (vertical - horizontal)));

    }
    private class ServoSettings {
        private double elbowPos;
        private double armPos;

        public ServoSettings setElbowPos(double elbowPos) {
            this.elbowPos = elbowPos;
            return this;
        }

        public ServoSettings setArmPos(double armPos) {
            this.armPos = armPos;
            return this;
        }


        public double getElbowPos() {
            return elbowPos;
        }

        public double getArmPos() {
            return armPos;
        }
    }
}






