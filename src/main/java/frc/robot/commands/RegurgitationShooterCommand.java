package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ArmSubsystem;

public class RegurgitationShooterCommand extends Command {
    private final ShooterSubsystem m_shooterSubsystem;
    private final ArmSubsystem m_armSubsystem;

    public RegurgitationShooterCommand(ShooterSubsystem shooterSubsystem,ArmSubsystem armSubsystem){
        m_shooterSubsystem = shooterSubsystem;
        m_armSubsystem =  armSubsystem;
        addRequirements(shooterSubsystem, armSubsystem);

    }

    @Override
    public void execute(){
        m_shooterSubsystem.indexerToShooter(1);
        m_shooterSubsystem.shoot(-1);
    }
    @Override
    public void end(boolean interrupted){
        m_shooterSubsystem.shoot(0.0);
        m_armSubsystem.moveToPosition(0);
        m_shooterSubsystem.indexerToShooter(0);
    }
}