// Shreyans

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RegurgitationCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;
    private final ShooterSubsystem m_shooterSubsystem;

    public RegurgitationCommand(IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem){
        m_intakeSubsystem = intakeSubsystem;
        m_shooterSubsystem = shooterSubsystem;
        addRequirements(intakeSubsystem, shooterSubsystem);

    }

    @Override
    public void execute(){
        m_shooterSubsystem.indexerToShooter(-1);
        m_intakeSubsystem.extendIntake();
        m_intakeSubsystem.intakePercentOutput(-1);
        m_shooterSubsystem.shoot(1);
    }
    @Override
    public void end(boolean interrupted){
        m_intakeSubsystem.intakePercentOutput(0.0);
        m_shooterSubsystem.shoot(0.0);
        m_intakeSubsystem.retractIntake();
        m_shooterSubsystem.indexerToShooter(0);
    }
}