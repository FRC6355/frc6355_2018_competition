package org.usfirst.frc.team6355.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6355.robot.RobotMap;

public class PitchUpWithLimitCommand extends Command {

    public PitchUpWithLimitCommand() {
    }

    protected void initialize() {
        RobotMap.pitch_subsystem.initializeCounter();
        RobotMap.pitch_subsystem.pitchUp();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return RobotMap.pitch_subsystem.isSwitchSet();
    }

    protected void end() {
        RobotMap.pitch_subsystem.pitchStop();
    }

    protected void interrupted() {
        end();
    }
}
