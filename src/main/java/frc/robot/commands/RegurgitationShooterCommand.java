package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RegurgitationShooterCommand extends Command {
    private final ShooterSubsystem m_shooterSubsystem;

    public RegurgitationShooterCommand(ShooterSubsystem shooterSubsystem){
        m_shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void execute(){
        m_shooterSubsystem.percentOutput(-1);
    }
    @Override
    public void end(boolean interrupted){
        m_shooterSubsystem.percentOutput(0.0);
    }
}