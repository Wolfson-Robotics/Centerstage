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

                    moveBot(46, 0, 0, -1);// ←
                    lowerArm();
                    // + 6
                    moveBot(35, 1, 0, 0); // ↑
                    sleep(500);
                    tapePlace();
                    sleep(500);
                    //moveBot(6, -1, 0, 0);
                    turnBot(-100);                             // ↶
                    moveBot(80, 1, 0, 0); // ↑
                    moveBot(6, 0, 0, 1); // ➜
                    moveBot(5, 1, 0, 0);  // ↑

                    backdropPlace();

                    break;

                }


                case CENTER: {

                    moveBot(12, 0, 0, -1);// ←
                    lowerArm();
                    moveBot(87, 1, 0, 0); // ↑
                    sleep(500);
                    moveBot(7.5, -1, 0, 0);
                    sleep(500);
                    tapePlace();

                    turnBot(-90);                              // ↶
                    sleep(1000);
                    moveBot(84, 1, 0, 0); // ↑
                    //sleep(500);
                    moveBot(6.5, 0, 0, 1); // ➜
                    sleep(500);
                    moveBot(15, 1, 0, 0); // ↑

                    backdropPlace();

                    break;

                }

                case RIGHT: {
/*
                    moveBot(25, 0, 0, -1);// ←
                    lowerArm();
                    moveBot(75, 1, 0, 0); // ↑
                    sleep(1000);
                    turnBot(95);
                    sleep(500);
                    moveBot(10, 1, 0, -1); // ←
                    sleep(500);
                    tapePlace();
                    sleep(500);
                    moveBot(10, 1, 0, 1); // ➜
                    sleep(500);
                    moveBot(2, -1, 0, 0); // ↓
                    sleep(500);
                    turnBot(215);                              // ↷
                    sleep(500);
                    moveBot(20, 0, 0, 1); // ➜
                    sleep(500);
                    moveBot(90, 1, 0, 0); // ↑
                    moveBot(5, 0, 0, 1);  // ➜
                    moveBot(15, 1, 0, 0); // ↑

                    backdropPlace();
                    break;*/
                    //moveBot(5 , 0, 0, -1);
                    moveBot(77, 0, 0, -1); //
                    sleep(500);
                    moveBot(93, 1, 0, 0); // ↑
                    sleep(500);
                    turnBot(92);                              //
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
                    tapePlace();

                    moveBot(51, -1, 0, 0);// ↓

                    turnBot(-230);                               //
                    moveBot(65, 1, 0, 0); // ↑
                    moveBot(5, 0, 0, 1);//
                    moveBot(5, 1, 0, 0);  // ↑
                    backdropPlace();
                    sleep(1000);
                    //moveBot(5, 1, 0, 0);
                    break;

                }
            }
            break;
        }

    }

}
