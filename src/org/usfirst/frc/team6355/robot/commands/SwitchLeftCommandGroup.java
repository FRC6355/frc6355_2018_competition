// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc.team6355.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class SwitchLeftCommandGroup extends CommandGroup {
    
    private static final double AUTONOMOUS_DRIVE_FORWARD_INCHES = 0.0;
    private static final double AUTONOMOUS_DRIVE_FORWARD_INCHES_1 = 48.0;
    private static final double AUTONOMOUS_DRIVE_FORWARD_INCHES_2 = 120.0;
    private static final double AUTONOMOUS_DRIVE_SPEED = 0.4;
    private static final double AUTONOMOUS_PITCH_UP_SECONDS = 0.5;
    private static final double AUTONOMOUS_PITCH_DOWN_SECONDS = 0.5;
    private static final double AUTONOMOUS_COLLECTOR_FORWARD_SECONDS = 0.5;
    private static final double AUTONOMOUS_COLLECTOR_BACKWARD_SECONDS = 0.2;
    private static final double AUTONOMOUS_LIFT_UP_SECONDS = 0.5;
    private static final double AUTONOMOUS_LIFT_DOWN_SECONDS = 0.5;
    private static final double AUTONOMOUS_TURN_SECONDS = 1.0;
    private static final double AUTONOMOUS_TURN_VALUE = 1.0;
    private static final double AUTONOMOUS_DRIVE_FORWARD_INCHES_3 = 24.0;
    private static final double AUTONOMOUS_SECONDS = 3;
    
    public SwitchLeftCommandGroup() {
    	System.out.println("Autonomous TryingAutonomousCommandGroup command created.");
    	this.addSequential(new PitchDownSecondsCommand(AUTONOMOUS_PITCH_DOWN_SECONDS));
    	this.addSequential(new DriveForwardInchesCommand(AUTONOMOUS_DRIVE_FORWARD_INCHES_1, AUTONOMOUS_DRIVE_SPEED));    	
    	this.addSequential(new AutonomousTurn1Command(AUTONOMOUS_TURN_SECONDS, AUTONOMOUS_TURN_VALUE));
    	this.addSequential(new AutonomousCrossFieldCommand(AUTONOMOUS_DRIVE_FORWARD_INCHES, AUTONOMOUS_DRIVE_SPEED));  
    	this.addSequential(new AutonomousTurn2Command(AUTONOMOUS_TURN_SECONDS, AUTONOMOUS_TURN_VALUE));
    	this.addSequential(new DriveForwardInchesCommand(AUTONOMOUS_DRIVE_FORWARD_INCHES_2, AUTONOMOUS_DRIVE_SPEED));  
    	this.addSequential(new AutonomousTurn3Command(AUTONOMOUS_TURN_SECONDS, AUTONOMOUS_TURN_VALUE));
    	this.addSequential(new DriveForwardInchesCommand(AUTONOMOUS_DRIVE_FORWARD_INCHES_3, AUTONOMOUS_DRIVE_SPEED));  
    	this.addSequential(new CollectorForwardSecondsCommand(AUTONOMOUS_SECONDS));



//    	this.addSequential(new DriveForwardSecondsCommand(AUTONOMOUS_DRIVE_FORWARD_SECONDS, AUTONOMOUS_DRIVE_SPEED));
//    	this.addSequential(new PitchDownSecondsCommand(AUTONOMOUS_PITCH_DOWN_SECONDS));
//    	this.addSequential(new CollectorForwardSecondsCommand(AUTONOMOUS_COLLECTOR_FORWARD_SECONDS));
//    	this.addSequential(new CollectorBackwardSecondsCommand(AUTONOMOUS_COLLECTOR_BACKWARD_SECONDS));
//    	this.addSequential(new LiftUpSecondsCommand(AUTONOMOUS_LIFT_UP_SECONDS));
//    	this.addSequential(new LiftDownSecondsCommand(AUTONOMOUS_LIFT_UP_SECONDS));
    	
    	
    }

}
																																								