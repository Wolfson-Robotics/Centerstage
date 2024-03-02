package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoJavaBlueBackBackdrop", group = "Auto")
public class AutoJavaBlueBackBackdrop extends AutoJava {


    public AutoJavaBlueBackBackdrop() {
        super(true);
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
//                moveBot(59.824, 0, 0, 1);
                moveBot(29.459415, 0, 0, 1);
                sleep(500);
// 45 inches input
                moveBot(96.944, 1, 0, 0);
                sleep(500);
                turnBot(-115);
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
                sleep(500);
                moveBot(27.041668920139077, -1, 0, 0);
                sleep(500);
                moveBot(92.12500767708399, 0, 0, -1);
                sleep(350);
                trussArm();
                sleep(350);
/*
moveBot(94.64584122048677, 1, 0, 0);
sleep(1000);
moveBot(181.72918181076517, 1, 0, 0);
*/
                moveBot(276.37502303125194, 1, 0, 0);
                sleep(500);
                moveBot(51.333337611111475, 0, 0, 1);
                sleep(500);
                moveBot(51.906254325521196, 1, 0, 0);
                sleep(500);
                backdropPlace();
                switch (this.parkDir) {
                    case "leftPark":
                        sleep(500);
                        moveBot(6.645833887152825, -1, 0, 0);
                        moveBot(95.39584099132009, 0, 0, 1);
                        moveBot(40.67708672309056, 1, 0, 0);
                        break;
                    case "rightPark":
                        sleep(500);
                        moveBot(6.645833887152825, -1, 0, 0);
                        moveBot(79.06250658854222, 0, 0, -1);
                        moveBot(44.34375369541281, 1, 0, 0);
                        break;
                }
                break;

            }

            case CENTER: {

                lowerArm();
//                moveBot(16.38541803211817, 0, 0, 1);
                moveBot(20.054169696788183, 0, 0, -1);
                sleep(500);
                moveBot(131.54167762847314, 1, 0, 0);
                sleep(500);
                moveBot(50.30208752517397, -1, 0, 0);
                sleep(500);
                tapePlace();
                sleep(500);
                turnBot(-112);
                sleep(500);
                moveBot(282.21877351823116, 1, 0, 0);
                sleep(500);
//moveBot 9.6250008020834 0 0 1
//moveBot 71.50000595833383 0 0 -1
//sleep 500
//moveBot 51.906254325521196 1 0 0
                sleep(500);
                backdropPlace();
                switch(this.parkDir) {
                    case "leftPark":
                        sleep(500);
                        moveBot(6.645833887152825, -1, 0, 0);
                        sleep(500);
                        moveBot(86.02084041840338, 0, 0, 1);
                        sleep(500);
                        moveBot(44.34375369541281, 1, 0, 0);
                        break;
                    case "rightPark":
                        sleep(500);
                        moveBot(43.08333692361141, -1, 0, 0);
                        sleep(500);
                        moveBot(85.25000710416727, 0, 0, -1);
                        sleep(500);
                        moveBot(74.36458953038246, 1, 0, 0);
                        sleep(500);
                        break;
                }
                break;

            }

            case RIGHT: {

// 13.5 inches input PLUS 7.5 inches input 31.04 + 22.694
//                moveBot(57.934, 0, 0, 1);
                moveBot(27.569415, 0, 0, 1);
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
                sleep(500);
                moveBot(97.85417482118125, 1, 0, 0);
                sleep(500);
                moveBot(204.41668370139033, 1, 0, 0);
                sleep(500);
                moveBot(103.39584144965346, 0, 0, 1);
                sleep(500);
                moveBot(26.010418834201573, 1, 0, 0);
                sleep(500);
                backdropPlace();
                switch (this.parkDir) {
                    case "leftPark":
                        moveBot(6.645833887152825, -1, 0, 0);
                        sleep(500);
                        moveBot(59.12500492708375, 0, 0, 1);
                        sleep(500);
                        moveBot(37.166670180555855, 1, 0, 0);
                        break;
                    case "rightPark":
                        sleep(500);
                        moveBot(6.645833887152825, -1, 0, 0);
                        moveBot(116.22917585243133, 0, 0, -1);
                        moveBot(36.55208637934054, 1, 0, 0);
                        break;
                }
                break;

            }

        }


    }
}
