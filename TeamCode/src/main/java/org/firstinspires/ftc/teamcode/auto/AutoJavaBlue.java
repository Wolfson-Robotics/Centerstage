package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "AutoJavaBlue", group = "Auto")
public class AutoJavaBlue extends AutoJava {


    public AutoJavaBlue() {
        super(true, false);
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
                right_drive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                right_drive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                left_drive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                left_drive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
                moveBot(87.824, 1, 0, 0);
                sleep(500);
                // 8 inches input
                moveBot(24.364, -1, 0, 0);
                sleep(1000);
                tapePlace();
                break;

            }


            case CENTER: {

                moveBot(23, 0, 0, 1);
                moveBot(20, 1, 0, 0);
                sleep(1000);
                lowerArm();
                sleep(1000);
                moveBot(67, 1, 0, 0);
                sleep(1150);
                moveBot(20, -1, 0, 0);
                sleep(1150);
                tapePlace();
                break;

            }


            case RIGHT: {

                moveBot(47, 0, 0, 1);
                sleep(1000);
                lowerArm();
                sleep(1000);
                moveBot(43, 1, 0, 0);
                sleep(850);
                moveBot(3, -1, 0, 0);
                sleep(1000);
                tapePlace();
                break;

            }

        }


    }
}
