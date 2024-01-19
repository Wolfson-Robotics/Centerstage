package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoJavaBlueBackdropOld", group = "Auto")
public class AutoJavaBlueBackdropOld extends AutoJava {


    public AutoJavaBlueBackdropOld() {
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

                    moveBot(53, 0, 0, -1);
                    moveBot(33, 1, 0, 0);
                    sleep(1000);
                    sleep(1000);
                    armServo.setPosition(0.55);
                    elbowServo.setPosition(0.27);
                    sleep(1000);
                    claw1.setPosition(0.0);
                    sleep(500);
                    armServo.setPosition(0.55);
                    elbowServo.setPosition(0.3075);
                    sleep(1500);
                    claw1.setPosition(0.12);
                    sleep(1000);
                    armServo.setPosition(0.4927);
                    elbowServo.setPosition(0.50);
                    sleep(1000); turnBot(-100);
                    moveBot(80, 1, 0, 0);
                    moveBot(15, 0, 0, 1);
                    moveBot(5, 1, 0, 0);
                    sleep(1000);
                    claw1.setPosition(0);
                    sleep(1000);
                    armServo.setPosition(0.4927);
                    elbowServo.setPosition(0.5483);
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    moveBot(70, 0, 0, -1);
                    moveBot(20, 1, 0, 0);
                    break;

                }


                case CENTER: {

                    moveBot(23, 0, 0, -1);
                    moveBot(20, 1, 0, 0);
                    sleep(1000);
                    armServo.setPosition(0.55);
                    elbowServo.setPosition(0.27);
                    moveBot(67, 1, 0, 0);
                    sleep(1500);
                    moveBot(20, -1, 0, 0);
                    sleep(1000);
                    claw1.setPosition(0.0);
                    sleep(500);
                    armServo.setPosition(0.55);
                    elbowServo.setPosition(0.3075);
                    sleep(1000);
                    claw1.setPosition(0.12);
                    sleep(1000);
                    armServo.setPosition(0.4927);
                    elbowServo.setPosition(0.50);
                    sleep(1000);
                    sleep(1000);
                    turnBot(-90);
                    sleep(1000);
                    moveBot(84, 1, 0, 0);
                    moveBot(15, 0, 0, 1);
                    sleep(500);
                    moveBot(15, 1, 0, 0);
                    sleep(1000);
                    moveBot(10, 0, 0, -1);
                    sleep(1000);
                    claw1.setPosition(0);
                    sleep(1000);
                    armServo.setPosition(0.4927);
                    elbowServo.setPosition(0.5483);
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    moveBot(80, 0, 0, -1);
                    moveBot(20, 1, 0, 0);
                    break;

                }

                case RIGHT: {

                    moveBot(15, 0, 0, -1);
                    moveBot(83, 1, 0, 0);
                    sleep(1000);
                    turnBot(105);
                    sleep(500);
                    moveBot(2, -1, 0, 0);
                    sleep(500);
                    sleep(1000);
                    armServo.setPosition(0.55);
                    elbowServo.setPosition(0.27);
                    sleep(1000);
                    moveBot(10, 0, 0, -1);
                    sleep(1000);
                    moveBot(4, 0, 0, 1);
                    sleep(1000);
                    claw1.setPosition(0.0);
                    sleep(500);
                    armServo.setPosition(0.55);
                    elbowServo.setPosition(0.3075);
                    sleep(1500);
                    claw1.setPosition(0.12);
                    sleep(1000);
                    armServo.setPosition(0.4927);
                    elbowServo.setPosition(0.50);
                    sleep(1000);;
                    sleep(1000);
                    moveBot(6, 0, 0, 1);
                    sleep(1000);
                    //moveBot(1, -1, 0, 0);
                    sleep(500);
                    turnBot(205);
                    sleep(500);
                    moveBot(35, 0, 0, 1);
                    sleep(500);
                    moveBot(90, 1, 0, 0);
                    moveBot(30, 0, 0, -1);
                    moveBot(10, 1, 0, 0);
                    sleep(1000);
                    sleep(1000);
                    claw1.setPosition(0);
                    sleep(1000);
                    armServo.setPosition(0.4927);
                    elbowServo.setPosition(0.5483);
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    moveBot(100, 0, 0, -1);
                    moveBot(20, 1, 0, 0);
                    break;

                }
            }
            break;
        }


    }
}
