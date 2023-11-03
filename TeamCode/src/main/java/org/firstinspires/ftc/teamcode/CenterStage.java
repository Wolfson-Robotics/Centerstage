package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Gamepad 1 drive trains
 * Gamepad2 Arm
 * author: WolfsonRobotics
 */
@TeleOp(name = "JavaDriveCenter")
public class CenterStage extends LinearOpMode {
    private DcMotor right_drive1;
    private DcMotor right_drive2;
    private DcMotor left_drive1;
    private DcMotor left_drive2;
    public Servo claw1;
    public Servo armServo;
    private DcMotor leftMotor;
    double powerFactor = 0.8;
    private int speedChangeValue = 1;
    private boolean speedChangedUp = false;
    private boolean speedChangedDown = false;
    int armPosition = 0;
    double targetPosition = .3;

    public void initMotors() {
        right_drive1 = hardwareMap.get(DcMotor.class, "right_drive1");  //"rf_drive");
        right_drive2 = hardwareMap.get(DcMotor.class, "right_drive2");  //"rb_drive");
        left_drive1 = hardwareMap.get(DcMotor.class, "left_drive1");   //lf_drive");
        left_drive2 = hardwareMap.get(DcMotor.class, "left_drive2");   //lb_drive");

        right_drive1.setDirection(DcMotorSimple.Direction.REVERSE);
        right_drive2.setDirection(DcMotorSimple.Direction.REVERSE);
        claw1 = hardwareMap.get(Servo.class, "claw");
        armServo = hardwareMap.get(Servo.class, "claw_arm");
        leftMotor = hardwareMap.get(DcMotor.class, "arm");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        claw1.setPosition(0.0);
        armServo.setPosition(0.3);
    }


    @Override
    public void runOpMode() {
        initMotors();

        telemetry.addLine("Waiting for start");
        telemetry.update();

        leftMotor.setPower(0);
        //0.4
        //0.39
        //0.38
        //0.37
        //0.36
        //0.35
        /*
         * Wait for the user to press start on the Driver Station
         */

        waitForStart();
        while (opModeIsActive()) {
            telemetry.update();
            callSpeedChange();
            double powerLevel1 = gamepad2.left_stick_y; // Arm1
//            double powerLevel2 = gamepad2.right_stick_y; // Arm 2
            leftMotor.setPower(Range.clip(powerLevel1, -1.0, 1.0));
//            armServo.setPosition(powerLevel2);

//            double powerLevel1 = gamepad2.left_stick_y; // Arm1
//            double initialPower = .1;
//
//            while (initialPower < powerLevel1) {
//                // Set the motor power
//                leftMotor.setPower(initialPower);
//
//                // Increment the power for the next loop iteration
//                initialPower += 0.01; // Adjust as needed for your desired speed
//
//                // Sleep for a short duration (adjust as needed)
//                sleep(100); // Sleep for 100 milliseconds (adjust for desired speed)
//                powerLevel1 = gamepad2.left_stick_y;
//            }

//            leftMotor.setTargetPosition(100);
//            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set the motor power to move the arm to the target position
//            leftMotor.setPower(.5);

            if (gamepad2.right_stick_y > .2) targetPosition = .5;
            if (gamepad2.right_stick_y > .5) targetPosition =.7;
            if (gamepad2.right_stick_y < -.1) targetPosition =.3;
            if (gamepad2.right_stick_y < -.5) targetPosition = 0;

            while (Math.abs(armServo.getPosition() - targetPosition) > 0.01) {
                // Move the servo towards the target position slowly
                if (armServo.getPosition() < targetPosition) {
                    armServo.setPosition(armServo.getPosition() + .02);
                } else {
                    armServo.setPosition(armServo.getPosition() - .02);
                }

                // Sleep for a short duration (adjust as needed)
                sleep(100); // Sleep for 100 milliseconds (adjust for desired speed)
            }


            if (gamepad2.left_trigger > 0) claw1.setPosition(0.08); //grab claw
            if (gamepad2.right_trigger > 0) claw1.setPosition(0.00); //drop

            moveBot(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
        }
    }
    void callSpeedChange()
    {

        if (this.gamepad2.right_bumper && !speedChangedUp)
        {
            speedChangedUp = true;
            speedChange(true);
        }
        else if (!(this.gamepad2.right_bumper) && speedChangedUp)
        {
            speedChangedUp = false;
        }
        if (this.gamepad2.left_bumper && !speedChangedDown)
        {
            speedChangedDown = true;
            speedChange(false);
        }
        else if (!this.gamepad2.left_bumper && speedChangedDown)
        {
            speedChangedDown = false;
        }

    }

    void speedChange(boolean faster)
    {

        if (faster && speedChangeValue < 5)
        {
            speedChangeValue++;
        }
        if (!faster && speedChangeValue > 0)
        {
            speedChangeValue--;
        }
        //0.4
        //0.39
        //0.38
        //0.37
        //0.36
        //0.35
        switch (speedChangeValue)
        {
            case 0:
                targetPosition = 0.4;
                break;
            case 1:
                targetPosition = 0.392;
                break;
            case 2:
                targetPosition = 0.384;
                break;
            case 3:
                targetPosition = 0.376;
                break;
            case 4:
                targetPosition = 0.368;
                break;
            case 5:
                targetPosition = 0.36;
                break;
        }

        telemetry.addData("speed change", powerFactor);
        telemetry.update();


    }
    private void moveBot(float vertical, float pivot, float horizontal) {

        right_drive1.setPower(powerFactor * (-pivot + (vertical - horizontal)));
        right_drive2.setPower(powerFactor * (-pivot + vertical + horizontal));
        left_drive1.setPower(powerFactor * (pivot + vertical + horizontal));
        left_drive2.setPower(powerFactor * (pivot + (vertical - horizontal)));
        right_drive1.setPower(0);
        right_drive2.setPower(0);
        left_drive1.setPower(0);
        left_drive2.setPower(0);
    }

}