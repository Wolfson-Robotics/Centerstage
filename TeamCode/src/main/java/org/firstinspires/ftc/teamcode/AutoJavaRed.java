package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoJavaRed", group = "Auto")
public class AutoJavaRed extends AutoJava {


    public AutoJavaRed() {
        super(false);
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

            // robot must be 8.5 inches away from the left of the robot
            switch (pixelDetection.getPosition()) {

                case LEFT: {

                    moveBot(15, 0, 0, -1); // ←
                    sleep(500);
                    lowerArm();
                    moveBot(68, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(10, -1, 0, 0); // ↓
                    sleep(500);
                    tapePlace();
                    break;

                }

                case CENTER: {

                    moveBot(47, 1, 0, 0); // ↑
                    lowerArm();
                    moveBot(20, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(5, -1, 0, 0); // ↓
                    sleep(500);
                    tapePlace();
                    break;

                }

                case RIGHT: {

                    moveBot(77, 0, 0, -1);// ←
                    sleep(500);
                    moveBot(83, 1, 0, 0); // ↑
                    sleep(500);
                    turnBot(95);                              // ↷
                    sleep(500);
                    lowerArm();
                    moveBot(80, 1, 0, 0); // ↑
                    moveBot(7, -1, 0, 0);// ↓
                    moveBot(5, 0, 0, 1); // ➜
                    tapePlace();
                    break;

                }

            }


        }



    }
}
