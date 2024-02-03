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

        // The "x inches input" comment is supposed to represent how many inches were initially plugged
        // into this equation: y=3.340x-2.356 (which is a model which converts inches to in-code units)
        // to get the value. The value will likely be adjusted from its initial value for accuracy.
        while (opModeIsActive()) {

/*
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
            }*/
            /*
            switch (pixelDetection.getPosition()) {

                case LEFT: {
                    //moveBot(49, 0, 0, -1);
                    moveBot(44, 0, 0, -1); //
                    sleep(500);
                    lowerArm();
                    moveBot(68, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(7.5, -1, 0, 0); // ↓
                    sleep(500);
                    tapePlace();
                    //moveBot(25, -1, 0, 0); // ↓
                    sleep(500);
                    turnBot(-100);
                    sleep(500);
                    moveBot(60, 1, 0, 0);
                    moveBot(17.5, 0, 0, 1);
                    moveBot(20, 1, 0, 0);
                    moveBot(5, 1, 0, 0);
                    sleep(1000);
                    backdropPlace();
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    moveBot(65, 0, 0, -1);
                    moveBot(20, 1, 0, 0);
                    break;
                }

                case CENTER: {
//                    moveBot(888, 1, 0, 0);
                    //moveBot(8, 0, 0, 1);
                    moveBot(16, 0, 0, -1);
                    sleep(500);
                    moveBot(27, 1, 0, 0); // ↑
                    sleep(500);
                    lowerArm();
                    sleep(500);
                    moveBot(50, 1, 0, 0);
                    sleep(500);
                    moveBot(9, -1, 0, 0); // ↓
                    sleep(500);
                    tapePlace();
                    sleep(500);
                    turnBot(-97);                              // ↷
                    sleep(1000);
                    moveBot(47, 1, 0, 0);
                    moveBot(20.5, 0, 0, 1);
                    sleep(500);
                    moveBot(44, 1, 0, 0); // ↑
                    //moveBot(15, 0, 0, -1);
                    sleep(500);
                    backdropPlace();
                    sleep(500);
                    moveBot(5, -1, 0, 0);
                    moveBot(80, 0, 0, -1);
                    moveBot(20, 1, 0, 0);
                    break;
                }

                case RIGHT: {
                    //moveBot(5 , 0, 0, -1);
                    moveBot(87, 0, 0, -1); // ➜
                    sleep(1000);
                    moveBot(93, 1, 0, 0); // ↑
                    sleep(1000);
                    turnBot(102);                              // ↶
                    sleep(500);
                    // correct for robot length since it turns
                    //moveBot(13.62,1,0,0); // ↑
                    // sleep(500);

                    lowerArm();
                    sleep(500);
//                    moveBot(43, 0, 0, 1);
                    moveBot(80, 1, 0, 0); // ↑
                    sleep(1000);
                    moveBot(11.65, -1, 0, 0);// ↓
                    //sleep(500);
                    //moveBot(5, 0, 0, -1);
                    sleep(1000);
                    tapePlace();

                    moveBot(51, -1, 0, 0);// ↓

                    turnBot(230);                               // ↷
                    moveBot(5, 0, 0, 1);//
                    moveBot(50, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(3, 0, 0, 1);
                    moveBot(5, 1, 0, 0);
                    //sleep(500);
                    //moveBot(25, 1, 0, 0);
                    //moveBot(7.5, 1, 0, 0);
                    sleep(1000);
                    backdropPlace();
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    moveBot(110, 0, 0, -1);
                    moveBot(20, 1, 0, 0);
                    //moveBot(5, 1, 0, 0);
                    break;
                }

            }*/
            switch (pixelDetection.getPosition()) {

                case LEFT: {
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
                    moveBot(32.714, -1, 0, 0);
                    sleep(1000);
                    tapePlace();
                    sleep(1000);
                    turnBot(-110);
                    sleep(500);
                    // 25 inches input
                    moveBot(83.144, 1, 0, 0);
                    moveBot(8, 0, 0, 1);
                    moveBot(10, 1, 0, 0);
                    sleep(1000);
                    backdropPlace();
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    // 14.5 inches input
                    moveBot(56.074, 0, 0, -1);
                    // 9 inches input
                    moveBot(27.704, 1, 0, 0);
                    break;
                }


                case CENTER: {
                    // 4.5 inches input
                    moveBot(12.674, 0, 0, -1);
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
                    sleep(500);
                    turnBot(-110);
                    sleep(500);
                    // 33 inches input
                    moveBot(40, 1, 0, 0);
                    sleep(500);
                    moveBot(6.95, 0, 0, 1);
                    sleep(500);
                    moveBot(78.864, 1, 0, 0);
                    sleep(1000);
                    backdropPlace();
                    sleep(500);
                    moveBot(5, -1, 0, 0);
                    // 30 inches input
                    moveBot(87.344, 0, 0, -1);
                    // 9 inches input
                    moveBot(31.704, 1, 0, 0);
                    break;
                }


                case RIGHT: {
                    // 27 inches input
                    moveBot(87.824, 0, 0, -1);
                    sleep(1000);
                    // 45 inches input
                    moveBot(114.944, 1, 0, 0);
                    sleep(500);
                    turnBot(120);
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
                    sleep(1000);
                    turnBot(240);
                    sleep(500);
                    // 30 inches input
                    moveBot(108.844, 1, 0, 0);
                    sleep(500);
                    //moveBot 12.45 0 0 1
                    //sleep 500
                    backdropPlace();
                    sleep(1000);
                    moveBot(5, -1, 0, 0);
                    sleep(500);
                    // 42 inches input
                    moveBot(137.924, 0, 0, -1);
                    sleep(500);
                    // 9 inches input
                    moveBot(27.704, 1, 0, 0);
                    break;
                }

            }
            break;
        }


    }
}
