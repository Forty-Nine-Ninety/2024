package frc.robot.commands.Auto;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.ArmAmpCommand;
import frc.robot.commands.ArmNeutralCommand;
import frc.robot.commands.ArmSpeakerCommand;
import frc.robot.commands.ShootEverythingCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoAmpCommand extends SequentialCommandGroup{
    private final ShooterSubsystem m_shooter;
    private final ArmSubsystem m_arm;

    public AutoAmpCommand(ShooterSubsystem shooter, ArmSubsystem arm) {
        m_shooter = shooter;
        m_arm = arm;
        addCommands(new ArmAmpCommand(m_arm).withTimeout(2.5),
                    new ShootEverythingCommand(m_shooter).withTimeout(1),
                    new ArmNeutralCommand(m_arm).withTimeout(1.0)
        );
    }
}
