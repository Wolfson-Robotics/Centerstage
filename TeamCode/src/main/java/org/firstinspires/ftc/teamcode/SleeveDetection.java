
/*
package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
        import org.opencv.core.CvType;
        import org.opencv.core.Mat;
        import org.opencv.core.Point;
        import org.opencv.core.Rect;
        import org.opencv.core.Scalar;
        import org.opencv.core.Size;
        import org.opencv.imgproc.Imgproc;
        import org.openftc.easyopencv.OpenCvPipeline;

public class SleeveDetection extends OpenCvPipeline {
    /*
    YELLOW  = Parking Left
    CYAN    = Parking Middle
    MAGENTA = Parking Right
     *|/

    public enum BackdropPosition {
        LEFT,
        CENTER,
        RIGHT
    }


    // TODO: adjust points

    private static Point LEFT_MAT_TOPLEFT_ANCHOR_POINT = new Point(0, 0);
    private static Point CENTER_MAT_TOPLEFT_ANCHOR_POINT = new Point(0, 0);
    private static Point RIGHT_MAT_TOPLEFT_ANCHOR_POINT = new Point(0, 0);

    // Width and height for the bounding box
    // TODO: change size
    public static int REGION_WIDTH = 30;
    public static int REGION_HEIGHT = 40;

    // Lower and upper boundaries for colors
    private static final Scalar
            lower_yellow_bounds  = new Scalar(150, 150, 70, 255),
            upper_yellow_bounds  = new Scalar(255, 255, 160, 255),

    // Color definitions
    private final Scalar YELLOW  = new Scalar(255, 255, 0);

    // Percent and mat definitions
    private double yelPercent;
    private Mat leftMat = new Mat(REGION_WIDTH, REGION_HEIGHT, CvType.CV_16UC4),
            Mat centerMat = new Mat(REGION_WIDTH, REGION_HEIGHT, CvType.CV_16UC4);
            Mat rightMat = new Mat(REGION_WIDTH, REGION_HEIGHT, CvType.CV_16UC4);


    private Mat blurredMat = new Mat();
    private Mat kernel = new Mat();

    // Anchor point definitions
    Point leftMat_pointA = generateMatPointA(LEFT_MAT_TOPLEFT_ANCHOR_POINT);
    Point leftMat_pointB = generateMatPointB(LEFT_MAT_TOPLEFT_ANCHOR_POINT);

    Point centerMat_pointA = generateMatPointA(CENTER_MAT_TOPLEFT_ANCHOR_POINT);
    Point centerMat_pointB = generateMatPointB(LEFT_MAT_TOPLEFT_ANCHOR_POINT);

    Point rightMat_pointA = generateMatPointA(RIGHT_MAT_TOPLEFT_ANCHOR_POINT);
    Point rightMat_pointB = generateMatPointB(LEFT_MAT_TOPLEFT_ANCHOR_POINT);


    Mat leftBlurredMat = new Mat();
    Mat centerBlurredMat = new Mat();
    Mat rightBlurredMat = new Mat();

    // Running variable storing the parking position
    private volatile BackdropPosition position = BackdropPosition.LEFT;



    @Override
    public Mat processFrame(Mat input) {

        int leftPercent = yelPercent(leftMat);
        int centerPercent = yelPercent(centerMat);
        int rightPercent = yelPercent(rightMat);

        int[] yelPercents = new int[] { leftPercent, centerPercent, rightPercent };
        int highestPercent = Arrays.stream(yelPercents).max().getAsInt();


        switch (highestPercent) {
            case leftPercent:
                position = BackdropPosition.LEFT;
                Imgproc.rectangle(
                        input,
                        leftMat_pointA,
                        leftMat_pointB,
                        YELLOW,
                        2
                        );
                break;
            case centerPercent:
                position = BackdropPosition.CENTER;
                Imgproc.rectangle(
                        input,
                        centerMat_pointA,
                        centerMat_pointB,
                        YELLOW,
                        2
                );
                break;
            case rightPercent:
                position = BackdropPosition.RIGHT;
                Imgproc.rectangle(
                        input,
                        rightMat_pointA,
                        rightMat_pointB,
                        YELLOW,
                        2
                );
                break;
        }

        // Memory cleanup
        blurredMat.release();
        yelMat.release();
        cyaMat.release();
        magMat.release();
        kernel.release();

        return input;
    }



    private int yelPercent(Mat blurredMat) {
/*
        Imgproc.blur(input, blurredMat, new Size(5, 5));
        blurredMat = blurredMat.submat(new Rect(sleeve_pointA, sleeve_pointB));

        // Apply Morphology
        kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.morphologyEx(blurredMat, blurredMat, Imgproc.MORPH_CLOSE, kernel);
*|/
        // Gets channels from given source mat

        Core.inRange(blurredMat, lower_yellow_bounds, upper_yellow_bounds, blurredMat);
        yelPercent = Core.countNonZero(blurredMat);

    }





    private Point generateMatPointA(Point matAnchorPoint) {
        return new Point(
                matAnchorPoint.x,
                matAnchorPoint.y);
    }

    private Point generateMatPointB(Point matAnchorPoint) {
        return new Point(
                matAnchorPoint.x + REGION_WIDTH,
                matAnchorPoint.y + REGION_HEIGHT);
    }




    // Returns an enum being the current position where the robot will park
    public ParkingPosition getPosition() {
        return position;
    }

    public double getYelPercent() {
        return yelPercent;
    }

    public double getCyaPercent() {
        return cyaPercent;
    }

    public double getMagPercent() {
        return magPercent;
    }

}*/
