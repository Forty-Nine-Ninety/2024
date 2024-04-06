// Shreyans

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RegurgitationCommand extends Command {
    private final IntakeSubsystem m_intake;
    private final ShooterSubsystem m_shooter;

    public RegurgitationCommand(IntakeSubsystem intake, ShooterSubsystem shooter){
        m_intake = intake;
        m_shooter = shooter;
        addRequirements(intake, shooter);

    }

    @Override
    public void execute(){
        m_shooter.preshoot(-0.4);
        m_intake.extendIntake();
        m_intake.intakePercentOutput(-1);
        m_shooter.shoot(0.3);
    }
    @Override
    public void end(boolean interrupted){
        m_intake.intakePercentOutput(0.0);
        m_shooter.shoot(0.0);
        m_intake.retractIntake();
        m_shooter.preshoot(0);
    }
}