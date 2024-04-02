package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class OuttakeCommand extends SequentialCommandGroup{
    private final ShooterSubsystem m_shooter_subsystem;

    public OuttakeCommand(ShooterSubsystem shooter_subsystem) {
        m_shooter_subsystem = shooter_subsystem;
        addRequirements(shooter_subsystem);
        addCommands(
            new ParallelRaceGroup(
                new WaitCommand(1.0), 
                new ShooterCommand(m_shooter_subsystem)
                ),
            new ShootEverythingCommand(m_shooter_subsystem)
        );
    }
}