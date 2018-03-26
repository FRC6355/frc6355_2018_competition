package org.usfirst.frc.team6355.robot;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.sensors.PigeonIMU;

//import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
	
    	// Drive
	private static final int LEFT_1_VICTOR_CAN_ID = 9;
	private static final int LEFT_2_VICTOR_CAN_ID = 1;
	private static final int LEFT_3_VICTOR_CAN_ID = 2;
	    
	private static final int RIGHT_1_VICTOR_CAN_ID = 3;
	private static final int RIGHT_2_VICTOR_CAN_ID = 4;
	private static final int RIGHT_3_VICTOR_CAN_ID = 6;

	public static WPI_VictorSPX left1, left2, left3;
	public static WPI_VictorSPX right1, right2, right3;
	public static WPI_VictorSPX lift, collector, pitch;
	    
	public static SpeedControllerGroup leftDrive;
	public static SpeedControllerGroup rightDrive;

	public static DifferentialDrive differentialDrive;

	// Non-drive motors
	private static final int COLLECTOR_VICTOR_CAN_ID = 8;
	private static final int PITCH_VICTOR_CAN_ID = 5;
	private static final int LIFT_VICTOR_CAN_ID = 7;

	public static double COLLECTOR_SPEED = 1.0;
	public static double PITCH_SPEED = 0.5;
	public static double LIFT_SPEED = 0.4;
	
	// Pneumatics
	public static Boolean compressor = false ;
	private static final int SHIFTER_SOLENOID_ID = 0 ;
	private static final int COLLECTOR_RELEASE_SOLENOID_ID = 1 ;
	public static Solenoid collector_release;
	public static Solenoid solenoid;

	
	// Servo for camera
	public static Servo cameraServo;
	public static double CAMERA_ANGLE_LEFT = 45.0 ;
	public static double CAMERA_ANGLE_FORWARD = 111.0 ;
	public static double CAMERA_ANGLE_RIGHT = 210.0 ;
	public static double camera_angle = CAMERA_ANGLE_FORWARD ;
	public static double CAMERA_ANGLE_INC = 2.0 ;


	@SuppressWarnings("deprecation")
	public static void init() {
		
	        collector = new WPI_VictorSPX(COLLECTOR_VICTOR_CAN_ID);
	        pitch = new WPI_VictorSPX(PITCH_VICTOR_CAN_ID);
	        lift = new WPI_VictorSPX(LIFT_VICTOR_CAN_ID);

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
	        
	        // Solenoids
	        if (compressor)
	        {
	            solenoid = new Solenoid(SHIFTER_SOLENOID_ID);
	            collector_release = new Solenoid(COLLECTOR_RELEASE_SOLENOID_ID);
	        }
	        
	        cameraServo = new Servo(0);
	        
	        SmartDashboard.putData(cameraServo);

	        // Get the current value so that it doesn't move right away when
	        //   camera moving commands start
//	        cameraServo.setAngle(100.0);
	        RobotMap.camera_angle = cameraServo.getAngle();
	        cameraServo.setAngle(RobotMap.camera_angle);
	        
	        LiveWindow.addActuator("DriveTrain", "right1", (WPI_VictorSPX) right1);
//	        LiveWindow.addSensor("DriveTrain", "leftDriveTrainEncoder", left_encoder);


	}
}
