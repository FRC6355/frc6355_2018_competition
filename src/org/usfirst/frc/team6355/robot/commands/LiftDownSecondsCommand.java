package org.usfirst.frc.team6355.robot.commands;

import org.usfirst.frc.team6355.robot.Robot;
import org.usfirst.frc.team6355.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftDownSecondsCommand extends Command {

    private double startTime = 0.0;
    private double liftSeconds = 0.0;

    public LiftDownSecondsCommand(double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	liftSeconds = seconds ;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	setTimeout(Robot.timeout);

	startTime = Timer.getFPGATimestamp();	// seconds.
	RobotMap.lift.set(-RobotMap.LIFT_SPEED_DOWN);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	if (isTimedOut())
	    return true;
	
	double currentTime = Timer.getFPGATimestamp();
    	if (startTime + liftSeconds < currentTime){
		return true;
	}    		
    return false;
   }

    // Called once after isFinished returns true
    protected void end() {
	RobotMap.lift.set(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	RobotMap.lift.set(0.0);
    }
}
