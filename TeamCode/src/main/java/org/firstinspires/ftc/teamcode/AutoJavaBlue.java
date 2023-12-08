package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoJavaBlue", group = "Auto")
public class AutoJavaBlue extends AutoJava {


    public AutoJavaBlue() {
        super(true);
    }


    @Override
    public void runOpMode() {

        initMotors();
        initCamera();


        while (!isStarted()) {
            telemetry.addData("White percent of LCR mats:", pixelDetection.getLeftPercent() + " "
                    + pixelDetection.getCenterPercent() + " " + pixelDetection.getRightPercent());
            telemetry.addData("ROTATION1: ", pixelDetection.getPosition());
            telemetry.update();
        }


        telemetry.addLine("Waiting for start");
        telemetry.update();
        waitForStart();

        camera.closeCameraDevice();
        while (opModeIsActive()) {


            switch (pixelDetection.getPosition()) {

                case LEFT: {

                    moveBot(43, 0, 0, -1);
                    moveBot(29, 1, 0, 0);
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
                    // TODO: further auto instructions
                    break;

                }


                case CENTER: {

                    moveBot(23, 0, 0, -1);
                    moveBot(67, 1, 0, 0);
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
                    // TODO: further auto instructions
                    break;

                }

                case RIGHT: {

                    moveBot(15, 0, 0, -1);
                    moveBot(83, 1, 0, 0);
                    sleep(1000);
                    turnBot(95);
                    sleep(500);
                    sleep(500);
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
                    moveBot(2, -1, 0, 0);
                    sleep(500);
                    // TODO: further auto instructions
                    break;

                }
            }


        }


    }
}
