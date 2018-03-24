package org.usfirst.frc.team6355.robot.commands;

import org.usfirst.frc.team6355.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftUpCommand extends Command {

    public LiftUpCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	System.out.println("LiftUpCommand");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	RobotMap.lift.set(RobotMap.LIFT_SPEED);
	System.out.println("LiftUpCommand init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	System.out.println("LiftUpCommand execute");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	System.out.println("LiftUpCommand isFinished");
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
