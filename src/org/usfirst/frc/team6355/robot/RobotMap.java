package org.usfirst.frc.team6355.robot;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.sensors.PigeonIMU;

//import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static WPI_VictorSPX collector;
	public static WPI_VictorSPX pitch;
	    private static final int COLLECTOR_VICTOR_CAN_ID = 8;
	    private static final int PITCH_VICTOR_CAN_ID = 5;

	public static void init() {
		
	        collector = new WPI_VictorSPX(COLLECTOR_VICTOR_CAN_ID);
	        pitch = new WPI_VictorSPX(PITCH_VICTOR_CAN_ID);


		
	}
}
