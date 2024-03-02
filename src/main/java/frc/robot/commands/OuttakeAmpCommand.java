// Shreyans (waiting for @Chloe to finish Shooter Subsystem)
// Shreyans carried Chloe

/*ackage frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ArmSubsystem;

public class OuttakeAmpCommand extends Command {

    private final ShooterSubsystem shooter_subsystem;
    private final ArmSubsystem arm_subsystem; 
    private double speed = 1;

    public OuttakeAmpCommand(ShooterSubsystem shooter_subsystem,ArmSubsystem arm_subsystem) {
        this.shooter_subsystem = shooter_subsystem;
        this.arm_subsystem = arm_subsystem;
        addRequirements(shooter_subsystem);
    }

    @Override
    public void initialize() {
        shooter_subsystem.percentOutput(speed);
    }

    @Override
    public void end(boolean interrupted){
        shooter_subsystem.percentOutput(0);
        arm_subsystem.moveToPosition(0);
    }

}*/