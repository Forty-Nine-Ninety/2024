//Allison B)

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ArmFerryBackupCommand extends Command{
    private final ArmSubsystem m_arm;

    public ArmFerryBackupCommand(ArmSubsystem arm){
        m_arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize(){
        m_arm.moveToPosition(-16.8269);
        // original 35
    }
}
