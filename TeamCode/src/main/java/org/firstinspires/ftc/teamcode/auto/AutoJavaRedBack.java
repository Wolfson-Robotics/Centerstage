package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoJavaRedBack", group = "Auto")
public class AutoJavaRedBack extends AutoJava {


    public AutoJavaRedBack() {
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

// 13.5 inches input PLUS 7.5 inches input 31.04 + 22.694
                moveBot(57.934, 0, 0, -1);
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
                sleep(500);
                tapePlace();
                break;

            }

            case CENTER: {

                lowerArm();
                moveBot(16.38541803211817, 0, 0, -1);
                sleep(500);
                moveBot(131.54167762847314, 1, 0, 0);
                sleep(500);
                moveBot(50.30208752517397, -1, 0, 0);
                sleep(500);
                tapePlace();
                break;

            }

            case RIGHT: {

// 27 inches input
                moveBot(59.824, 0, 0, -1);
                sleep(500);
// 45 inches input
                moveBot(96.944, 1, 0, 0);
                sleep(500);
                turnBot(115);
                sleep(500);
                lowerArm();
                sleep(500);
// 39 inches input
// 27 inches input
// -5.5 inches input 80.824-16.014
                moveBot(47.136, 1, 0, 0);
                sleep(500);
// 8 inches input
                moveBot(14.364, -1, 0, 0);
                sleep(500);
                tapePlace();
                break;

            }

        }
        if (this.parkDir.equals("noPark")) {
            return;
        }
        switch(pixelDetection.getPosition()) {
            case LEFT:
                sleep(500);
                moveBot(41.79, -1, 0, 0);
                sleep(500);
                turnBot(115);
                sleep(500);
                moveBot(10.020834001736167, 0, 0, 1);
                sleep(500);
                trussArm();
                sleep(500);
                moveBot(302.27085852257158, 1, 0, 0);
                break;
            // TODO
            case CENTER:
                return;
            case RIGHT:
                sleep(500);
                moveBot(27.041668920139077, -1, 0, 0);
                sleep(500);
                moveBot(92.12500767708399, 0, 0, 1);
                sleep(500);
                trussArm();
                sleep(500);
                moveBot(276.37502303125194, 1, 0, 0);
                break;
        }
        switch (this.parkDir) {
            case "leftPark":
                sleep(500);
                moveBot(162.52084637673721, 0, 0, -1);
                moveBot(36.55208637934054, 1, 0, 0);
                break;
            case "rightPark":
                moveBot(36.55208637934054, 1, 0, 0);
                break;
        }



    }
}
