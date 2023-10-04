package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "JavaDrive")
public class DriveJava extends LinearOpMode {

    private DcMotor right_drive1;
    private DcMotor right_drive2;
    private DcMotor left_drive1;
    private DcMotor left_drive2;
    private double powerFactor = 0;
    private int speedChangeValue = 1;
    private boolean speedChangedUp = false;
    private boolean speedChangedDown = false;

    private void initMotors() {

        right_drive1 = hardwareMap.get(DcMotor.class, "right_drive1");
        right_drive2 = hardwareMap.get(DcMotor.class, "right_drive2");
        left_drive1 = hardwareMap.get(DcMotor.class, "left_drive1");
        left_drive2 = hardwareMap.get(DcMotor.class, "left_drive2");
        // Put initialization blocks here
        right_drive1.setDirection(DcMotorSimple.Direction.REVERSE);
        right_drive2.setDirection(DcMotorSimple.Direction.REVERSE);
        powerFactor = 0.4;

    }

    @Override
    public void runOpMode() {
        initMotors();

        telemetry.addLine("Waiting for start");
        telemetry.update();

        /*
         * Wait for the user to press start on the Driver Station
         */
        waitForStart();
        telemetry.addLine("Driver mode initiated");
        telemetry.update();

        while (opModeIsActive()) {

            callSpeedChange();
            moveBot(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);

        }
    }

    /**
     * Describe this function...
     */

    void callSpeedChange()
    {

        if (this.gamepad1.right_trigger != 0 && !speedChangedUp)
        {
            speedChangedUp = true;
            speedChange(true);
        }
        else if (this.gamepad1.right_trigger == 0 && speedChangedUp)
        {
            speedChangedUp = false;
        }
        if (this.gamepad1.left_trigger != 0 && !speedChangedDown)
        {
            speedChangedDown = true;
            speedChange(false);
        }
        else if (this.gamepad1.left_trigger == 0 && speedChangedDown)
        {
            speedChangedDown = false;
        }

    }

    void speedChange(boolean faster)
    {

        if (faster && speedChangeValue < 4)
        {
            speedChangeValue++;
        }
        if (!faster && speedChangeValue > 0)
        {
            speedChangeValue--;
        }

        switch (speedChangeValue)
        {
            case 0:
                powerFactor = 0.2;
                break;
            case 1:
                powerFactor = 0.4;
                break;
            case 2:
                powerFactor = 0.6;
                break;
            case 3:
                powerFactor = 0.8;
                break;
            case 4:
                powerFactor = 1;
                break;
        }

        telemetry.addData("speed change", powerFactor);
        telemetry.update();

    }
   private void moveBot(float vertical, float pivot, float horizontal) {

        right_drive1.setPower(powerFactor * (-pivot + (vertical - horizontal)));
        right_drive2.setPower(powerFactor * (-pivot + vertical + horizontal));
        left_drive1.setPower(powerFactor * (pivot + vertical + horizontal));
        left_drive2.setPower(powerFactor * (pivot + (vertical - horizontal)));

        right_drive1.setPower(0);
        right_drive2.setPower(0);
        left_drive1.setPower(0);
        left_drive2.setPower(0);

    }
 }

