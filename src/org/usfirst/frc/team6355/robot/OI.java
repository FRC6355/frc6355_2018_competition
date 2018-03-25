// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc.team6355.robot;

import org.usfirst.frc.team6355.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
@SuppressWarnings("deprecation")
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static Joystick joystick;
    private static Joystick buttonBox;

    // Solenoids
    public static final int SHIFT_BUTTON = 5;
    public static final int COLLECTOR_RELEASE_BUTTON = 3;
    public static JoystickButton shiftButton;

    // Motors
    private static final int PITCH_UP_BUTTON = 6;
    private static final int PITCH_DOWN_BUTTON = 4;
    private static final int COLLECTOR_FORWARD_BUTTON = 11;
    private static final int COLLECTOR_BACKWARD_BUTTON = 12;
    private static final int LIFT_UP_BUTTON = 7;
    private static final int LIFT_DOWN_BUTTON = 8;
    private JoystickButton pitchUpButton, pitchDownButton;
    private JoystickButton liftUpButton, liftDownButton;
    private JoystickButton collectorForwardButton, collectorBackwardButton;
    
    // Button box
    private static final int BUTTON_BOX_PURPLE = 1;
    private static final int BUTTON_BOX_RED = 2;
    private static final int BUTTON_BOX_PINK = 3;
    private static final int BUTTON_BOX_TEAL = 4;
    private static final int BUTTON_BOX_L1LB = 5;
    private static final int BUTTON_BOX_R1RB = 6;
    private static final int BUTTON_BOX_L2LT = 2; // axis
    private static final int BUTTON_BOX_R2RT = 3; // axis
    private static final int BUTTON_BOX_OPTIONS = 8;
    private static final int BUTTON_BOX_R3SR = 10;

    // LED wall
    private static NetworkTable led_matrix ;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        joystick = new Joystick(0);
        buttonBox = new Joystick(1);
        
        // Pitch
        pitchUpButton = new JoystickButton(joystick, PITCH_UP_BUTTON);
        pitchDownButton = new JoystickButton(joystick, PITCH_DOWN_BUTTON);
        pitchUpButton.whileHeld(new PitchUpCommand());
        pitchDownButton.whileHeld(new PitchDownCommand());

        // Collector
        collectorForwardButton = new JoystickButton(joystick, COLLECTOR_FORWARD_BUTTON);
        collectorBackwardButton = new JoystickButton(joystick, COLLECTOR_BACKWARD_BUTTON);
        collectorForwardButton.whileHeld(new CollectorForwardCommand());
        collectorBackwardButton.whileHeld(new CollectorBackwardCommand());

        // Lift
        liftUpButton = new JoystickButton(joystick, LIFT_UP_BUTTON);
        liftDownButton = new JoystickButton(joystick, LIFT_DOWN_BUTTON);
        liftUpButton.whileHeld(new LiftUpCommand());
        liftDownButton.whileHeld(new LiftDownCommand());

        // SmartDashboard Buttons
        // SmartDashboard.putData("Reset Drive Encoders Command", new ResetDrivetrainEncodersCommand());
		
        
        led_matrix = NetworkTable.getTable("LEDMatrix");

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getJoystick() {
        return joystick;
    }

    static void led_wall_selection()
    {	
	if (buttonBox.getRawButton(BUTTON_BOX_L1LB))
	    led_matrix.putString("image","SPONSORSCROLL.PPM");
	else if (buttonBox.getRawAxis(BUTTON_BOX_L2LT) > 0.0)
	    led_matrix.putString("image","FULLIMAGESCROLL.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_PURPLE))
	    led_matrix.putString("image","HIJONDARR.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_RED))
	    led_matrix.putString("image","BAM.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_PINK))
	    led_matrix.putString("image","BLUEGHOST.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_TEAL))
	    led_matrix.putString("image","BUCKEYEREGIONAL.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_R1RB))
	    led_matrix.putString("image","1STDIGITALTWIN.PPM");
	else if (buttonBox.getRawAxis(BUTTON_BOX_R2RT) > 0.0)
	    led_matrix.putString("image","FIRSTFUELCELLS.PPM");
	else if (buttonBox.getRawAxis(BUTTON_BOX_OPTIONS) > 0.0)
	    led_matrix.putString("image","GOGOGADGET.PPM");
	else if (buttonBox.getRawButton(BUTTON_BOX_R3SR))
	    led_matrix.putString("image","off");
//	else 
//	    led_matrix.putString("image","off");
	return;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

