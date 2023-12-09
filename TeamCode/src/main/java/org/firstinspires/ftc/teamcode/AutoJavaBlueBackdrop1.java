package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoJavaBlueBackdrop1", group = "Auto")
public class AutoJavaBlueBackdrop1 extends AutoJava {


    public AutoJavaBlueBackdrop1() {
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

                    moveBot(43, 0, 0, -1);// ←
                    armDrop();
                    moveBot(29, 1, 0, 0); // ↑
                    drop1();

                    turnBot(-100);                             // ↶
                    moveBot(80, 1, 0, 0); // ↑
                    moveBot(15, 0, 0, 1); // ➜
                    moveBot(5, 1, 0, 0);  // ↑

                    drop2();

                    break;

                }


                case CENTER: {

                    moveBot(23, 0, 0, -1);// ←
                    armDrop();
                    moveBot(67, 1, 0, 0); // ↑
                    drop1();

                    turnBot(-90);                              // ↶
                    sleep(1000);
                    moveBot(84, 1, 0, 0); // ↑
                    moveBot(15, 0, 0, 1); // ➜
                    sleep(500);
                    moveBot(15, 1, 0, 0); // ↑

                    drop2();

                    break;

                }

                case RIGHT: {

                    moveBot(15, 0, 0, -1);// ←
                    armDrop();
                    moveBot(83, 1, 0, 0); // ↑
                    sleep(1000);
                    turnBot(95);

                    drop1();

                    moveBot(2, -1, 0, 0); // ↓
                    sleep(500);
                    turnBot(215);                              // ↷
                    sleep(500);
                    moveBot(35, 0, 0, 1); // ➜
                    sleep(500);
                    moveBot(90, 1, 0, 0); // ↑
                    moveBot(5, 0, 0, 1);  // ➜
                    moveBot(10, 1, 0, 0); // ↑

                    drop2();
                    break;

                }
            }
            break;
        }

    }

    private void drop1() {
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
    }

    private void drop2() {
        sleep(1000);
        claw1.setPosition(0);
        sleep(1000);
        armServo.setPosition(0.4927);
        elbowServo.setPosition(0.5483);
        sleep(1000);
        moveBot(5, 1, 0, 0);
    }

    private void armDrop() {
        sleep(500);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.33);
        sleep(1000);
    }
}
