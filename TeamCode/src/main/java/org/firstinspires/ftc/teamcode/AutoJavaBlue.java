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

        // The "x inches input" comment is supposed to represent how many inches were initially plugged
        // into this equation: y=3.340x-2.356 (which is a model which converts inches to in-code units)
        // to get the value. The value will likely be adjusted from its initial value for accuracy.
        while (opModeIsActive()) {

            switch (pixelDetection.getPosition()) {

                case LEFT:
                    // 27 inches input
                    moveBot(87.824, 0, 0, 1);
                    sleep(1000);
                    // 45 inches input
                    moveBot(114.944, 1, 0, 0);
                    sleep (500);
                    turnBot(-120);
                    sleep(1000);
                    lowerArm();
                    sleep(1000);
                    // 39 inches input
                    // 27 inches input
                    moveBot(87.824, 1, 0, 0);
                    sleep(500);
                    // 8 inches input
                    moveBot(24.364, -1, 0, 0);
                    sleep(1000);
                    tapePlace();
                    break;


                case CENTER:
                    // 4.5 inches input
                    moveBot(12.674, 0, 0, 1);
                    // 53.625 inches input
                    sleep(500);
                    moveBot(20, 1, 0, 0);
                    sleep(1000);
                    lowerArm();
                    sleep(1000);
                    moveBot(90, 1, 0, 0);
                    sleep(1000);
                    moveBot(26, -1, 0, 0);
                    sleep(500);
                    tapePlace();
                    break;


                case RIGHT:
                    // 13.5 inches input
                    moveBot(52.534, 0, 0, -1);
                    sleep(1000);
                    // 29 inches input
                    moveBot(24, 1, 0, 0);
                    sleep(500);
                    lowerArm();
                    sleep(500);
                    moveBot(74.504, 1, 0, 0);
                    sleep(500);
                    // 10.5 inches input
                    moveBot (32.714, -1, 0, 0);
                    sleep (1000);
                    tapePlace();
                    break;





            }
            break;
        }


    }
}
