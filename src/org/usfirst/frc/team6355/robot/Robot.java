package org.usfirst.frc.team6355.robot;

import org.usfirst.frc.team6355.robot.commands.CollectorBackwardCommand;
import org.usfirst.frc.team6355.robot.commands.CollectorForwardCommand;
import org.usfirst.frc.team6355.robot.commands.LedWallSponsors;
import org.usfirst.frc.team6355.robot.commands.LiftDownCommand;
import org.usfirst.frc.team6355.robot.commands.LiftUpCommand;
import org.usfirst.frc.team6355.robot.commands.PitchDownCommand;
import org.usfirst.frc.team6355.robot.commands.PitchUpCommand;

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
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    
    private static final int LEFT_1_VICTOR_CAN_ID = 9;
    private static final int LEFT_2_VICTOR_CAN_ID = 1;
    private static final int LEFT_3_VICTOR_CAN_ID = 2;
    
    private static final int RIGHT_1_VICTOR_CAN_ID = 3;
    private static final int RIGHT_2_VICTOR_CAN_ID = 4;
    private static final int RIGHT_3_VICTOR_CAN_ID = 6;

    
    private static final int PURPLE_BOX_PURPLE = 1;
    private static final int BUTTON_BOX_RED = 2;
    private static final int BUTTON_BOX_PINK = 3;
    private static final int BUTTON_BOX_TEAL = 4;
    private static final int BUTTON_BOX_L1RB = 5;
    private static final int BUTTON_BOX_R1RB = 6;
    private static final int BUTTON_BOX_L2LT = 2; // axis
    private static final int BUTTON_BOX_R2RT = 3; // axis
    private static final int BUTTON_BOX_OPTIONS = 8;
    private static final int BUTTON_BOX_R3SR = 10;
    
    
    private static final int SHIFT_BUTTON = 5;
    private static final int COLLECTOR_RELEASE_BUTTON = 3;
    private static final int PITCH_UP_BUTTON = 6;
    private static final int PITCH_DOWN_BUTTON = 4;
    private static final int COLLECTOR_FORWARD_BUTTON = 12;
    private static final int COLLECTOR_BACKWARD_BUTTON = 11;

    private static final int LIFT_UP_BUTTON = 7;
    private static final int LIFT_DOWN_BUTTON = 8;

//
    
//    PWMVictorSPX left1, left2, left3;
//    PWMVictorSPX right1, right2, right3;
//    PWMVictorSPX lift;
    
    WPI_VictorSPX left1, left2, left3;
    WPI_VictorSPX right1, right2, right3;
    WPI_VictorSPX lift, collector, pitch;
    
    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;

    DifferentialDrive differentialDrive;

