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
        while (opModeIsActive()) {

            // robot must be 8.5 inches away from the left of the robot
            switch (pixelDetection.getPosition()) { //

                case LEFT: {

                    //moveBot(5 , 0, 0, -1);
                    moveBot(30, 0, 0, 1);
                    sleep(500);
                    moveBot(83, 1, 0, 0);
                    sleep(1500);
                    turnBot(-95);
                    sleep(500);
                    // correct for robot length since it turns
                    moveBot(13.62,1,0,0);
                    sleep(500);

                    armDrop();

//                    moveBot(43, 0, 0, 1);
                    moveBot(12, 1, 0, 0);
                    drop1();

                    moveBot(20, -1, 0, 0);

                    turnBot(230);
                    moveBot(60, 1, 0, 0);
                    moveBot(15, 0, 0, -1);
                    moveBot(5, 1, 0, 0);
                    drop2();
                    sleep(1000);
                    //moveBot(5, 1, 0, 0);
                    break;

                }

                case CENTER: {

                    //moveBot(8, 0, 0, 1);
                    moveBot(47, 1, 0, 0);

                    armDrop();

                    moveBot(20, 1, 0, 0);
                    sleep(500);
                    moveBot(5, -1, 0, 0);
                    drop1();
                    turnBot(100);
                    sleep(1000);
                    moveBot(84, 1, 0, 0);
                    sleep(500);
                    moveBot(15, 0, 0, -1);
                    //moveBot(15, 0, 0, -1);
                    sleep(500);
                    moveBot(10, 1, 0, 0);
                    drop2();
                    //moveBot(5, 1, 0, 0);
                    break;

                }

                case RIGHT: {

                    moveBot(63, 1, 0, 0);
                    sleep(500);
                    armDrop();
//                    moveBot(20, 1, 0, 0);
                    turnBot(100);
                    sleep(500);

                    moveBot(5,-1,0,0);
                    drop1();

                    moveBot(90, 1, 0, 0);
                    sleep(500);
                    moveBot(15, 0, 0, 1);

                    drop2();

                    break;

                }

            }
            break;


        }



    }

    private void drop2() {
        sleep(1000);
        claw1.setPosition(0);
        sleep(1000);
        armServo.setPosition(0.4927);
        elbowServo.setPosition(0.5483);
    }

    private void drop1() {
        sleep(500);
        elbowServo.setPosition(0.27);
        sleep(1000);
        claw1.setPosition(0.0);
        sleep(500);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.36);
        claw1.setPosition(0.12);
        sleep(1000);
        armServo.setPosition(0.4927);
        elbowServo.setPosition(0.50);
        sleep(1000);
    }

    private void armDrop() {
        sleep(500);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.33);
        sleep(1000);
    }
}
