package org.usfirst.frc.team6355.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {

    private static final int LEFT_1_VICTOR_PORT = 12;
    private static final int LEFT_2_VICTOR_PORT = 13;
    private static final int LEFT_3_VICTOR_PORT = 14;
    private static final int RIGHT_1_VICTOR_PORT = 3;
    private static final int RIGHT_2_VICTOR_PORT = 2;
    private static final int RIGHT_3_VICTOR_PORT = 1;

    PWMVictorSPX left1, left2, left3;
    PWMVictorSPX right1, right2, right3;

    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;

    DifferentialDrive differentialDrive;

    XboxController xboxController;

    @Override
    public void robotInit() {

        left1 = new PWMVictorSPX(LEFT_1_VICTOR_PORT);
        left2 = new PWMVictorSPX(LEFT_2_VICTOR_PORT);
        left3 = new PWMVictorSPX(LEFT_3_VICTOR_PORT);
        right1 = new PWMVictorSPX(RIGHT_1_VICTOR_PORT);
        right2 = new PWMVictorSPX(RIGHT_2_VICTOR_PORT);
        right3 = new PWMVictorSPX(RIGHT_3_VICTOR_PORT);

        leftDrive = new SpeedControllerGroup(left1, left2, left3);
        rightDrive = new SpeedControllerGroup(right1, right2, right3);

        differentialDrive = new DifferentialDrive(leftDrive, rightDrive);

        xboxController = new XboxController(0);

    }


    @Override
    public void teleopInit() {}


    @Override
    public void teleopPeriodic() {
        if (isOperatorControl() && isEnabled()) {
            differentialDrive.arcadeDrive(xboxController.getY(GenericHID.Hand.kLeft), -xboxController.getX(GenericHID.Hand.kLeft));
        }
    }

}