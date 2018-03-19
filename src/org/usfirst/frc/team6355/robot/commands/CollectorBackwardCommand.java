package org.usfirst.frc.team6355.robot.commands;

import org.usfirst.frc.team6355.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollectorBackwardCommand extends Command {

    public CollectorBackwardCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	System.out.println("CollectorBackwardCommand");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	RobotMap.collector.set(-RobotMap.COLLECTOR_SPEED);
	System.out.println("CollectorBackwardCommand init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	System.out.println("CollectorBackwardCommand execute");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	System.out.println("CollectorBackwardCommand isFinished");
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
