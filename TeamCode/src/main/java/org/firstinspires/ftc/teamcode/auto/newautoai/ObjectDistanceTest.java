package org.firstinspires.ftc.teamcode.auto.newautoai;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PixelDetection;
import org.firstinspires.ftc.teamcode.auto.AutoJava;

/**
 * Small test to see if the distance of the team prop from the camera can be calculated
 * and taken into affect for autonomous.
 *
 * Must be tested on the blue center!
 *
 * @author HarrisonFld
 */
@Autonomous(name = "Team Prop Distance Test")
public class ObjectDistanceTest extends AutoJava {

    //Units: mm
    double sensorHeight = 2.02D;
    double focalLength = 4D;
    double propHeight; //Put in mm!

    //Units: pixels
    double imageHeight = 240D;
    double objectImageHeight;


    protected ObjectDistanceTest() {
        super(true); //Must be tested from the blue side
    }

    @Override
    public void runOpMode() {

        this.initCamera();
        this.initMotors();

        // TODO: Implement object height
        objectImageHeight = calculateObjectImageHeight(); //Replace with some method that gets this

        waitForStart();

        //Must be tested on the center

        PixelDetection.BackdropPosition position = pixelDetection.getPosition();
        double distIN = 0;

        if (position == PixelDetection.BackdropPosition.CENTER) {

            distIN = calculateDistance() / 25.4; //Convert mm to inches
            telemetry.addLine(String.valueOf(distIN));

        } else {
            telemetry.addLine("Team prop not placed center but rather " + String.valueOf(position));
        }

        telemetry.update();

        moveBot(distIN,0,0,0);

    }

    /**
     * @return distance in millimeters
     */

    protected double calculateObjectImageHeight() {
        return 1D;
    }

    protected double calculateDistance() {
        double distance = (focalLength * objectImageHeight * imageHeight) / (propHeight * sensorHeight);
        return distance;
    }

}
