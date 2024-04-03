package frc.robot.commands;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;

public class ChainEndgameCommand extends Command {
    private final ArmSubsystem m_arm;

    public ChainEndgameCommand(ArmSubsystem arm) {
        m_arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize(){
        m_arm.moveToPosition(-90); //revhardware client pid said -65
    }
}
