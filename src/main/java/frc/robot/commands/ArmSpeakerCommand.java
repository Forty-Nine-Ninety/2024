//Allison B)

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSpeakerCommand extends Command{
    private final ArmSubsystem arm_subsystem;
    private double position;

    public ArmSpeakerCommand(ArmSubsystem arm_subsystem){
        this.arm_subsystem = arm_subsystem;
        addRequirements(arm_subsystem);
    }
    public void setPosition(double position){
        this.position = position;
    }

    @Override
    public void initialize(){
        arm_subsystem.moveToPosition(position);
    }
}
