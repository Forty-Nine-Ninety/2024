package frc.robot.commands.Auto;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.ArmAmpCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoAmpCommand extends SequentialCommandGroup{
    private final ShooterSubsystem shooter_subsystem;
    private final ArmSubsystem arm_subsystem;

    public AutoAmpCommand(ShooterSubsystem shooter_subsystem,ArmSubsystem arm_subsystem) {
      this.shooter_subsystem = shooter_subsystem;
      this.arm_subsystem = arm_subsystem;
      addCommands(new SequentialCommandGroup(new ArmAmpCommand(arm_subsystem),
                                             new ParallelCommandGroup(new WaitCommand(1.0),
                                                                      new OuttakeCommand(shooter_subsystem)
                                                                     )
                                            )
      );
    }
}
