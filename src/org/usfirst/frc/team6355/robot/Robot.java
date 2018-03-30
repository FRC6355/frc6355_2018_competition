package org.usfirst.frc.team6355.robot;

import org.usfirst.frc.team6355.robot.commands.*;
import org.usfirst.frc.team6355.robot.commands.StraightLeftCommandGroup;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
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

import org.usfirst.frc.team6355.robot.BNO055;


@SuppressWarnings("deprecation")
public class Robot extends IterativeRobot {
    
    public static OI oi;
    
    @SuppressWarnings("unused")
    private PowerDistributionPanel pdp;
       
    Preferences prefs;
    
    public static NetworkTable navx_table ;
    
    Command autonomousCommand;
    SendableChooser autoChooser;

    private double[] pos = new double[3]; // [x,y,z] position data
//    private BNO055.CalData cal;
    public static BNO055 imu;


    @Override
    public void robotInit() {
	
	// Preferences
	prefs = Preferences.getInstance();
//	RobotMap.use_compressor = prefs.getBoolean("use_compressor", false);
	System.out.println("use compressor prefs: " + RobotMap.use_compressor);

	RobotMap.init();
        
	pdp = new PowerDistributionPanel(0); // need this so we get data in network tables for digital twin
//	pdp.resetTotalEnergy();
	SmartDashboard.putData(pdp);
	
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
	
    	imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS,
				BNO055.vector_type_t.VECTOR_EULER);

	
	autoChooser = new SendableChooser() ;
	autoChooser.addDefault("just go straight from left position", new StraightLeftCommandGroup());
//	autoChooser.addDefault("just go straight from center position", new StraightCenterCommandGroup());
//	autoChooser.addDefault("just go straight from right position", new StraightRightCommandGroup());
	autoChooser.addDefault("go to switch", new AutonomousSwitchCommandGroup());

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
            RobotMap.differentialDrive.arcadeDrive(-OI.joystick.getY(),OI.joystick.getX());
        }        
        
        if (oi.joystick.getThrottle() > 0.7 )
        {
    		RobotMap.pitch.set(-RobotMap.PITCH_SPEED);
        }
        else if (oi.joystick.getThrottle() <  - 0.7 )
        {
		RobotMap.pitch.set(RobotMap.PITCH_SPEED);
        }
        else
        {
		RobotMap.pitch.set(0.0);
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
        
    	System.out.println("imu heading: " + imu.getHeading());
    	System.out.println("imu vector: " + imu.getVector()); // [heading, roll, pitch]

                        
	Scheduler.getInstance().run();
    }
    
    
    @Override
    public void autonomousInit() {
	    
	
	    
//	    Command tryingAutonomousCommandGroup = new StraightLeftCommandGroup() ;
	    
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