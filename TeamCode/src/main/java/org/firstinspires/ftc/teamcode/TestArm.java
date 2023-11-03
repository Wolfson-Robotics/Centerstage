package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "TestArm")
public class TestArm extends LinearOpMode {

    private DcMotor Arm;
    private Servo claw;
    private Servo ArmServo;

    private void initMotors() {

        Arm = hardwareMap.get(DcMotor.class, "Arm");
        claw = hardwareMap.get(Servo.class, "claw");
        ArmServo = hardwareMap.get(Servo.class, "ArmServo");
        claw.setDirection(Servo.Direction.REVERSE);

    }

    @Override
    public void runOpMode() {
        initMotors();
        ArmServo.setPosition(0.24);
        telemetry.addLine("Waiting for start");
        telemetry.update();

        /*
         * Wait for the user to press start on the Driver Station
         */

        waitForStart();
        telemetry.addLine("Driver mode initiated");
        telemetry.update();
        telemetry.addData("Servo Pos", claw.getPosition());
        telemetry.update();

        while (opModeIsActive()) {
            Arm.setPower(gamepad1.left_stick_y*0.5);
            ArmServo.setPosition(1-(gamepad1.right_stick_x + 0.24));
            telemetry.addData("right stick Pos", gamepad1.right_stick_x);
            telemetry.update();
            claw.setPosition((gamepad1.right_trigger == 1) ? 0.84 : 1);

         //   claw.setPosition(gamepad1.right_trigger);

        }
    }
}

    /**
     * Describe this function...
     */
