package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ArmSubsystem;

public class RegurgitationShooterCommand extends Command {
    private final ShooterSubsystem m_shooterSubsystem;
    private final ArmSubsystem m_armSubsystem;

    public RegurgitationShooterCommand(ShooterSubsystem m_shooterSubsystem,ArmSubsystem m_armSubsystem){
        this.m_shooterSubsystem = m_shooterSubsystem;
        this.m_armSubsystem = m_armSubsystem;
    }

    @Override
    public void execute(){
        m_shooterSubsystem.percentOutput(-1);
    }
    @Override
    public void end(boolean interrupted){
        m_shooterSubsystem.percentOutput(0.0);
        m_armSubsystem.moveToPosition(0);
    }
}