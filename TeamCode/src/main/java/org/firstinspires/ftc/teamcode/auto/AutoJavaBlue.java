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

// 27 inches input
                moveBot(59.824, 0, 0, 1);
                sleep(1000);
// 45 inches input
                moveBot(96.944, 1, 0, 0);
                sleep(500);
                turnBot(-115);
                sleep(500);
                lowerArm();
                sleep(500);
// 39 inches input
// 27 inches input
// -5.5 inches input (80.824-16.014)
                moveBot(47.136, 1, 0, 0);
                sleep(500);
// 8 inches input
                moveBot(14.364, -1, 0, 0);
                sleep(500);
                tapePlace();
                sleep(1000);
                moveBot(27.041668920139077, -1, 0, 0);
                sleep(1000);
                moveBot(92.12500767708399, 0, 0, -1);
                trussArm();
                sleep(1000);
/*
moveBot(94.64584122048677, 1, 0, 0);
sleep(1000);
moveBot(181.72918181076517, 1, 0, 0);
*/
                moveBot(276.37502303125194, 1, 0, 0);
                sleep(1000);
                moveBot(51.333337611111475, 0, 0, 1);
                sleep(1000);
                moveBot(51.906254325521196, 1, 0, 0);
                sleep(1000);
                backdropPlace();
                break;

            }


            case CENTER: {

                moveBot(10, 0, 0, 1);
                sleep(400);
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
                sleep(1000);
                turnBot(-116);
                sleep(1000);
                moveBot(25.3229187769099, -1, 0, 0);
                sleep(1000);
                moveBot(74.25000618750052, 0, 0, -1);
                trussArm();
                sleep(1000);
                moveBot(80.32292336024362, 1, 0, 0);
                sleep(1000);
                moveBot(186.54168221180686, 1, 0, 0);
                sleep(1000);
                moveBot(71.50000595833383, 0, 0, 1);
                sleep(1000);
                moveBot(47.895837324653115, 1, 0, 0);
                sleep(1000);
                backdropPlace();
                break;

            }


            case RIGHT: {

// 13.5 inches input PLUS 7.5 inches input (31.04 + 22.694)
                moveBot(57.934, 0, 0, 1);
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
/*
sleep(1000);
moveBot(41.79, -1, 0, 0);
sleep(500);
moveBot(24, -1, 0, 0);
sleep(500);
moveBot(37.934, 0, 0, 1);
sleep(1000);
moveBot(96.944, 1, 0, 0);
sleep(10000);
turnBot(115);
sleep(1000);
moveBot(179.088, 1, 0, 0);
sleep(500);
moveBot(100, 1, 0, 0);
sleep(500);
*/
                sleep(1000);
                moveBot(41.79, -1, 0, 0);
                sleep(500);
                turnBot(-115);
                sleep(500);
                moveBot(10.020834001736167, 0, 0, -1);
                sleep(500);
                trussArm();
                sleep(500);
                moveBot(97.85417482118125, 1, 0, 0);
                sleep(500);
                moveBot(204.41668370139033, 1, 0, 0);
                sleep(500);
                moveBot(97.39584144965346, 0, 0, 1);
                sleep(500);
                moveBot(26.010418834201573, 1, 0, 0);
                sleep(500);
                backdropPlace();
                sleep(500);
                break;

            }

        }


    }
}
