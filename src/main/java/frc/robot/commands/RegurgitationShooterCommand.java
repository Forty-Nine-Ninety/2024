package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ArmSubsystem;

public class RegurgitationShooterCommand extends Command {
    private final ShooterSubsystem m_shooter;
    private final ArmSubsystem m_arm;

    public RegurgitationShooterCommand(ShooterSubsystem shooter, ArmSubsystem arm){
        m_shooter = shooter;
        m_arm = arm;
        addRequirements(shooter, arm);

    }

    @Override
    public void execute(){
        m_shooter.preshoot(-0.4);
        m_shooter.shoot(0.2);
    }
    @Override
    public void end(boolean interrupted){
        m_shooter.shoot(0.0);
        m_arm.moveToPosition(0);
        m_shooter.preshoot(0);
    }
}