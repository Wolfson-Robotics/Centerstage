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
    double focalLength = 4;
    double propHeight; //Put in mm!

    //Units: pixels
    double imageHeight;
    double objectImageHeight;


    protected ObjectDistanceTest() {
        super(true); //Must be tested from the blue side
    }

    @Override
    public void runOpMode() {
        this.commonAutoInit();

        imageHeight = 1D; //Replace with image resolution height
        objectImageHeight = 1D; //Replace with some method that gets this

        waitForStart();

        //Must be tested on the center
        if (pixelDetection.getPosition() == PixelDetection.BackdropPosition.CENTER) {
            calculateDistance();
        }

    }

    /**
     * @return distance in millimeters
     */
    protected double calculateDistance() {
        double distance = (focalLength * objectImageHeight * imageHeight) / (propHeight * sensorHeight);
        return distance;
    }

}
