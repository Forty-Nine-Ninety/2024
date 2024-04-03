package frc.robot.commands;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;

public class ShootEverythingCommand extends Command {
    private final ShooterSubsystem m_shooter;
    public ShootEverythingCommand(ShooterSubsystem shooter){
            m_shooter = shooter;
            addRequirements(shooter);
    }

    @Override
    public void execute(){
        m_shooter.preshoot(-1.0);
        m_shooter.shoot(1.0);

    }

    @Override
    public void end(boolean interrupted){
        m_shooter.preshoot(0.0);
        m_shooter.shoot(0.0);

    }

}