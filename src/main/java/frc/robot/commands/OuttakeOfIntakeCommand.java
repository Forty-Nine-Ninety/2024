// Shreyans

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class OuttakeOfIntakeCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;
    private final ShooterSubsystem m_shootersubsystem;

    public OuttakeOfIntakeCommand(IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem){
        m_intakeSubsystem = intakeSubsystem;
        m_shootersubsystem = shooterSubsystem;
    }

    @Override
    public void execute(){
        m_shootersubsystem.percentOutput(-1);
        m_intakeSubsystem.intakePercentOutput(-1);
    }

    @Override
    public void end(boolean interrupted){
        m_intakeSubsystem.intakePercentOutput(0);
        m_shootersubsystem.percentOutput(0);
    }
}