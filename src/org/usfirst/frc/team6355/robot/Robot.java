package org.usfirst.frc.team6355.robot;

import org.usfirst.frc.team6355.robot.commands.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;

//import org.usfirst.frc.team6355.robot.BNO055;


@SuppressWarnings("deprecation")
public class Robot extends IterativeRobot {
    
    public static OI oi;
    
    @SuppressWarnings("unused")
    private PowerDistributionPanel pdp;
       
    Preferences prefs;
    
    public static NetworkTable navx_table ;
    
    public static double timeout = 15.0;

    Command autonomousCommand;
    SendableChooser autoChooser;
    
    // BNO055
//    private double[] pos = new double[3]; // [x,y,z] position data
//    public static BNO055 imu;

    
    @Override
    public void robotInit() {
	
	// Preferences
	prefs = Preferences.getInstance();
//	RobotMap.use_compressor = prefs.getBoolean("use_compressor", false);
	System.out.println("use compressor prefs: " + RobotMap.use_compressor);

	RobotMap.init();
        
	pdp = new PowerDistributionPanel(0); // need this so we get data in network tables for digital twin
//	pdp.resetTotalEnergy();
//	SmartDashboard.putData(pdp);
	
	// OI must be constructed after subsystems. If the OI creates Commands
	// (which it very likely will), subsystems are not guaranteed to be
	// constructed yet. Thus, their requires() statements may grab null
	// pointers. Bad news. Don't move it.
	oi = new OI();
	
	// Start camera feeds.
	try {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
	} catch (Exception ex) {
		DriverStation.reportError(ex.getMessage(), true);
	}

	NetworkTable.setServerMode();
	navx_table = NetworkTable.getTable("navx");

	
	try {
	    RobotMap.ahrs = new AHRS(SPI.Port.kMXP);
	} catch (RuntimeException ex) {
	    DriverStation.reportError("Error instantiating navX" + ex.getMessage(), true);
	}
	RobotMap.ahrs.reset();
	
	autoChooser = new SendableChooser();
	autoChooser.addDefault("Autonomous Straight Sides", new StraightLeftCommandGroup());
	autoChooser.addObject("Autonomous Straight Center", new StraightCenterCommandGroup());
	SmartDashboard.putData("Auto_Mode_Chooser", autoChooser);

	// BNO055
//    	imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS,
//				BNO055.vector_type_t.VECTOR_EULER);


    }

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {
	double left_rotations = RobotMap.left_encoder.getDistance();
	double right_rotations =  RobotMap.right_encoder.getDistance() ;
	
	double angle = RobotMap.ahrs.getAngle();
	System.out.println("===================== angle: " + angle);
//	System.out.println("pdp temp: " + pdp.getTemperature());
//	System.out.println("pdp total current: " + pdp.getTotalCurrent());
//	System.out.println("pdp total energy: " + pdp.getTotalEnergy());
//	System.out.println("pdp total power: " + pdp.getTotalPower());
//	System.out.println("pdp voltage: " + pdp.getVoltage());
//	System.out.println("gyro: " + gyro.getAngle());
		
	// Drive
        if (isOperatorControl() && isEnabled()) {
            if ( Math.abs(OI.joystick.getY()) < 0.1 && Math.abs(OI.joystick.getZ()) < 0.1)
            {
        	RobotMap.differentialDrive.stopMotor();
            }
            else
            {
                RobotMap.differentialDrive.arcadeDrive(-OI.joystick.getY(),OI.joystick.getZ());        	
            }
        }        
        
//	System.out.println("in teleop use compressor prefs: " + RobotMap.use_compressor);

        if (RobotMap.use_compressor)
        {
            // Shift
            RobotMap.solenoid.set(OI.joystick.getRawButton(OI.SHIFT_BUTTON));
        
            // Collect
            RobotMap.collector_release.set(OI.joystick.getRawButton(OI.COLLECTOR_RELEASE_BUTTON));
        }
        
        OI.led_wall_selection();
        
        OI.camera_pan();
        
        navxToNetworkTables();
        
        // BNO005
//    	System.out.println("imu heading: " + imu.getHeading());
//    	System.out.println("imu vector: " + imu.getVector()); // [heading, roll, pitch]

        
//	RobotMap.pitch.set(OI.xbox.getY(Hand.kRight));

                        
	Scheduler.getInstance().run();
    }
    
    
    @Override
    public void autonomousInit() {
	    
	
	    
//	    Command tryingAutonomousCommandGroup = new StraightLeftCommandGroup() ;
//	    Command tryingAutonomousCommandGroup = new StraightCenterCommandGroup() ;
	    
//	    tryingAutonomousCommandGroup.start();
	    
//	     Command autonomousCommandPitchUpWithLimit = new PitchUpWithLimitCommand();
//	     autonomousCommandPitchUpWithLimit.start();

	    
//		driveTrain.resetDistanceMeasures(); // Reset encoders to 0.
//		
//		// schedule the autonomous command (example)
//		// RobotMap.ahrs.reset();
//
//		if (autonomousCommand != null)
//			autonomousCommand.cancel();
//		
//		// Get the command that's selected in the chooser.
		autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
    	@Override
    	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

        @Override
        public void disabledInit() 
	{
	    // Users should override this method for initialization code which 
	    // will be called the first time that the robot enters disabled mode. 
	    // This function will be called one time when the robot first enters disabled mode.
	    System.out.println("Robot.disabledInit()");
	}
        
        @Override
        public void disabledPeriodic() 
	{
	    // Users should override this method for code which will be called
	    // periodically at a regular rate while the robot is in disabled mode.
//	     System.out.println("Robot.disabledPeriodic()");
	}
	public void robotPeriodic() 
	{
//	     System.out.println("Robot.robotPeriodic()");
	}

	public void navxToNetworkTables() 
	{
//	    SmartDashboard.putData(ahrs.getAltitude());
	    double altitude = RobotMap.ahrs.getAltitude() ;
	    double navx_pitch = RobotMap.ahrs.getPitch() ;
	    double navx_roll = RobotMap.ahrs.getRoll() ;
	    double navx_yaw = RobotMap.ahrs.getYaw() ;
	    navx_table.putDouble("altitude", altitude );
	    navx_table.putDouble("navx_pitch", navx_pitch );
	    navx_table.putDouble("navx_roll", navx_roll );
	    navx_table.putDouble("navx_yaw", navx_yaw );
	    System.out.println("getAltitude: " + altitude );
	    
	}

    
    
}