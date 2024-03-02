package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoJavaRedFrontBackdrop", group = "Auto")
public class AutoJavaRedFrontBackdrop extends AutoJava {


    public AutoJavaRedFrontBackdrop() {
        super(false);
    }


    // Quick auto guide:
    // For turnBot, it turns in a clockwise direction. So negative values turn CCW, while positive values turn CW
    // For moveBot:
    // The robot will travel forward if the vertical parameter is positive. Otherwise, it will travel backwards.
    // The robot will travel right if the horizontal parameter is positive. Otherwise, it will travel left.

    // The "x inches input" comment is supposed to represent how many inches were initially plugged
    // into this equation: y=3.340x-2.356 (which is a lin reg model which converts inches to in-code units)
    // to get the value. The value will almost always be adjusted afterwards from its initial value for accuracy.
    @Override
    public void runOpMode() {

        this.commonAutoInit();
        switch (pixelDetection.getPosition()) {


            case LEFT: {

                // 27 inches input
                // offset of 23.0312
//                moveBot(87.824, 0, 0, 1);
                moveBot(64.7928, 0, 0, 1);
                sleep(500);
                // 45 inches input
                moveBot(114.944, 1, 0, 0);
                sleep(500);
                turnBot(-115);
                sleep(500);
                lowerArm();
                sleep(500);
                // 39 inches input
                // 27 inches input
                moveBot(75.824, 1, 0, 0);
                sleep(500);
                // 8 inches input
                moveBot(22.664, -1, 0, 0);
                sleep(500);
                tapePlace();
                sleep(500);
                turnBot(-230);
                sleep(500);
                // 30 inches input
                moveBot(108.844, 1, 0, 0);
                sleep(500);
                //moveBot 16 0 0 -1
                //moveBot 8 0 0 -1
//                moveBot(8.95, 0, 0, 1);
                moveBot(2.45, 0, 0, -1);
//                sleep(500);
                moveBot(21, 1, 0, 0);
                sleep(500);
                backdropPlace();
                sleep(500);
                moveBot(5, -1, 0, 0);
                // 42 inches input
                moveBot(127.924, 0, 0, 1);
                // 9 inches input
                moveBot(22.404, 1, 0, 0);
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
                moveBot(5.95, 0, 0, 1);
                sleep(500);
                moveBot(87.864, 1, 0, 0);
                sleep(500);
                backdropPlace();
                sleep(500);
                moveBot(5, -1, 0, 0);
                // 30 inches input
                moveBot(85.344, 0, 0, 1);
                // 9 inches input
                moveBot(31.704, 1, 0, 0);
                break;

            }

            case RIGHT: {

                // 13.5 inches input
                moveBot(31.04, 0, 0, 1);
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
                turnBot(120);
                sleep(500);
                // 25 inches input
                moveBot(83.144, 1, 0, 0);
                moveBot(12.775, 0, 0, -1);
                moveBot(10, 1, 0, 0);
                sleep(1000);
                backdropPlace();
                sleep(1000);
                moveBot(5, -1, 0, 0);
                // 14.5 inches input
                moveBot(65.074, 0, 0, 1);
                // 9 inches input
                moveBot(30.704, 1, 0, 0);
                break;

            }

        }



    }

}
