package org.usfirst.frc.team6355.robot.subsystems;


import org.usfirst.frc.team6355.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
public class Pitch extends Subsystem {

    DigitalInput limitSwitch = new DigitalInput(4);
    SpeedController pitchMotor = RobotMap.pitch;
    Counter counter = new Counter(limitSwitch);

    public boolean isSwitchSet() {
        return counter.get() > 0;
    }

    public void initializeCounter() {
        counter.reset();
    }

    public void pitchUp() {
	pitchMotor.set(RobotMap.PITCH_SPEED);
    }

    public void pitchDown() {
	pitchMotor.set(-RobotMap.PITCH_SPEED);
    }

    public void pitchStop() {
	pitchMotor.set(0.0);
    }
    protected void initDefaultCommand() {
    }
}