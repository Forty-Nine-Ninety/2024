/*package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeExtendCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

public class Auto22NBlueCommand extends SequentialCommandGroup{
    private final SwerveSubsystem m_drivebase;
    private final ArmSubsystem m_arm;
    private final ShooterSubsystem m_shooter;
    private final IntakeSubsystem m_intake;

    public Auto22NBlueCommand(SwerveSubsystem drivebase, ArmSubsystem arm, ShooterSubsystem shooter,IntakeSubsystem intake) {
        m_drivebase = drivebase;
        m_arm = arm;
        m_shooter = shooter;
        m_intake = intake;
        addCommands(new AutoSpeakerCommand(m_shooter, m_arm).withTimeout(4.05),
                    new WaitCommand(0),
                    new ParallelRaceGroup(new IntakeExtendCommand(m_intake,m_shooter).withTimeout(5),
                                          m_drivebase.getAutonomousCommand("22NBlueA")),
                    m_drivebase.getAutonomousCommand("22NBlueB"),
                    new AutoSpeakerCommand(m_shooter, m_arm).withTimeout(4.05),
                    m_drivebase.getAutonomousCommand("22NBlueC")
                    

        );
    }
}*/
