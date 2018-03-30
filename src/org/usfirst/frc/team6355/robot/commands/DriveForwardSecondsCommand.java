package org.usfirst.frc.team6355.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6355.robot.Robot;
import org.usfirst.frc.team6355.robot.RobotMap;

//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveForwardSecondsCommand extends Command {
	
    private double startTime = 0.0;
    private double driveSeconds = 0.0;
    private double driveForwardMagnitude = 0.0;
	
	
    public DriveForwardSecondsCommand(double seconds, double forwardMagnitude) {
    	requires(RobotMap.driveTrain);
    	driveSeconds = seconds;
    	driveForwardMagnitude = forwardMagnitude;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	setTimeout(Robot.timeout);
	
   	// Reset the navx and turn assist.
	RobotMap.driveTrain.Stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	RobotMap.driveTrain.Drive(driveForwardMagnitude, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
	
	if (isTimedOut())
	    return true;
		    
    	double currentTime = Timer.getFPGATimestamp();
    	if (startTime + driveSeconds < currentTime){
		return true;
	}    		
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTrain.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
