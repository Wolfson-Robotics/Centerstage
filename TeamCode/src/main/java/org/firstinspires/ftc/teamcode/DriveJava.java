package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
/**
 * Gamepad 1 drive trains
 * Gamepad2 Arm
 * author: WolfsonRobotics
 */
@TeleOp(name = "CenterStage1")
public class DriveJava extends LinearOpMode {
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
        double startposright = 0;
        double startposleft = 0;
        boolean buttonPressed = false;
        boolean debug = false;
        boolean canLog = false;
        int numberlog = 0;
        if(gamepad1.dpad_up)
        {
            debug = true;
            telemetry.addLine("debug on");
            telemetry.update();
        }
        /*
         * Wait for the user to press start on the Driver Station
         */
        try {
            waitForStart();
            if (debug) {
                String fileName = "/sdcard/Logs/log" + new Date().toString() + ".txt";
                logger = new CustomTelemetryLogger(fileName);
                telemetry.addData("name of file: ", fileName);
                telemetry.update();
            }
            while (opModeIsActive()) {
                if(debug)
                {
                    if(gamepad1.dpad_up) {
                        canLog = true;
                        startposright = right_drive1.getCurrentPosition();
                        startposleft = left_drive1.getCurrentPosition();
                        telemetry.addLine("log start");
                        telemetry.update();
                    }
                    if(gamepad1.dpad_down && canLog)
                    {
                        canLog = false;
                        numberlog++;
                        logger.logData("log num: " + numberlog);
                        logger.logData("right movement:" + (right_drive1.getCurrentPosition() - startposright));
                        logger.logData("left movement:" + (left_drive1.getCurrentPosition() - startposleft));
                        logger.logData("elbow pos:" + elbowServo.getPosition());
                        logger.logData("arm pos:" + armServo.getPosition());
                        telemetry.addData("log end", numberlog);
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
                if (gamepad2.left_trigger > 0) claw1.setPosition(0.12); //grab claw
                if (gamepad2.right_trigger > 0) claw1.setPosition(0.00); //drop

                moveBot(-gamepad1.left_stick_y, (gamepad1.right_stick_x), gamepad1.left_stick_x);

            }
        }catch (IOException e) {
            telemetry.addData("Error", "IOException: " + e.getMessage());
            telemetry.update();
        } finally {
            if (logger != null) {
                logger.close();
            }
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






