package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class OuttakeCommand extends SequentialCommandGroup{
    private final ShooterSubsystem m_shooter;

    public OuttakeCommand(ShooterSubsystem shooter) {
        m_shooter = shooter;
        addRequirements(shooter);
        addCommands(
            new ParallelRaceGroup(
                new WaitCommand(1.0), 
                new ShooterCommand(m_shooter)
                ),
            new ShootEverythingCommand(m_shooter)
        );
    }
}