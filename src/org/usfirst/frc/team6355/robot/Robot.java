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

@SuppressWarnings("deprecation")
public class Robot extends IterativeRobot {
    
    public static OI oi;
    
    @SuppressWarnings("unused")
    private PowerDistributionPanel pdp;

    private Encoder left_encoder;

    private Encoder right_encoder;
    
    AHRS ahrs;
    
    Gyro gyro;

    @Override
    public void robotInit() {
	RobotMap.init();
        
	pdp = new PowerDistributionPanel();
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
	double diameter = 6.0 ; // inches
	double revsPerPulse = 500.0 ;
	double distancePerPulse = Math.PI * diameter / revsPerPulse ;
//	left_encoder = new Encoder(7,8,false,Encoder.EncodingType.k4X);
	left_encoder = new Encoder(7,8,false);
	left_encoder.reset();
	left_encoder.setDistancePerPulse(distancePerPulse);
//	right_encoder = new Encoder(5,6,false,Encoder.EncodingType.k4X);
	right_encoder = new Encoder(5,6,true);
	right_encoder.reset();
	right_encoder.setDistancePerPulse(distancePerPulse);
	
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
	double left_rotations = left_encoder.getDistance();
	double right_rotations = right_encoder.getDistance() ;
//	System.out.println("left: " + left_encoder.getDistance());
//	System.out.println("right: " + right_encoder.getDistance());
	
	double angle = ahrs.getAngle();
	System.out.println("angle: " + angle);
	System.out.println("pdp temp: " + pdp.getTemperature());
	System.out.println("pdp total current: " + pdp.getTotalCurrent());
	System.out.println("pdp total energy: " + pdp.getTotalEnergy());
	System.out.println("pdp total power: " + pdp.getTotalPower());
	System.out.println("pdp voltage: " + pdp.getVoltage());
	System.out.println("gyro: " + gyro.getAngle());
	
//	pdp.getTemperature();

	
	
	// Drive
        if (isOperatorControl() && isEnabled()) {
            RobotMap.differentialDrive.arcadeDrive(OI.joystick.getY(),-OI.joystick.getX());
        }        
        
        if (RobotMap.compressor)
        {
            // Shift
            RobotMap.solenoid.set(OI.joystick.getRawButton(OI.SHIFT_BUTTON));
        
            // Collect
            RobotMap.collector_release.set(OI.joystick.getRawButton(OI.COLLECTOR_RELEASE_BUTTON));
        }
        
        OI.led_wall_selection();
                
	Scheduler.getInstance().run();

    }

}