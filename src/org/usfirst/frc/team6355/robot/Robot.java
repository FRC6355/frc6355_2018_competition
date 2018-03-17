package org.usfirst.frc.team6355.robot;

import org.usfirst.frc.team6355.robot.commands.LedWallSponsors;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Robot extends IterativeRobot {

    private static final int LEFT_1_VICTOR_PORT = 2;
    private static final int LEFT_2_VICTOR_PORT = 7;
    private static final int LEFT_3_VICTOR_PORT = 8;
    private static final int RIGHT_1_VICTOR_PORT = 3;
    private static final int RIGHT_2_VICTOR_PORT = 4;
    private static final int RIGHT_3_VICTOR_PORT = 5;
    private static final int LIFT_VICTOR_PORT = 9;
    private static final int SHIFT_BUTTON = 11;
    
    private static final int LEFT_1_VICTOR_CAN_ID = 9;
    private static final int LEFT_2_VICTOR_CAN_ID = 1;
    private static final int LEFT_3_VICTOR_CAN_ID = 2;
    
    private static final int RIGHT_1_VICTOR_CAN_ID = 3;
    private static final int RIGHT_2_VICTOR_CAN_ID = 4;
    private static final int RIGHT_3_VICTOR_CAN_ID = 6;

    private static final int COLLECTOR_VICTOR_CAN_ID = 8;
    private static final int LIFT_VICTOR_CAN_ID = 7;
    private static final int PITCH_VICTOR_CAN_ID = 5;
//
//    PWMVictorSPX left1, left2, left3;
//    PWMVictorSPX right1, right2, right3;
//    PWMVictorSPX lift;
    
    WPI_VictorSPX left1, left2, left3;
    WPI_VictorSPX right1, right2, right3;
    WPI_VictorSPX lift;
    
    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;

    DifferentialDrive differentialDrive;

    XboxController xboxController;
    private Joystick buttonBox;
    private Joystick joystick;
    private PowerDistributionPanel pdp;
    private JoystickButton sponsorsButton;
    private LedWallSponsors lws;
    private Solenoid collector_release;
    static Solenoid solenoid;
    
	public static OI oi;


    @Override
    public void robotInit() {

//        left1 = new PWMVictorSPX(LEFT_1_VICTOR_PORT);
//        left1.setInverted(false);
//        left2 = new PWMVictorSPX(LEFT_2_VICTOR_PORT);
//        left2.setInverted(true);
//        left3 = new PWMVictorSPX(LEFT_3_VICTOR_PORT);
//        left3.setInverted(true);
//        right1 = new PWMVictorSPX(RIGHT_1_VICTOR_PORT);
//        right1.setInverted(false);
//        right2 = new PWMVictorSPX(RIGHT_2_VICTOR_PORT);
//        right2.setInverted(true);
//        right3 = new PWMVictorSPX(RIGHT_3_VICTOR_PORT);
//        right3.setInverted(true);
//
//        lift = new PWMVictorSPX(LIFT_VICTOR_PORT);

        left1 = new WPI_VictorSPX(LEFT_1_VICTOR_PORT);
        left1.setInverted(false);
        left2 = new WPI_VictorSPX(LEFT_2_VICTOR_PORT);
        left2.setInverted(true);
        left3 = new WPI_VictorSPX(LEFT_3_VICTOR_PORT);
        left3.setInverted(true);
        right1 = new WPI_VictorSPX(RIGHT_1_VICTOR_PORT);
        right1.setInverted(false);
        right2 = new WPI_VictorSPX(RIGHT_2_VICTOR_PORT);
        right2.setInverted(true);
        right3 = new WPI_VictorSPX(RIGHT_3_VICTOR_PORT);
        right3.setInverted(true);

        lift = new WPI_VictorSPX(LIFT_VICTOR_PORT);

        
        leftDrive = new SpeedControllerGroup(left1, left2, left3);
        rightDrive = new SpeedControllerGroup(right1, right2, right3);

        differentialDrive = new DifferentialDrive(leftDrive, rightDrive);

        xboxController = new XboxController(1);
        solenoid = new Solenoid(0);
        collector_release = new Solenoid(1);
        buttonBox = new Joystick(0);
//        joystick = new Joystick(1);
        
	pdp = new PowerDistributionPanel();
	
	// OI must be constructed after subsystems. If the OI creates Commands
	// (which it very likely will), subsystems are not guaranteed to be
	// constructed yet. Thus, their requires() statements may grab null
	// pointers. Bad news. Don't move it.
	oi = new OI();


//      lws = new LedWallSponsors();
//      System.out.println("lws**************************");
//      System.out.println(lws);

//        sponsorsButton = new JoystickButton(joystick, 11);
//      sponsorsButton.whenPressed(new LedWallSponsors());
        
//      sponsorsButton.whileHeld(new LedWallSponsors());
//      sponsorsButton.whileHeld(lws);

	
	// Start camera feeds.
	try {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
	} catch (Exception ex) {
		DriverStation.reportError(ex.getMessage(), true);
	}

    }

    @Override
    public void teleopInit() {}


    @Override
    public void teleopPeriodic() {
        if (isOperatorControl() && isEnabled()) {
            differentialDrive.arcadeDrive(xboxController.getY(GenericHID.Hand.kLeft), -xboxController.getX(GenericHID.Hand.kLeft));
        }
        solenoid.set(xboxController.getXButton());
        collector_release.set(xboxController.getBButton());
//        System.out.println("button state");
//        System.out.println(oi.joystick.getRawButton(11));
        System.out.println(pdp.getCurrent(7));
        lift.set(xboxController.getY(GenericHID.Hand.kRight));
//        System.out.println(xboxController.getY(GenericHID.Hand.kRight));
        
	Scheduler.getInstance().run();

    }

}