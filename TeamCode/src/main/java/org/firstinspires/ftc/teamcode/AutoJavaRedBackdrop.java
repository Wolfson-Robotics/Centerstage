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

                // Completely working
                case LEFT: {

                    //moveBot(5 , 0, 0, -1);
                    moveBot(77, 0, 0, 1); // ➜
                    sleep(500);
                    moveBot(93, 1, 0, 0); // ↑
                    sleep(500);
                    turnBot(-92);                              // ↶
                    sleep(500);
                    // correct for robot length since it turns
                    //moveBot(13.62,1,0,0); // ↑
                   // sleep(500);

                    lowerArm();
                    sleep(500);
//                    moveBot(43, 0, 0, 1);
                    moveBot(80, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(10, -1, 0, 0);// ↓
                    sleep(500);
                    moveBot(5, 0, 0, 1);
                    sleep(500);
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
                    sleep(1000);

                    moveBot(51, -1, 0, 0);// ↓

                    turnBot(230);                               // ↷
                    moveBot(65, 1, 0, 0); // ↑
                    moveBot(5, 0, 0, -1);// ←
                    moveBot(5, 1, 0, 0);  // ↑
                    backdropPlace();
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    moveBot(100, 0, 0, 1);
                    moveBot(20, 1, 0, 0);
                    //moveBot(5, 1, 0, 0);
                    break;

                }

                case CENTER: {

                    //moveBot(8, 0, 0, 1);
                    moveBot(47, 1, 0, 0); // ↑
                    lowerArm();
                    moveBot(20, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(5, -1, 0, 0); // ↓
                    sleep(1000);
                    lowerArm();
                    sleep(500);
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
                    sleep(1000);
                    sleep(500);
                    turnBot(100);                              // ↷
                    sleep(1000);
                    moveBot(84, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(12, 0, 0, -1);// ←
                    //moveBot(15, 0, 0, -1);
                    sleep(500);
                    moveBot(10, 1, 0, 0); // ↑
                    backdropPlace();
                    sleep(500);
                    moveBot(5, -1, 0, 0);
                    moveBot(80, 0, 0, 1);
                    moveBot(20, 1, 0, 0);
                    break;

                }

                // Completely working
                case RIGHT: {

                    moveBot(15, 0, 0, 1); // ➜
                    sleep(500);
                    lowerArm();
                    moveBot(68, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(10, -1, 0, 0); // ↓
                    sleep(500);
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
                    sleep(1000);
                    sleep(500);
                    turnBot(100);
                    sleep(500);
                    moveBot(45, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(15, 0, 0, -1); // ←
                    //moveBot(15, 0, 0, 1);
                    //sleep(500);
                    moveBot(37.5, 1, 0, 0); // ↑
                    sleep(500);
                    backdropPlace();
                    sleep(500);
                    moveBot(5, -1, 0, 0);
                    moveBot(70, 0, 0, 1);
                    moveBot(20, 1, 0, 0);
                    break;

                }

            }
            break;


        }



    }

}
