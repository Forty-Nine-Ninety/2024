// Shreyans

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ArmSubsystem;

public class OuttakeCommand extends Command {

    private final ShooterSubsystem shooter_subsystem;
  //  private final ArmSubsystem arm_subsystem; 
    private double speed = 1.0; 

    public OuttakeCommand(ShooterSubsystem shooter_subsystem) {
      //  this.arm_subsystem = arm_subsystem;
        this.shooter_subsystem = shooter_subsystem;
        addRequirements(shooter_subsystem);
      //  addRequirements(arm_subsystem);
    }

    @Override
    public void initialize() {
        shooter_subsystem.shoot(speed);
        
    }

    @Override
    public void end(boolean interrupted){
        shooter_subsystem.stop();
        //arm_subsystem.moveToPosition(0);
    }
} 