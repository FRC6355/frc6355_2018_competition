package org.usfirst.frc.team6355.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;

@SuppressWarnings("deprecation")
public class Robot extends IterativeRobot {
    
    public static OI oi;
    
    @SuppressWarnings("unused")
    private PowerDistributionPanel pdp;
    
    AHRS ahrs;
    
    Gyro gyro;
    
    Preferences prefs;


    @Override
    public void robotInit() {
	
	// Preferences
	prefs = Preferences.getInstance();

	RobotMap.use_compressor = prefs.getBoolean("use_compressor", false);
	System.out.println("use compressor prefs: " + RobotMap.use_compressor);

	RobotMap.init();
        
//	pdp = new PowerDistributionPanel();
	pdp = new PowerDistributionPanel(0);
	SmartDashboard.putData(pdp);
//	pdp.g
//	PDP.reset(new PowerDistributionPanel(0));
//	SmartDashboard::PutData(PDP.get());

	pdp.resetTotalEnergy();
	
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
	
	try {
	    ahrs = new AHRS(SPI.Port.kMXP);
	} catch (RuntimeException ex) {
	    DriverStation.reportError("Error instantiating navX" + ex.getMessage(), true);
	}
	ahrs.reset();
	

    }

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {
	double left_rotations = RobotMap.left_encoder.getDistance();
	double right_rotations =  RobotMap.right_encoder.getDistance() ;
//	System.out.println("left: " + left_encoder.getDistance());
//	System.out.println("right: " + right_encoder.getDistance());
	
	double angle = ahrs.getAngle();
	System.out.println("angle: " + angle);
	System.out.println("pdp temp: " + pdp.getTemperature());
	System.out.println("pdp total current: " + pdp.getTotalCurrent());
	System.out.println("pdp total energy: " + pdp.getTotalEnergy());
	System.out.println("pdp total power: " + pdp.getTotalPower());
	System.out.println("pdp voltage: " + pdp.getVoltage());
//	System.out.println("gyro: " + gyro.getAngle());
	
//	pdp.getTemperature();
	
	// Drive
        if (isOperatorControl() && isEnabled()) {
            RobotMap.differentialDrive.arcadeDrive(OI.joystick.getY(),-OI.joystick.getX());
        }        
        
        
	System.out.println("in teleop use compressor prefs: " + RobotMap.use_compressor);

        if (RobotMap.use_compressor)
        {
            // Shift
            RobotMap.solenoid.set(OI.joystick.getRawButton(OI.SHIFT_BUTTON));
        
            // Collect
            RobotMap.collector_release.set(OI.joystick.getRawButton(OI.COLLECTOR_RELEASE_BUTTON));
        }
        
        OI.led_wall_selection();
                
	Scheduler.getInstance().run();

    }
    
    
	public void autonomousInit() {
	    
	    String gameData;
	    gameData = DriverStation.getInstance().getGameSpecificMessage();
	    if(gameData.length() > 0)
	    {
		  if(gameData.charAt(0) == 'L')
		  {
			//Put left auto code here
		  } else {
			//Put right auto code here
		  }
	    }
	    
	    System.out.println("game data: " + gameData);

	    
//		driveTrain.resetDistanceMeasures(); // Reset encoders to 0.
//		
//		// schedule the autonomous command (example)
//		// RobotMap.ahrs.reset();
//
//		if (autonomousCommand != null)
//			autonomousCommand.cancel();
//		
//		// Get the command that's selected in the chooser.
//		autonomousCommand = autonomousChooser.getSelected();
//		if (autonomousCommand != null)
//			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}


    
    
    
}