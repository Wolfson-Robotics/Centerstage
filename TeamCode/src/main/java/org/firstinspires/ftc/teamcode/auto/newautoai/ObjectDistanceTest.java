package org.firstinspires.ftc.teamcode.auto.newautoai;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.PixelDetection;
import org.firstinspires.ftc.teamcode.auto.AutoJava;
import org.firstinspires.ftc.teamcode.devices.Camera;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

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

    private double propHeight; //Units: mm

    private  double objectImageHeight; //Units: pixels

    protected ObjectDistanceTest() {
        super(true); //Must be tested from the blue side
    }

    @Override
    public void runOpMode() {

        this.initCamera();
        this.initMotors();

        objectImageHeight = calculateObjectImageHeight(); //Replace with some method that gets this

        waitForStart();

        PixelDetection.BackdropPosition position = pixelDetection.getPosition();
        double dist = 0D;

        //Must be tested on the center
        if (position == PixelDetection.BackdropPosition.CENTER) {

            dist = calculateDistance(); //Convert mm to inches
            telemetry.addLine(String.valueOf(dist));

        } else {
            telemetry.addLine("Team prop not placed center but rather " + String.valueOf(position));
        }

        telemetry.update();

        moveBot(dist,1,0,0);

    }

    /**
     *  Overrides the traditional moveBot, this one uses Millimeter as the distance the robot movements.
     */
    @Override
    protected void moveBot(double distMM, double forward, double pivot, double horizontal) {

        distMM /= 25.4; //Convert MM to inches
        distMM = 3.340 * distMM - 2.356; //Convert to Robot Units

        super.moveBot(distMM, forward, pivot, horizontal);

    }

    // TODO: Actually Calculate the Object Image Height
    protected double calculateObjectImageHeight() {

        //I have no clue how to do this
        Mat mat = new Mat(3, 3, CvType.CV_8UC4);

        System.out.println(mat.height());

        return mat.height();

    }

    /**
     * @return distance in millimeters
     */
    protected double calculateDistance() {
        return (Camera.focalLength * objectImageHeight * Camera.cameraResHeight) / (propHeight * Camera.sensorHeightMM);
    }

}
