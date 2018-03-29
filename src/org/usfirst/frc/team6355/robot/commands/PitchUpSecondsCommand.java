package org.usfirst.frc.team6355.robot.commands;

import org.usfirst.frc.team6355.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PitchUpSecondsCommand extends Command {
    
    private double startTime = 0.0;
    private double pitchSeconds = 0.0;


    public PitchUpSecondsCommand(double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
//	requires(RobotMap.pitch_subsystem);
	pitchSeconds = seconds ;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	startTime = Timer.getFPGATimestamp();	// seconds.
	RobotMap.pitch.set(RobotMap.PITCH_SPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double currentTime = Timer.getFPGATimestamp();
    	if (startTime + pitchSeconds < currentTime){
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
