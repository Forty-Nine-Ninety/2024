// Shreyans

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class OuttakeCommand extends ParallelCommandGroup{
    private final ShooterSubsystem shooter_subsystem;

    public OuttakeCommand(ShooterSubsystem shooter_subsystem) {
      this.shooter_subsystem = shooter_subsystem;
      addCommands(new ParallelCommandGroup(new ShooterCommand(shooter_subsystem),
                                           new SequentialCommandGroup(new WaitCommand(1.0),
                                                                      new PreshooterCommand(shooter_subsystem)
                                                                     )
                                          )
      );
    }
}