package org.usfirst.frc.team6355.robot.commands;

import org.usfirst.frc.team6355.robot.Robot;
import org.usfirst.frc.team6355.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollectorBackwardSecondsCommand extends Command {

    private double startTime = 0.0;
    private double collectorSeconds = 0.0;


    public CollectorBackwardSecondsCommand(double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	collectorSeconds = seconds ;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	setTimeout(Robot.timeout);

	startTime = Timer.getFPGATimestamp();	// seconds.
	RobotMap.collector.set(RobotMap.COLLECTOR_SPEED_FORWARD);
    }

    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	if (isTimedOut())
	    return true;

    	double currentTime = Timer.getFPGATimestamp();
    	if (startTime + collectorSeconds < currentTime){
		return true;
	}    		
    return false;
   }

    // Called once after isFinished returns true
    protected void end() {
	RobotMap.collector.set(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	RobotMap.collector.set(0.0);
    }
}
