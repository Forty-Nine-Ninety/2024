// Shreyans

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class OuttakeSpeakerCommand extends Command {

    private final ShooterSubsystem shooter_subsystem; 
    private double speed = 1; 

    public OuttakeSpeakerCommand(ShooterSubsystem shooter_subsystem) {
        this.shooter_subsystem = shooter_subsystem;
        addRequirements(shooter_subsystem);
    }

    @Override
    public void initialize() {
        shooter_subsystem.percentOutput(speed);
    }

    @Override
    public void end(boolean interrupted){
        shooter_subsystem.percentOutput(0.0);
    }
} 