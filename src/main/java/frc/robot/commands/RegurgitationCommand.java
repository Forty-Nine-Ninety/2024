// Shreyans

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RegurgitationCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;

    public RegurgitationCommand(IntakeSubsystem intakeSubsystem){
        m_intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void execute(){
        m_intakeSubsystem.intakePercentOutput(-1);
    }
}