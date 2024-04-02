package frc.robot.commands;
import frc.robot.subsystems.*;


import edu.wpi.first.wpilibj2.command.Command;

public class ShooterCommand extends Command{
    private final ShooterSubsystem shooter_subsystem;
    public ShooterCommand(ShooterSubsystem shooter){
        this.shooter_subsystem = shooter;
    }

    @Override
    public void initialize(){
        shooter_subsystem.shoot(1.0);
    }

    @Override
    public void execute(){
        shooter_subsystem.shoot(1.0);
    }

    @Override
    public void end(boolean interrupted){
        shooter_subsystem.shoot(0.0);
    }

}
