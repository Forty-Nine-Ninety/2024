

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
    }

    @Override
    public void initialize(){
        m_intakeSubsystem.extendIntake();
    }

    @Override
    public void execute(){
        m_intakeSubsystem.intakePercentOutput(1);
        m_shooterSubsystem.percentOutput(1);
        if(m_shooterSubsystem.breakBeam()){
            m_shooterSubsystem.percentOutput(0.0);
            m_intakeSubsystem.intakePercentOutput(0);
            m_intakeSubsystem.retractIntake();
        }
    }

    @Override
    public void end(boolean interrupted){
        m_shooterSubsystem.percentOutput(0.0);
        m_intakeSubsystem.intakePercentOutput(0);
        m_intakeSubsystem.retractIntake();
    }
}
