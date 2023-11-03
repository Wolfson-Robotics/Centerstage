package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.slf4j.event.Level;

import java.util.ArrayList;

@Autonomous(name = "AutoJava", group = "Auto")
public class AutoJava extends LinearOpMode {
    private DcMotorEx right_drive1;
    private DcMotorEx right_drive2;
    private DcMotorEx left_drive1;
    private DcMotorEx left_drive2;
    public Servo elbowServo;
    public Servo armServo;
    public Servo claw1;
    private final double WHEEL_DIAMETER_INCHES = 3.0;
    private final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER_INCHES;

    private final double TICKS_PER_ROTATION = 288;
    private ArrayList<String> movements = new ArrayList<>();
    //private volatile SleeveDetection.ParkingPosition pos;

    private PixelDetection pixelDetection;
    private OpenCvCamera camera;

    // Name of the Webcam to be set in the config
    private String webcamName = "Webcam 1";


    double powerFactor = 1;
    double intCon = 8.727272;
    double startingPF = 0;
    boolean startPressed = false;
    boolean clawClosed = false;
    //we may need some additional variables here ^^

    private void initMotors() {

        right_drive1 = hardwareMap.get(DcMotorEx.class, "right_drive1");
        right_drive2 = hardwareMap.get(DcMotorEx.class, "right_drive2");
        left_drive1 = hardwareMap.get(DcMotorEx.class, "left_drive1");
        left_drive2 = hardwareMap.get(DcMotorEx.class, "left_drive2");

        right_drive1.setDirection(DcMotorSimple.Direction.REVERSE);
        right_drive2.setDirection(DcMotorSimple.Direction.REVERSE);
        claw1 = hardwareMap.get(Servo.class, "claw");
        elbowServo = hardwareMap.get(Servo.class, "elbow");
        armServo = hardwareMap.get(Servo.class, "arm");
        claw1.setPosition(0.08);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.7);
        // stop and reset encoder goes in init motors don't change
        // claw things here
        powerFactor = 1;
        startingPF = powerFactor;
    }


    @Override
    public void runOpMode() {
        initMotors();

            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);
            pixelDetection = new PixelDetection();
            camera.setPipeline(pixelDetection);


            camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
            {
                @Override
                public void onOpened()
                {
                    camera.startStreaming(432,240, OpenCvCameraRotation.UPRIGHT);
                }

                @Override
                public void onError(int errorCode) {
                    telemetry.addData("Camera error code:", errorCode);
                    telemetry.update();
                }
            });


            while (!isStarted()) {
                telemetry.addData("White percent of LCR mats:", pixelDetection.getLeftWhitePercent() + " "
                        + pixelDetection.getCenterWhitePercent() + " " + pixelDetection.getRightWhitePercent());
                telemetry.addData("ROTATION1: ", pixelDetection.getPosition());
                telemetry.update();
            }


        telemetry.addLine("Waiting for start");
        telemetry.update();
        waitForStart();

        startPressed = true;
        boolean stop = false;
        camera.closeCameraDevice();
        while (opModeIsActive()) {

            if(!stop) {
                stop = true;
                moveBot(28, 1, 0, 0);
                armServo.setPosition(0.55);
                elbowServo.setPosition(0.27);
                claw1.setPosition(0.0);
                armServo.setPosition(0.55);
                elbowServo.setPosition(0.29);
                claw1.setPosition(0.08);
                armServo.setPosition(0.4927);
                elbowServo.setPosition(0.5483);

            }



        }


    }


    public void moveBot(double distIN, float vertical, float pivot, float horizontal) {

        // 23 motor tics = 1 IN
        int motorTics;
        int posNeg = (vertical >= 0) ? 1 : -1;

        right_drive1.setPower(powerFactor * (-pivot + (vertical - horizontal)));
        right_drive2.setPower(powerFactor * (-pivot + vertical + horizontal));
        left_drive1.setPower(powerFactor * (pivot + vertical + horizontal));
        left_drive2.setPower(powerFactor * (pivot + (vertical - horizontal)));

        if (horizontal >= 0) {
            motorTics = left_drive1.getCurrentPosition() + (int) ((distIN * intCon) * posNeg);
            if (posNeg == -1) {
                while ((left_drive1.getCurrentPosition() > motorTics) && opModeIsActive()) {
                    idle();
                }
            } else {
                while ((left_drive1.getCurrentPosition() < motorTics) && opModeIsActive()) {
                    idle();
                }
            }
        } else {
            motorTics = right_drive1.getCurrentPosition() + (int) ((distIN * intCon) * posNeg);
            while ((right_drive1.getCurrentPosition() < motorTics) && opModeIsActive()) {
                idle();
            }
        }
        right_drive1.setPower(0);
        left_drive1.setPower(0);
        right_drive2.setPower(0);
        left_drive2.setPower(0);

    }

    private void turnBot(int degrees) {
        // 13.62 inches is default robot length
        double robotLength = 13.62;
        double distUnit = (robotLength) / (Math.cos(45));
        moveBot(((distUnit * (degrees*15.6211111)) / 90), 0, 1, 0);
    }

    private void moveServo(Servo servo, double targetPosition, long speed) {
        if (Math.abs(servo.getPosition() - targetPosition) > 0.01) {
            // Move the servo towards the target position slowly
            if (servo.getPosition() < targetPosition) {
                servo.setPosition(servo.getPosition() + .01);
            } else {
                servo.setPosition(servo.getPosition() - .01);
            }

            // Sleep for a short duration (adjust as needed)
            sleep(speed); // Sleep for 100 milliseconds (adjust for desired speed)
        }

    }
}


