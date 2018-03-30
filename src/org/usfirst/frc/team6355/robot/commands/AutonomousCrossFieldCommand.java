package org.usfirst.frc.team6355.robot.commands;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6355.robot.Robot;
import org.usfirst.frc.team6355.robot.RobotMap;

//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutonomousCrossFieldCommand extends Command {
	
	private double startDistance = 0.0;
	private double driveForwardInches = 0.0;
	private double driveForwardMagnitude = 0.0;
	
	
    public AutonomousCrossFieldCommand(double forwardMagnitude) {
    	requires(RobotMap.driveTrain);
    	System.out.println("Drive Fwd Inches created.");
    	driveForwardMagnitude = forwardMagnitude;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Reset the navx and turn assist.
	RobotMap.driveTrain.Stop();
	    String gameData;
	    int location;
	    DriverStation ds = DriverStation.getInstance();
	    location = ds.getLocation();
	    gameData = ds.getGameSpecificMessage();
	    
	    if(gameData.length() > 0)
	    {
		  if(gameData.charAt(0) == 'L')
		  {
		      if ( location == 1 ) // so robot on left side
		      {
			  driveForwardInches = 12 * 20 ; // change 20 to the number of feet it has to drive
		      }
		      else if ( location == 2) // robot in middle
		      {
			  driveForwardInches = 12 * 20 ; // change 20 to the number of feet it has to drive			  
		      }
		      else if ( location == 3 ) // robot on right side
		      {
			  driveForwardInches = 12 * 20 ; // change 20 to the number of feet it has to drive			  
		      }
		  } else { // game Data says switch on right side
		      if ( location == 1 )
		      {
			  driveForwardInches = 12 * 20 ; // change 20 to the number of feet it has to drive			  
		      }
		      else if ( location == 2 )
		      {
			  driveForwardInches = 12 * 20 ; // change 20 to the number of feet it has to drive			  
		      }
		      else if ( location == 3 )
		      {
			  driveForwardInches = 12 * 20 ; // change 20 to the number of feet it has to drive			  
		      }
		  }
	    }

	startDistance = RobotMap.driveTrain.getWheelDistanceAverage();
		
    	System.out.println("Drive Fwd init: From " + startDistance + " Magnitude: " + driveForwardMagnitude);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	RobotMap.driveTrain.Drive(driveForwardMagnitude, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double currentDistance = RobotMap.driveTrain.getWheelDistanceAverage();
    	double distanceDelta = Math.abs(currentDistance - startDistance);
    	
    	if (distanceDelta > Math.abs(driveForwardInches)){
    		return true;
    	}    	
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Drive Fwd end.");
    	RobotMap.driveTrain.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
