package org.usfirst.frc.team6355.robot.commands;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6355.robot.Robot;
import org.usfirst.frc.team6355.robot.RobotMap;

//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutonomousTurn2Command extends Command {
	
    private double startTime = 0.0;
    private double driveSeconds = 0.0;
    private double driveRotateValue = 0.0;
    private double turnDirection = 1.0;
	
    public AutonomousTurn2Command(double seconds, double rotateValue)
    {
    	requires(RobotMap.driveTrain);
    	driveSeconds = seconds;
    	driveRotateValue = rotateValue;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Reset the navx and turn assist.
	RobotMap.driveTrain.Stop();
	    String gameData;
	    gameData = DriverStation.getInstance().getGameSpecificMessage();
	    if(gameData.length() > 0)
	    {
		  if(gameData.charAt(0) == 'L')
		  {
		      turnDirection= 1;
		  } else {
		      turnDirection= -1;
		  }
	    }
	    
	    System.out.println("game data: " + gameData);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	RobotMap.driveTrain.Drive(0.0,driveRotateValue*turnDirection);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
