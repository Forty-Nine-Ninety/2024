package frc.robot.commands.Auto;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.ArmNeutralCommand;
import frc.robot.commands.ArmSpeakerCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoSpeakerCommand extends SequentialCommandGroup{
    private final ShooterSubsystem m_shooter;
    private final ArmSubsystem m_arm;

    public AutoSpeakerCommand(ShooterSubsystem shooter, ArmSubsystem arm) {
        m_shooter = shooter;
        m_arm = arm;
        addCommands(new ArmSpeakerCommand(m_arm),
                    new ParallelRaceGroup(new WaitCommand(1.2),
                                          new OuttakeCommand(m_shooter, m_arm)
                                         ),
                    new ArmNeutralCommand(m_arm)
        );
    }
}
