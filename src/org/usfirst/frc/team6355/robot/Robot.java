package org.usfirst.frc.team6355.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {

    private static final int LEFT_1_VICTOR_PORT = 2;
    private static final int LEFT_2_VICTOR_PORT = 7;
    private static final int LEFT_3_VICTOR_PORT = 8;
    private static final int RIGHT_1_VICTOR_PORT = 3;
    private static final int RIGHT_2_VICTOR_PORT = 4;
    private static final int RIGHT_3_VICTOR_PORT = 5;
    private static final int LIFT_VICTOR_PORT = 9;
    private static final int SHIFT_BUTTON = 11;

    PWMVictorSPX left1, left2, left3;
    PWMVictorSPX right1, right2, right3;
    PWMVictorSPX lift;
    
    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;

    DifferentialDrive differentialDrive;

    XboxController xboxController;
    private Joystick joystick;
    static Solenoid solenoid;

    @Override
    public void robotInit() {

        left1 = new PWMVictorSPX(LEFT_1_VICTOR_PORT);
        left1.setInverted(false);
        left2 = new PWMVictorSPX(LEFT_2_VICTOR_PORT);
        left2.setInverted(true);
        left3 = new PWMVictorSPX(LEFT_3_VICTOR_PORT);
        left3.setInverted(true);
        right1 = new PWMVictorSPX(RIGHT_1_VICTOR_PORT);
        right1.setInverted(false);
        right2 = new PWMVictorSPX(RIGHT_2_VICTOR_PORT);
        right2.setInverted(true);
        right3 = new PWMVictorSPX(RIGHT_3_VICTOR_PORT);
        right3.setInverted(true);

        lift = new PWMVictorSPX(LIFT_VICTOR_PORT);

        
        leftDrive = new SpeedControllerGroup(left1, left2, left3);
        rightDrive = new SpeedControllerGroup(right1, right2, right3);

        differentialDrive = new DifferentialDrive(leftDrive, rightDrive);

        xboxController = new XboxController(0);
        solenoid = new Solenoid(0);
//        joystick = new Joystick(0);
    }

    @Override
    public void teleopInit() {}


    @Override
    public void teleopPeriodic() {
        if (isOperatorControl() && isEnabled()) {
            differentialDrive.arcadeDrive(xboxController.getY(GenericHID.Hand.kLeft), -xboxController.getX(GenericHID.Hand.kLeft));
        }
//        if (joystick.getRawButton(SHIFT_BUTTON))
        solenoid.set(xboxController.getXButton());
        
        lift.set(xboxController.getY(GenericHID.Hand.kRight));
//        System.out.println(xboxController.getTriggerAxis(GenericHID.Hand.kLeft));
        System.out.println(xboxController.getY(GenericHID.Hand.kRight));
//        if (xboxController.getYButton())
//        {
//            lift.set(0.1);
//        }
//        lift.set()
//        if (xboxController.getAButton())
//        {
//            solenoid.set(true);
//        }
//        else
//        {
//            solenoid.set(false);    
//        }
    }

}