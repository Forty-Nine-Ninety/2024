package frc.robot.commands;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;

public class ChainEndgameCommand extends Command {
    private final ArmSubsystem m_ArmSubsystem;

    public ChainEndgameCommand(ArmSubsystem armsubsystem) {
        m_ArmSubsystem = armsubsystem;
        addRequirements(m_ArmSubsystem);
    }

    @Override
    public void initialize(){
        m_ArmSubsystem.moveToPosition(0);
    }
}
