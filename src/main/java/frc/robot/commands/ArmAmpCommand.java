//Allison B)

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ArmAmpCommand extends Command{
    private final ArmSubsystem arm_subsystem;
    public ArmAmpCommand(ArmSubsystem arm_subsystem){
        this.arm_subsystem = arm_subsystem;
    }

    @Override
    public void initialize(){
        arm_subsystem.moveToPosition(-100);
    }

    @Override
    public void end(boolean interrupted){
        arm_subsystem.moveToPosition(0);
    }
}
