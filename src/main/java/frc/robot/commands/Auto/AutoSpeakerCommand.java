package frc.robot.commands.Auto;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
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
        addRequirements(m_shooter,m_arm);
        addCommands(new SequentialCommandGroup(new ArmSpeakerCommand(m_arm),
                                               new ParallelCommandGroup(new WaitCommand(1.0),
                                                                        new OuttakeCommand(m_shooter,m_arm)
                                                                       ),
                                               new ArmNeutralCommand(m_arm)
                                              )
        );
    }
}
