package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "AutoJavaBlueFrontBackdrop", group = "Auto")
public class AutoJavaBlueFrontBackdrop extends AutoJava {


    public AutoJavaBlueFrontBackdrop() {
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

                moveBot(47, 0, 0, -1);
                sleep(500);
                lowerArm();
                sleep(500);
                moveBot(43, 1, 0, 0);
                sleep(750);
                moveBot(3, -1, 0, 0);
                sleep(500);
                tapePlace();
                sleep(500);
                turnBot(-100);
                moveBot(80, 1, 0, 0);
                //                    moveBot(5, 0, 0, 1);
                moveBot(5, 1, 0, 0);
                sleep(500);
                backdropPlace();
                sleep(500);
                moveBot(5, -1, 0, 0);
                moveBot(59.5, 0, 0, -1);
                moveBot(12.45, 1, 0, 0);
                break;

            }


            case CENTER: {

                moveBot(23, 0, 0, -1);
                moveBot(20, 1, 0, 0);
                sleep(500);
                lowerArm();
                sleep(500);
                moveBot(67, 1, 0, 0);
                sleep(500);
                moveBot(20, -1, 0, 0);
                sleep(500);
                tapePlace();
                sleep(500);
                turnBot(-90);
                sleep(500);
                moveBot(84, 1, 0, 0);
                moveBot(13.65, 0, 0, 1);
                sleep(500);
                moveBot(15, 1, 0, 0);
                sleep(500);
                //                    moveBot(10, 0, 0, -1);
                backdropPlace();
                sleep(500);
                /*
                moveBot(5, -1, 0, 0);
                moveBot(80, 0, 0, -1);
                moveBot(18, 1, 0, 0);
                 */
                moveBot(5, -1, 0, 0);
                sleep(500);
                moveBot(67.25000710416727, 0, 0, -1);
                sleep(500);
                moveBot(12.45, 1, 0, 0);
                sleep(500);
                break;

            }

            case RIGHT: {
                right_drive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                right_drive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                left_drive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                left_drive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                // 27 inches input
                moveBot(87.824, 0, 0, -1);
                sleep(500);
                // 45 inches input
                moveBot(114.944, 1, 0, 0);
                sleep(500);
                turnBot(120);
                sleep(500);
                lowerArm();
                sleep(500);
                // 39 inches input
                // 27 inches input
                moveBot(87.824, 1, 0, 0);
                sleep(500);
                // 8 inches input
                moveBot(24.364, -1, 0, 0);
                sleep(500);
                tapePlace();
                sleep(500);
                turnBot(230);
                sleep(500);
                // 30 inches input
                moveBot(116.844, 1, 0, 0);
                sleep(500);
                //moveBot 12.45 0 0 1
                //sleep 500
                moveBot(4.45, 0, 0, -1);
                sleep(500);
                backdropPlace();
                sleep(500);
                moveBot(5, -1, 0, 0);
                sleep(500);
                // 42 inches input
                /*
                moveBot(137.924, 0, 0, -1);
                sleep(500);
                // 9 inches input
                moveBot(27.704, 1, 0, 0);
                */
                moveBot(6.645833887152825, -1, 0, 0);
                moveBot(116.22917585243133, 0, 0, -1);
                moveBot(36.55208637934054, 1, 0, 0);
                break;

            }
        }


    }
}
