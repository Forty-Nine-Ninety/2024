package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class OuttakeCommand extends SequentialCommandGroup{
    private final ShooterSubsystem m_shooter;
    private final ArmSubsystem m_arm;

    public OuttakeCommand(ShooterSubsystem shooter,ArmSubsystem arm) {
        m_shooter = shooter;
        m_arm = arm;
        addRequirements(shooter);
        addCommands(
            new ParallelRaceGroup(
                new WaitCommand(1.5),
                new ParallelRaceGroup(
                    new WaitCommand(0.85), 
                    new ShooterCommand(m_shooter)
                    ),
                new ShootEverythingCommand(m_shooter)
            ),
            new ArmNeutralCommand(m_arm)

        );
    }
}