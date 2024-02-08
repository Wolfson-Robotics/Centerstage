package org.firstinspires.ftc.teamcode.auto.newautoai;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.PixelDetection;
import org.firstinspires.ftc.teamcode.auto.AutoJava;
import org.firstinspires.ftc.teamcode.devices.Camera;
import org.firstinspires.ftc.teamcode.devices.Prop;
import org.firstinspires.ftc.teamcode.devices.Robot;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
 * Small test to see if the distance of the team prop from the camera can be calculated
 * and taken into affect for autonomous.
 *
 * <P>Must be tested on the blue center!<P>
 *
 * @author HarrisonFld
 */
@Autonomous(name = "Team Prop Distance Test", group = "Auto")
public class ObjectDistanceTest extends AutoJava {

    private double cameraAngle = Robot.cameraAngle;

    private double objectImageHeight; //Units: pixels

    protected ObjectDistanceTest() {
        super(true); //Must be tested from the blue side
    }

    public static void main(String[] args) {
        new ObjectDistanceTest().runOpMode();
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
            telemetry.addLine("Team prop not placed center but rather " + position);
        }

        telemetry.update();

        moveBot(dist * Math.sin(cameraAngle), 1,0,0); //Move the horizontal distance of the robot to the prop
        //moveBot(dist,1,0,0); //Move the Straightforward distance of the camera to prop
        lowerArm();

        camera.closeCameraDevice();

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

    protected double calculateObjectImageHeight() {

        VideoCapture video = new VideoCapture(0);
        Mat frame = new Mat(CvType.CV_8UC4);

        video.read(frame);

        //Temp to see if the camera width can be retrieved
        //telemetry.addLine("Frame Width" + String.valueOf(video.get(Videoio.CAP_PROP_FRAME_WIDTH)));
        //telemetry.update();

        double[] blueSampleRGB = {60, 221, 76}; // TODO: Update To Actual Team Prop Color

        double highestRow = 0;
        double lowestRow = frame.rows();
        int threshold = 10;

        for (int row = 0; row < frame.rows(); row++) {
            cols:
            for (int col = 0; col < frame.cols(); col++) {

                double[] pixelSampleRGB = frame.get(row, col);

                for (double blueRGB : blueSampleRGB) {
                    for (double pixelRGB : pixelSampleRGB) {

                        //Check if it this pixel meets the threshold
                        if (!(blueRGB - pixelRGB <= threshold || pixelRGB - blueRGB <= threshold)) {
                            continue cols;
                        }

                    }
                }

                highestRow = (row > highestRow) ? row : highestRow;
                lowestRow = (row < lowestRow) ? row : highestRow;

            }
        }

        frame.release();
        video.release();

        return highestRow - lowestRow;

    }

    /**
     * @return distance in millimeters
     */
    protected double calculateDistance() {
        return (Camera.focalLength * objectImageHeight * Camera.cameraResHeight) / (Prop.propHeight * Camera.sensorHeightMM);
    }

}
