package org.usfirst.frc.team6355.robot.commands;

import org.usfirst.frc.team6355.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollectorForwardCommand extends Command {

    public CollectorForwardCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	RobotMap.collector.set(-RobotMap.COLLECTOR_SPEED_FORWARD);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
