package frc.robot.commands.Auto;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.ArmNeutralCommand;
import frc.robot.commands.ArmSpeakerCommand;
import frc.robot.commands.ShootEverythingCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoSpeakerCommand extends SequentialCommandGroup{
    private final ShooterSubsystem m_shooter;
    private final ArmSubsystem m_arm;

    public AutoSpeakerCommand(ShooterSubsystem shooter, ArmSubsystem arm) {
        m_shooter = shooter;
        m_arm = arm;
        addCommands(new ArmSpeakerCommand(m_arm).withTimeout(1),
                    new ParallelRaceGroup(new WaitCommand(0.85),
                                          new ShooterCommand(m_shooter)
                                         ),
                    new ParallelRaceGroup(new WaitCommand(1.2),
                                          new ShootEverythingCommand(m_shooter)
                                         ),
                    new ArmNeutralCommand(m_arm).withTimeout(1.0)
        );
    }
}
