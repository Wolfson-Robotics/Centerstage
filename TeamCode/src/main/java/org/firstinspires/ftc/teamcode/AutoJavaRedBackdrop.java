package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoJavaRedBackdrop", group = "Auto")
public class AutoJavaRedBackdrop extends AutoJava {


    public AutoJavaRedBackdrop() {
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
        // The "x inches input" comment is supposed to represent how many inches were initially plugged
        // into this equation: y=3.340x-2.356 (which is a model which converts inches to in-code units)
        // to get the value. The value will likely be adjusted from its initial value for accuracy.
        while (opModeIsActive()) {

            // robot must be 8.5 inches away from the left of the robot
            switch (pixelDetection.getPosition()) { 					//


                case LEFT: {

                    // 27 inches input
                    moveBot(87.824, 0, 0, 1);
                    sleep(1000);
                    // 45 inches input
                    moveBot(114.944, 1, 0, 0);
                    sleep(500);
                    turnBot(-120);
                    sleep(1000);
                    lowerArm();
                    sleep(1000);
                    // 39 inches input
                    // 27 inches input
                    moveBot(94.824, 1, 0, 0);
                    sleep(500);
                    // 8 inches input
                    moveBot(14.364, -1, 0, 0);
                    sleep(1000);
                    tapePlace();
                    sleep(1000);
                    turnBot(-230);
                    sleep(500);
                    // 30 inches input
                    moveBot(108.844, 1, 0, 0);
                    sleep(500);
                    //moveBot 16 0 0 -1
                    //moveBot 8 0 0 -1
                    moveBot(5, 0, 0, -1);
                    sleep(500);
                    backdropPlace();
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    // 42 inches input
                    moveBot(129.924, 0, 0, 1);
                    // 9 inches input
                    moveBot(27.704, 1, 0, 0);
                    break;

                }

                case CENTER: {

                    // 53.625 inches input
                    moveBot(20, 1, 0, 0);
                    sleep(500);
                    lowerArm();
                    sleep(500);
                    moveBot(90, 1, 0, 0);
                    sleep(1000);
                    moveBot(30, -1, 0, 0);
                    sleep(500);
                    tapePlace();
                    sleep(500);
                    turnBot(110);
                    sleep(500);
                    // 33 inches input
                    moveBot(40, 1, 0, 0);
                    sleep(500);
                    //moveBot 6.95 0 0 -1
                    moveBot(4.95, 0, 0, 1);
                    sleep(500);
                    moveBot(78.864, 1, 0, 0);
                    sleep(1000);
                    backdropPlace();
                    sleep(500);
                    // 30 inches input
                    moveBot(87.344, 0, 0, 1);
                    // 9 inches input
                    moveBot(31.704, 1, 0, 0);
                    break;

                }

                case RIGHT: {

                    // 13.5 inches input
                    moveBot(24.04, 0, 0, 1);
                    sleep(500);
                    // 29 inches input
                    moveBot(24, 1, 0, 0);
                    sleep(500);
                    lowerArm();
                    sleep(500);
                    moveBot(74.504, 1, 0, 0);
                    sleep(500);
                    // 10.5 inches input
                    moveBot(32.714, -1, 0, 0);
                    sleep(1000);
                    tapePlace();
                    sleep(1000);
                    turnBot(110);
                    sleep(500);
                    // 25 inches input
                    moveBot(83.144, 1, 0, 0);
                    moveBot(5, 0, 0, -1);
                    moveBot(10, 1, 0, 0);
                    sleep(1000);
                    backdropPlace();
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    // 14.5 inches input
                    moveBot(65.074, 0, 0, 1);
                    // 9 inches input
                    moveBot(27.704, 1, 0, 0);
                    break;

                }

            }
            break;


        }



    }

}
