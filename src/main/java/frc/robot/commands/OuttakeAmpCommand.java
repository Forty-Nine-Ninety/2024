// Shreyans (waiting for @Chloe to finish Shooter Subsystem)
// Chloe's tagging along too because why not 

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class OuttakeAmpCommand extends Command {

    private final ShooterSubsystem shooter_subsystem; 
    private double speed = 1; //update this later, will be different 

    public OuttakeAmpCommand(ShooterSubsystem shooter_subsystem) {
        this.shooter_subsystem = shooter_subsystem;
        addRequirements(shooter_subsystem);
    }

    @Override
    public void initialize() {
        shooter_subsystem.percentOutput(speed);
    }
}