//    XboxController xboxController;
    private Joystick buttonBox;
    private Joystick joystick;
    private PowerDistributionPanel pdp;
    private JoystickButton sponsorsButton;
    private LedWallSponsors lws;
    private Solenoid collector_release;
    static Solenoid solenoid;
    
    private NetworkTable led_matrix, live_window ;
    
    public static OI oi;
    
    private Boolean compressor = false ;

    private JoystickButton shiftButton;
    private JoystickButton pitchUpButton, pitchDownButton;
    private JoystickButton liftUpButton, liftDownButton;
    private JoystickButton collectorForwardButton, collectorBackwardButton;


    @Override
    public void robotInit() {
	RobotMap.init();

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

//        left1 = new WPI_VictorSPX(LEFT_1_VICTOR_PORT);
//        left1.setInverted(false);
//        left2 = new WPI_VictorSPX(LEFT_2_VICTOR_PORT);
//        left2.setInverted(true);
//        left3 = new WPI_VictorSPX(LEFT_3_VICTOR_PORT);
//        left3.setInverted(true);
//        right1 = new WPI_VictorSPX(RIGHT_1_VICTOR_PORT);
//        right1.setInverted(false);
//        right2 = new WPI_VictorSPX(RIGHT_2_VICTOR_PORT);
//        right2.setInverted(true);
//        right3 = new WPI_VictorSPX(RIGHT_3_VICTOR_PORT);
//        right3.setInverted(true);
//
//        lift = new WPI_VictorSPX(LIFT_VICTOR_PORT);

        
        left1 = new WPI_VictorSPX(LEFT_1_VICTOR_CAN_ID);
        left1.setInverted(false);
        left2 = new WPI_VictorSPX(LEFT_2_VICTOR_CAN_ID);
        left2.setInverted(true);
        left3 = new WPI_VictorSPX(LEFT_3_VICTOR_CAN_ID);
        left3.setInverted(true);
        right1 = new WPI_VictorSPX(RIGHT_1_VICTOR_CAN_ID);
        right1.setInverted(false);
        right2 = new WPI_VictorSPX(RIGHT_2_VICTOR_CAN_ID);
        right2.setInverted(true);
        right3 = new WPI_VictorSPX(RIGHT_3_VICTOR_CAN_ID);
        right3.setInverted(true);


        
        leftDrive = new SpeedControllerGroup(left1, left2, left3);
        rightDrive = new SpeedControllerGroup(right1, right2, right3);

        differentialDrive = new DifferentialDrive(leftDrive, rightDrive);

//        xboxController = new XboxController(2);
        
        // Solenoids
        if (compressor)
        {
        solenoid = new Solenoid(0);
        collector_release = new Solenoid(1);
        }
        
        // Joysticks
        buttonBox = new Joystick(1);
        joystick = new Joystick(0);
        
        shiftButton = new JoystickButton(joystick, SHIFT_BUTTON);

        pitchUpButton = new JoystickButton(joystick, PITCH_UP_BUTTON);
        pitchDownButton = new JoystickButton(joystick, PITCH_DOWN_BUTTON);

        liftUpButton = new JoystickButton(joystick, LIFT_UP_BUTTON);
        liftDownButton = new JoystickButton(joystick, LIFT_DOWN_BUTTON);

        collectorForwardButton = new JoystickButton(joystick, COLLECTOR_FORWARD_BUTTON);
        collectorBackwardButton = new JoystickButton(joystick, COLLECTOR_BACKWARD_BUTTON);
        
        pitchUpButton.whileHeld(new PitchUpCommand());
        pitchDownButton.whileHeld(new PitchDownCommand());

        collectorForwardButton.whileHeld(new CollectorForwardCommand());
        collectorBackwardButton.whileHeld(new CollectorBackwardCommand());

        liftUpButton.whileHeld(new LiftUpCommand());
        liftDownButton.whileHeld(new LiftDownCommand());

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

	NetworkTable.setServerMode();
	
    }

    @Override
    public void teleopInit() {}

    private void led_wall_selection()
    {
	if (buttonBox.getRawButton(PURPLE_BOX_PURPLE))
	    led_matrix.putString("image","HIJONDARR.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_RED))
	    led_matrix.putString("image","BAM.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_PINK))
	    led_matrix.putString("image","BLUEGHOST.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_TEAL))
	    led_matrix.putString("image","BUCKEYEREGIONAL.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_L1RB))
	    led_matrix.putString("image","CATAPILLAR.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_R1RB))
	    led_matrix.putString("image","DIGITALTWIN.PPM");
	else if (buttonBox.getRawAxis(BUTTON_BOX_L2LT) > 0.0)
	    led_matrix.putString("image","ELECTRICANSUNIONLOCAL38.PPM");
	else if (buttonBox.getRawAxis(BUTTON_BOX_R2RT) > 0.0)
	    led_matrix.putString("image","FIRSTFUELCELLS.PPM");
	else if (buttonBox.getRawAxis(BUTTON_BOX_OPTIONS) > 0.0)
	    led_matrix.putString("image","GOGOGADGET.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_R3SR))
	    led_matrix.putString("image","off");
//	else 
//	    led_matrix.putString("image","off");
	return;
    }
    
//    -rw-r--r-- 1 pi pi   5965 Mar 12 00:55 8BIT.PPM
//    -rw-r--r-- 1 pi pi   2797 Mar 12 00:55 BAM.PPM
//    -rw-r--r-- 1 pi pi   3085 Mar 12 00:55 BLUEGHOST.PPM
//    -rw-r--r-- 1 pi pi  52444 Mar 12 00:55 BUCKEYEREGIONAL.PPM
//    -rw-r--r-- 1 pi pi   3757 Mar 12 00:55 CATAPILLAR.PPM
//    -rw-r--r-- 1 pi pi  21998 Mar 12 00:55 DIGITALTWIN.PPM
//    -rw-r--r-- 1 pi pi  16910 Mar 12 00:55 ELECTRICANSUNIONLOCAL38.PPM
//    -rw-r--r-- 1 pi pi  77466 Mar 12 00:55 FIRSTFUELCELLS.PPM
//    -rw-r--r-- 1 pi pi  41308 Mar 12 00:55 GOGOGADGET.PPM
//    -rw-r--r-- 1 pi pi  41308 Mar 12 00:55 HIJONDARR.PPM
//    -rw-r--r-- 1 pi pi   5389 Mar 12 00:55 ILOVE8BIT.PPM
//    -rw-r--r-- 1 pi pi  68188 Mar 12 00:55 MAKESOMENOISE.PPM
//    -rw-r--r-- 1 pi pi   2509 Mar 12 00:55 MARIO.PPM
//    -rw-r--r-- 1 pi pi   8653 Mar 12 00:55 MCPC.PPM
//    -rw-r--r-- 1 pi pi 194940 Mar 12 00:55 MILITARYNAMES.PPM
//    -rw-r--r-- 1 pi pi  18266 Mar 12 00:55 NASA.PPM
//    -rw-r--r-- 1 pi pi   4237 Mar 12 00:55 PACMANDOTS.PPM
//    -rw-r--r-- 1 pi pi  13454 Mar 12 00:55 PACMANSCROLL.PPM
//    -rw-r--r-- 1 pi pi   2797 Mar 12 00:55 POW.PPM
//    -rw-r--r-- 1 pi pi   3085 Mar 12 00:55 REDGHOST.PPM
//    -rw-r--r-- 1 pi pi  66844 Mar 12 00:55 ROBOTSOVERPARMA.PPM
//    -rw-r--r-- 1 pi pi  20654 Mar 12 00:55 SOCALBLENDER.PPM
//    -rw-r--r-- 1 pi pi  36892 Mar 12 00:55 TEAM6355.PPM
//    -rw-r--r-- 1 pi pi   5005 Mar 12 00:55 WHAM.PPM

    @Override
    public void teleopPeriodic() {
	
	// Drive
        if (isOperatorControl() && isEnabled()) {
//            differentialDrive.arcadeDrive(xboxController.getY(GenericHID.Hand.kLeft), -xboxController.getX(GenericHID.Hand.kLeft));
            differentialDrive.arcadeDrive(joystick.getY(),-joystick.getX());
        }
        
        
        SmartDashboard.putNumber("joystick_x", joystick.getX());
//        SmartDashboard.putNumber("joystick_x", 1.999);
        
        
        if (compressor)
        {

        // Shift
//            solenoid.set(xboxController.getXButton());
            solenoid.set(joystick.getRawButton(SHIFT_BUTTON));
        
        // Collect
//            collector_release.set(xboxController.getBButton());
            collector_release.set(joystick.getRawButton(COLLECTOR_RELEASE_BUTTON));

        }
        
        // Lift
//        lift.set(xboxController.getY(GenericHID.Hand.kRight));

        //        System.out.println(oi.joystick.getRawButton(11));
        System.out.println(pdp.getCurrent(1));
        System.out.println("victor get current");
        System.out.println(right1.getOutputCurrent());

        System.out.println("button box r1rb");
        
        System.out.println(buttonBox.getRawButton(BUTTON_BOX_R1RB));
        System.out.println("button box options");
        System.out.println(buttonBox.getRawButton(BUTTON_BOX_OPTIONS));
        System.out.println("button box raw axis");
        System.out.println(buttonBox.getRawAxis(BUTTON_BOX_L2LT));
        
//        System.out.println(xboxController.getY(GenericHID.Hand.kRight));
        
//        table = NetworkTable.getTable("SmartDashboard");

        led_matrix = NetworkTable.getTable("LEDMatrix");
//        led_matrix.putNumber("image",2.7);
//        led_matrix.putString("image",);
        
        led_wall_selection();
        
        live_window = NetworkTable.getTable("/LiveWindow/Ungrouped/PowerDistributionPanel[0]");
        System.out.println("live_window");
        System.out.println(live_window.getNumber("chan1",9993.14159));
        
	Scheduler.getInstance().run();

    }

}