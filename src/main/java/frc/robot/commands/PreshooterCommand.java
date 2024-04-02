package frc.robot.commands;
import frc.robot.subsystems.*;


import edu.wpi.first.wpilibj2.command.Command;

public class PreshooterCommand extends Command{
    private final ShooterSubsystem shooter_subsystem;
    public PreshooterCommand(ShooterSubsystem shooter){
        this.shooter_subsystem = shooter;
        addRequirements(shooter);

    }

    @Override
    public void initialize(){
        shooter_subsystem.indexerToShooter(-1.0);
    }

    @Override
    public void execute(){
        shooter_subsystem.indexerToShooter(-1.0);
    }

    @Override
    public void end(boolean interrupted){
        shooter_subsystem.indexerToShooter(0.0);
    }

}
