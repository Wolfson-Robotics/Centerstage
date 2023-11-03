package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.HashMap;
import java.util.Map;
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
    public Servo claw1;
    public Servo elbowServo;
    public Servo armServo;
    double powerFactor = 1.25;
    private ServoSettings servoSettings = new ServoSettings();
    private Map<String, ServoSettings> servoPositions = new HashMap<>();

    public void initMotors() {
        right_drive1 = hardwareMap.get(DcMotor.class, "right_drive1");
        right_drive2 = hardwareMap.get(DcMotor.class, "right_drive2");
        left_drive1 = hardwareMap.get(DcMotor.class, "left_drive1");
        left_drive2 = hardwareMap.get(DcMotor.class, "left_drive2");

        right_drive1.setDirection(DcMotorSimple.Direction.REVERSE);
        right_drive2.setDirection(DcMotorSimple.Direction.REVERSE);
        claw1 = hardwareMap.get(Servo.class, "claw");
        elbowServo = hardwareMap.get(Servo.class, "elbow");
        armServo = hardwareMap.get(Servo.class, "arm");
        claw1.setPosition(0.0);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.7);
            servoPositions.put("X", new ServoSettings().setArmPos(0.55).setElbowPos(0.3275));
        servoPositions.put("A", new ServoSettings().setArmPos(0.55).setElbowPos(0.32));
        servoPositions.put("B", new ServoSettings().setArmPos(0.55).setElbowPos(0.31));
        servoPositions.put("Y", new ServoSettings().setArmPos(0.55).setElbowPos(0.29));
        servoPositions.put("dpad_down", new ServoSettings().setArmPos(0.55).setElbowPos(0.27));
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
        /*
         * Wait for the user to press start on the Driver Station
         */
        waitForStart();
        while (opModeIsActive()) {

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
            }
            if(gamepad2.right_stick_y != 0 || gamepad2.left_stick_y != 0) buttonPressed = false;

            if(!buttonPressed) {
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
            }else
            {
                moveServo(armServo, servoSettings.getArmPos(), 20);
                moveServo(elbowServo, servoSettings.getElbowPos(), 10);
            }
            if (gamepad2.left_trigger > 0) claw1.setPosition(0.08); //grab claw
            if (gamepad2.right_trigger > 0) claw1.setPosition(0.00); //drop

            moveBot(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
            telemetry.addData("arm Drive pos:", armServo.getPosition());
            telemetry.addData("elbow Drive pos:", elbowServo.getPosition());
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






