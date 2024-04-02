package frc.robot.commands;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

public class ShootEverythingCommand extends Command {
    private final ShooterSubsystem m_shooter_subsystem;
    public ShootEverythingCommand(ShooterSubsystem shooter){
            m_shooter_subsystem = shooter;
            addRequirements(shooter);
    }

    @Override
    public void execute(){
        m_shooter_subsystem.indexerToShooter(-1.0);
        m_shooter_subsystem.shoot(1.0);

    }

    @Override
    public void end(boolean interrupted){
        m_shooter_subsystem.indexerToShooter(0.0);
        m_shooter_subsystem.shoot(0.0);

    }

}