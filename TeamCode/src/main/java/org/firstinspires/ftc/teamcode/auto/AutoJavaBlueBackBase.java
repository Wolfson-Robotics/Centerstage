package org.firstinspires.ftc.teamcode.auto;

public abstract class AutoJavaBlueBackBase extends AutoJava {

    protected AutoJavaBlueBackBase(boolean blue) {
        super(blue);
    }

    public void tapeAuto() {
        switch (pixelDetection.getPosition()) {
            case LEFT: {
// 27 inches input
//                moveBot(59.824, 0, 0, 1);
                moveBot(29.459415, 0, 0, 1);
                sleep(500);
// 45 inches input
                moveBot(98.944, 1, 0, 0);
                sleep(500);
                turnBot(-115);
                sleep(500);
                lowerArm();
                sleep(500);
// 39 inches input
// 27 inches input
// -5.5 inches input 80.824-16.014
                moveBot(44.5729, 1, 0, 0);
                sleep(500);
// 8 inches input
                moveBot(19.70833497, -1, 0, 0);
                sleep(500);
                tapePlace();
                break;
            }
            case CENTER: {
                lowerArm();
//                moveBot(16.38541803211817, 0, 0, 1);
                moveBot(10.054169696788183, 0, 0, -1);
                sleep(500);
                moveBot(131.54167762847314, 1, 0, 0);
                sleep(500);
                moveBot(50.30208752517397, -1, 0, 0);
                sleep(500);
                tapePlace();
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
                sleep(500);
                tapePlace();
                break;
            }

        }
    }


    public void backdropAuto() {
        switch (pixelDetection.getPosition()) {
            case LEFT: {

                sleep(500);
                moveBot(27.041668920139077, -1, 0, 0);
                sleep(500);
                moveBot(92.12500767708399, 0, 0, -1);
                sleep(350);
                trussArm();
                sleep(350);
                moveBot(276.37502303125194, 1, 0, 0);
                sleep(500);
                moveBot(51.333337611111475, 0, 0, 1);
                sleep(500);
                moveBot(51.906254325521196, 1, 0, 0);
                sleep(500);
                backdropPlace();
                break;
            }
            case CENTER: {
                sleep(500);
                turnBot(-112);
                sleep(500);
                moveBot(282.21877351823116, 1, 0, 0);
                sleep(500);
                sleep(500);
                backdropPlace();
                break;
            }
            case RIGHT: {
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
                break;
            }
        }
    }


    public void leftPark() {
        switch (pixelDetection.getPosition()) {
            case LEFT: {
                sleep(500);
                moveBot(6.645833887152825, -1, 0, 0);
                moveBot(95.39584099132009, 0, 0, 1);
                moveBot(40.67708672309056, 1, 0, 0);
                break;

            }
            case CENTER: {
                sleep(500);
                moveBot(6.645833887152825, -1, 0, 0);
                sleep(500);
                moveBot(86.02084041840338, 0, 0, 1);
                sleep(500);
                moveBot(44.34375369541281, 1, 0, 0);
                break;
            }
            case RIGHT: {
                moveBot(6.645833887152825, -1, 0, 0);
                sleep(500);
                moveBot(59.12500492708375, 0, 0, 1);
                sleep(500);
                moveBot(37.166670180555855, 1, 0, 0);
                break;
            }
        }
    }

    public void rightPark() {
        sleep(500);
        switch (pixelDetection.getPosition()) {
            case LEFT: {
                moveBot(6.645833887152825, -1, 0, 0);
                moveBot(79.06250658854222, 0, 0, -1);
                moveBot(44.34375369541281, 1, 0, 0);
                break;

            }
            case CENTER: {
                moveBot(43.08333692361141, -1, 0, 0);
                sleep(500);
                moveBot(85.25000710416727, 0, 0, -1);
                sleep(500);
                moveBot(74.36458953038246, 1, 0, 0);
                sleep(500);
                break;
            }
            case RIGHT: {
                moveBot(6.645833887152825, -1, 0, 0);
                moveBot(116.22917585243133, 0, 0, -1);
                moveBot(36.55208637934054, 1, 0, 0);
                break;
            }
        }
    }


    public abstract void runOpMode();
}
