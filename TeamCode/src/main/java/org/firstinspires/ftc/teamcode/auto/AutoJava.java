package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.PixelDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;


public abstract class AutoJava extends LinearOpMode {


    protected DcMotorEx right_drive1;
    protected DcMotorEx right_drive2;
    protected DcMotorEx left_drive1;
    protected DcMotorEx left_drive2;
    protected Servo elbowServo;
    protected Servo armServo;
    protected Servo claw1;


    protected final double WHEEL_DIAMETER_INCHES = 3.0;
    protected final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER_INCHES;
    protected final double TICKS_PER_ROTATION = 288;
    protected final double intCon = 8.727272;


    protected ArrayList<String> movements = new ArrayList<>();
    //protected volatile SleeveDetection.ParkingPosition pos;

    protected PixelDetection pixelDetection;
    protected OpenCvCamera camera;

    // Name of the Webcam to be set in the config
    protected String webcamName = "Webcam 1";


    protected double powerFactor = 1;
    protected boolean blue = false;
    protected boolean brake = false;
    protected String parkDir = "noPark";

    protected AutoJava(boolean blue) {
        this.blue = blue;
        this.brake = true;
    }

    protected AutoJava(boolean blue, boolean brake) {
        this.blue = blue;
        this.brake = brake;
    }




    protected void initMotors() {

        right_drive1 = hardwareMap.get(DcMotorEx.class, "right_drive1");
        right_drive2 = hardwareMap.get(DcMotorEx.class, "right_drive2");
        left_drive1 = hardwareMap.get(DcMotorEx.class, "left_drive1");
        left_drive2 = hardwareMap.get(DcMotorEx.class, "left_drive2");

        right_drive1.setDirection(DcMotorSimple.Direction.REVERSE);
        right_drive2.setDirection(DcMotorSimple.Direction.REVERSE);

        if (this.brake) {
            right_drive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            right_drive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            left_drive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            left_drive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        claw1 = hardwareMap.get(Servo.class, "claw");
        elbowServo = hardwareMap.get(Servo.class, "elbow");
        armServo = hardwareMap.get(Servo.class, "arm");

        claw1.setPosition(0.12);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.7);

        powerFactor = 1;

    }



