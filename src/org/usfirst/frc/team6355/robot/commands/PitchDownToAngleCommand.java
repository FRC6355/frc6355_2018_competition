package org.usfirst.frc.team6355.robot.commands;

import org.usfirst.frc.team6355.robot.RobotMap;
import org.usfirst.frc.team6355.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PitchDownToAngleCommand extends Command {
    
    private static double desired_angle ;
    private static double tolerance  = 1.0 ; // try to get angle within these many degrees
    
    public PitchDownToAngleCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
//	requires(RobotMap.pitch_subsystem);
	desired_angle = angle ;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	RobotMap.pitch.set(-RobotMap.PITCH_SPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	double[] pos = new double[3]; // [x,y,z] position data - [heading, roll, pitch]
	double pitch ;
	
	pos = Robot.imu.getVector();
	pitch = pos[2];
    	if ( Math.abs(desired_angle -  pitch ) < tolerance){
		return true;
	}    	
	
    return false;
   }

    // Called once after isFinished returns true
    protected void end() {
	RobotMap.pitch.set(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	RobotMap.pitch.set(0.0);
    }
}