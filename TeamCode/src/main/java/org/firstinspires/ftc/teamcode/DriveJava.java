package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Gamepad 1 drive trains
 * Gamepad2 Arm
 * author: WolfsonRobotics
 */
@TeleOp(name = "CenterStage1")
public class CenterStage1 extends LinearOpMode {
    private DcMotor right_drive1;
    private DcMotor right_drive2;
    private DcMotor left_drive1;
    private DcMotor left_drive2;
    private DcMotor lift;
    public Servo claw1;
    public Servo elbowServo;
    public Servo armServo;
    public Servo plane;
    double powerFactor = 0.8;

    public void initMotors() {
        right_drive1 = hardwareMap.get(DcMotor.class, "right_drive1");
        right_drive2 = hardwareMap.get(DcMotor.class, "right_drive2");
        left_drive1 = hardwareMap.get(DcMotor.class, "left_drive1");
        left_drive2 = hardwareMap.get(DcMotor.class, "left_drive2");
        lift = hardwareMap.get(DcMotor.class, "lift");

        right_drive1.setDirection(DcMotorSimple.Direction.REVERSE);
        right_drive2.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.setDirection(DcMotorSimple.Direction.FORWARD);
        claw1 = hardwareMap.get(Servo.class, "claw");
        elbowServo = hardwareMap.get(Servo.class, "elbow");
        armServo = hardwareMap.get(Servo.class, "arm");


        lift.setDirection(DcMotorSimple.Direction.REVERSE);

        claw1.setPosition(0.0);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.7);
    }

    @Override
    public void runOpMode() {
        initMotors();

        telemetry.addLine("Waiting for start");
        telemetry.update();
        double currentArmPosition = 0.55; // start position for armServo
        double currentElbowPosition = .7;
        /*
         * Wait for the user to press start on the Driver Station
         */
        waitForStart();
        while (opModeIsActive()) {

            if (gamepad2.y) {
                telemetry.addLine("Lift move up");
                telemetry.update();
                lift.setPower(-.9);
                sleep(100);
                lift.setPower(0);
            }
            if (gamepad2.a) {
                telemetry.addLine("Lift move down");
                telemetry.update();
                lift.setPower(.9);
                sleep(100);
                lift.setPower(0);
            }
            if (gamepad2.b) {
                telemetry.addLine("Shoot plane");
                telemetry.update();
                plane.setPosition(0.6);
                sleep(2000);
                plane.setPosition(0.0);
            }
            if (gamepad2.x) {
                elbowServo.setPosition(0.7); // Set the elbowServo to 0.7 position.
                currentArmPosition = -1;
            }
//            if (gamepad2.left_stick_y < 0) {
//                currentArmPosition += 0.01; // increase by a small step
//                if (currentArmPosition > 0.55) currentArmPosition = 0.55;
//            } else if (gamepad2.left_stick_y > 0) {
//                currentArmPosition -= 0.01; // decrease by a small steps
//                if (currentArmPosition < 0) currentArmPosition = 0;
//            }
            if (gamepad2.right_stick_y > 0) {
                currentElbowPosition += 0.01; // increase by a small step
                if (currentElbowPosition > 0.7) currentElbowPosition = 0.7;
            } else if (gamepad2.right_stick_y < 0) {
                currentElbowPosition -= 0.01; // decrease by a small steps
                if (currentElbowPosition < 0) currentElbowPosition = 0;
            }

            moveServo(armServo, currentArmPosition, 20);
            moveServo(elbowServo, currentElbowPosition,10);

            if (gamepad2.left_trigger > 0) claw1.setPosition(0.08); //grab claw
            if (gamepad2.right_trigger > 0) claw1.setPosition(0.00); //drop

            moveBot(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
//            telemetry.addLine();
//            telemetry.update();
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

}