    protected void initCamera() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        this.camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);
        this.pixelDetection = new PixelDetection(this.blue);
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

    }


    protected void moveBot(double distIN, double vertical, double pivot, double horizontal) {

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
    protected void moveBotEncode(double Vertical, double Horizontal) {

        // 23 motor tics = 1 IN
        powerFactor = 0.75;
        double newIntCon = 57.142857;
        int motorTics;
        if(Vertical != 0) {
            int posNeg = (Vertical >= 0) ? 1 : -1;

            right_drive1.setPower(powerFactor * (posNeg));
            right_drive2.setPower(powerFactor * (posNeg));
            left_drive1.setPower(powerFactor * (posNeg));
            left_drive2.setPower(powerFactor * (posNeg));
            motorTics = left_drive2.getCurrentPosition() + (int) (((Vertical * newIntCon)*5.44680851) * posNeg);
            if (posNeg == -1) {
                while ((left_drive2.getCurrentPosition() > motorTics) && opModeIsActive()) {
                    idle();
                }
            } else {
                while ((left_drive2.getCurrentPosition() < motorTics) && opModeIsActive()) {
                    idle();
                }
            }
            right_drive1.setPower(0);
            left_drive1.setPower(0);
            right_drive2.setPower(0);
            left_drive2.setPower(0);
        }
        sleep(5000);
        if(Horizontal != 0) {
            int posNeg = (Horizontal >= 0) ? 1 : -1;
            newIntCon = 53.33333333;
            right_drive1.setPower(powerFactor * (-0 + (0 - Horizontal)));
            right_drive2.setPower(powerFactor * (-0 + 0 + Horizontal));
            left_drive1.setPower(powerFactor * (0 + 0 + Horizontal));
            left_drive2.setPower(powerFactor * (0 + (0 - Horizontal)));
            motorTics = right_drive2.getCurrentPosition() + (int) ((Horizontal * newIntCon) * posNeg);
            if (posNeg == -1) {
                while ((right_drive2.getCurrentPosition() > motorTics) && opModeIsActive()) {
                    idle();
                }
            } else {
                while ((right_drive2.getCurrentPosition() < motorTics) && opModeIsActive()) {
                    idle();
                }
            }
            right_drive1.setPower(0);
            left_drive1.setPower(0);
            right_drive2.setPower(0);
            left_drive2.setPower(0);
        }


    }
    public void moveBotExact(double motorTics, float vertical, float pivot, float horizontal) {

        // 23 motor tics = 1 IN

        int posNeg = (vertical >= 0) ? 1 : -1;

        right_drive1.setPower(powerFactor * (-pivot + (vertical - horizontal)));
        right_drive2.setPower(powerFactor * (-pivot + vertical + horizontal));
        left_drive1.setPower(powerFactor * (pivot + vertical + horizontal));
        left_drive2.setPower(powerFactor * (pivot + (vertical - horizontal)));

        if (horizontal >= 0) {
            motorTics = left_drive1.getCurrentPosition() + (int) ((motorTics) * posNeg);
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
            motorTics = right_drive1.getCurrentPosition() + (int) ((motorTics) * posNeg);
            while ((right_drive1.getCurrentPosition() < motorTics) && opModeIsActive()) {
                idle();
            }
        }
        right_drive1.setPower(0);
        left_drive1.setPower(0);
        right_drive2.setPower(0);
        left_drive2.setPower(0);

    }
    protected void moveBotTics(double motorTics, float vertical, float pivot, float horizontal) {

        int posNeg = (vertical >= 0) ? 1 : -1;

        right_drive1.setPower(powerFactor * (-pivot + (vertical - horizontal)));
        right_drive2.setPower(powerFactor * (-pivot + vertical + horizontal));
        left_drive1.setPower(powerFactor * (pivot + vertical + horizontal));
        left_drive2.setPower(powerFactor * (pivot + (vertical - horizontal)));

        if (horizontal >= 0) {
            motorTics = left_drive1.getCurrentPosition() + (int) ((motorTics) * posNeg);
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
            motorTics = right_drive1.getCurrentPosition() + (int) ((motorTics) * posNeg);
            while ((right_drive1.getCurrentPosition() < motorTics) && opModeIsActive()) {
                idle();
            }
        }
        right_drive1.setPower(0);
        left_drive1.setPower(0);
        right_drive2.setPower(0);
        left_drive2.setPower(0);

    }

    protected void turnBot(double degrees) {
        // 13.62 inches is default robot length
        double robotLength = 13.62;
        double distUnit = (robotLength) / (Math.cos(45));
        double distIN = Math.abs((distUnit * ((degrees*1.75))) / 90);
        int motorTics;
        int pivot = (degrees >= 0) ? 1 : -1;
        right_drive1.setPower(powerFactor * (-pivot));
        right_drive2.setPower(powerFactor * (-pivot));
        left_drive1.setPower(powerFactor * (pivot));
        left_drive2.setPower(powerFactor * (pivot));
        motorTics = left_drive1.getCurrentPosition() + (int) Math.round((distIN * intCon)* pivot);
        if(pivot == 1) {
            while ((left_drive1.getCurrentPosition() < motorTics) && opModeIsActive()) {
                idle();
            }
        }
        if(pivot == -1) {
            while ((left_drive1.getCurrentPosition() > motorTics) && opModeIsActive()) {
                idle();
            }
        }
        right_drive1.setPower(0);
        left_drive1.setPower(0);
        right_drive2.setPower(0);
        left_drive2.setPower(0);

    }

    protected void moveServo(Servo servo, double targetPosition, long speed) {
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






    protected void commonAutoInit() {
        initMotors();
        initCamera();

        while (!isStarted()) {
            if (gamepad1.dpad_down) {
                this.parkDir = "noPark";
            }
            if (gamepad1.dpad_left) {
                this.parkDir = "leftPark";
            }
            if (gamepad1.dpad_right) {
                this.parkDir = "rightPark";
            }
            telemetry.addData("Parking direction:", this.parkDir);
            telemetry.addData("White percent of LCR mats:", pixelDetection.getLeftPercent() + " "
                    + pixelDetection.getCenterPercent() + " " + pixelDetection.getRightPercent());
            telemetry.addData("ROTATION1: ", pixelDetection.getPosition());
            telemetry.update();
        }

        telemetry.addLine("Waiting for start");
        telemetry.update();
        waitForStart();

        camera.closeCameraDevice();
    }



    // Common functions for autonomous instructions
    protected void tapePlace() {
        restArm();
        sleep(750);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.27);
        sleep(750);
        claw1.setPosition(0.0);
        sleep(750);
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.3075);
        sleep(750);
        claw1.setPosition(0.12);
        sleep(750);
        restArm();
    }


    protected void backdropPlace() {
        claw1.setPosition(0);
        sleep(750);
        armServo.setPosition(0.4927);
        elbowServo.setPosition(0.5483);
    }

    // Mainly to prevent interference with teammate's pixel placement
    protected void backdropPlaceHigh() {
        armServo.setPosition(0.005);
        elbowServo.setPosition(0.0622);
        sleep(750);
        claw1.setPosition(0);
        sleep(750);
        armServo.setPosition(0.4927);
        elbowServo.setPosition(0.5483);
    }

    // This lowers the arm & claw not too much to push the team prop out of the way to place the pixel
    protected void lowerArm() {
        armServo.setPosition(0.55);
        elbowServo.setPosition(0.32485);
    }

    // Lowers the arm so that the robot can move under the truss
    protected void trussArm() {
        armServo.setPosition(0.4927);
        elbowServo.setPosition(0.5283);
    }

    protected void restArm() {
        armServo.setPosition(0.4927);
        elbowServo.setPosition(0.50);
    }




    public abstract void runOpMode();

}


