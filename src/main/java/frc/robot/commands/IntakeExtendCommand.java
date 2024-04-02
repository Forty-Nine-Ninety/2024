

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class IntakeExtendCommand extends Command{
    private final IntakeSubsystem m_intakeSubsystem;
    private final ShooterSubsystem m_shooterSubsystem;

    public IntakeExtendCommand(IntakeSubsystem intakeSubsystem,ShooterSubsystem shooterSubsystem){
        m_intakeSubsystem = intakeSubsystem;
        m_shooterSubsystem = shooterSubsystem;
        addRequirements(intakeSubsystem, shooterSubsystem);

    }

    @Override
    public void initialize(){
        m_intakeSubsystem.extendIntake();
    }

    @Override
    public void execute(){
        if(m_shooterSubsystem.breakBeam()){
            m_intakeSubsystem.intakePercentOutput(0);
            m_shooterSubsystem.indexerToShooter(0);
            m_intakeSubsystem.retractIntake();
            cancel();
        } else {
            m_intakeSubsystem.intakePercentOutput(1);
            m_shooterSubsystem.indexerToShooter(-0.3);
        }
    }

    @Override
    public void end(boolean interrupted){
      //  m_shooterSubsystem.shoot(0.0);
        m_shooterSubsystem.indexerToShooter(0);
        m_intakeSubsystem.intakePercentOutput(0);
        m_intakeSubsystem.retractIntake();
    }
}
