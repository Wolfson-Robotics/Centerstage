package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoJavaBlueBackdrop", group = "Auto")
public class AutoJavaBlueBackdrop extends AutoJava {


    public AutoJavaBlueBackdrop() {
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
                    turnBot(-100);
                    moveBot(80, 1, 0, 0);
                    moveBot(15, 0, 0, 1);
                    moveBot(5, 1, 0, 0);
                    sleep(1000);
                    claw1.setPosition(0);
                    sleep(1000);
                    armServo.setPosition(0.4927);
                    elbowServo.setPosition(0.5483);
                    sleep(1000);
                    moveBot(5, 1, 0, 0);
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
                    turnBot(-90);
                    sleep(1000);
                    moveBot(84, 1, 0, 0);
                    moveBot(15, 0, 0, 1);
                    sleep(500);
                    moveBot(15, 1, 0, 0);
                    sleep(1000);
                    claw1.setPosition(0);
                    sleep(1000);
                    armServo.setPosition(0.4927);
                    elbowServo.setPosition(0.5483);
                    sleep(1000);
                    moveBot(5, 1, 0, 0);
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
                    turnBot(215);
                    sleep(500);
                    moveBot(35, 0, 0, 1);
                    sleep(500);
                    moveBot(90, 1, 0, 0);
                    moveBot(5, 0, 0, 1);
                    moveBot(10, 1, 0, 0);
                    sleep(1000);
                    claw1.setPosition(0);
                    sleep(1000);
                    armServo.setPosition(0.4927);
                    elbowServo.setPosition(0.5483);
                    sleep(1000);
                    moveBot(5, 1, 0, 0);
                    break;

                }
            }
            break;
        }


    }
}
