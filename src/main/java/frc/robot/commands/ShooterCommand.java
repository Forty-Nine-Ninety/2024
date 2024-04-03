package frc.robot.commands;
import frc.robot.subsystems.*;


import edu.wpi.first.wpilibj2.command.Command;

public class ShooterCommand extends Command{
    private final ShooterSubsystem m_shooter;
    public ShooterCommand(ShooterSubsystem shooter){
        m_shooter = shooter;
        addRequirements(shooter);

    }

    @Override
    public void initialize(){
        m_shooter.shoot(1.0);
    }

    @Override
    public void execute(){
        m_shooter.shoot(1.0);
    }

    @Override
    public void end(boolean interrupted){
        m_shooter.shoot(0.0);
    }

}
