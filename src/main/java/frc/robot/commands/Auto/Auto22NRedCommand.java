/*package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeExtendCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

public class Auto22NRedCommand extends SequentialCommandGroup{
    private final SwerveSubsystem m_drivebase;
    private final ArmSubsystem m_arm;
    private final ShooterSubsystem m_shooter;
    private final IntakeSubsystem m_intake;

    public Auto22NRedCommand(SwerveSubsystem drivebase, ArmSubsystem arm, ShooterSubsystem shooter,IntakeSubsystem intake) {
        m_drivebase = drivebase;
        m_arm = arm;
        m_shooter = shooter;
        m_intake = intake;
        addCommands(new AutoSpeakerCommand(m_shooter, m_arm).withTimeout(4.05),
                    new WaitCommand(0),
                    new ParallelRaceGroup(new IntakeExtendCommand(m_intake,m_shooter).withTimeout(3),
                                          m_drivebase.getAutonomousCommand("22NRedA"))
                    m_drivebase.getAutonomousCommand("22NRedB"),
                    new AutoSpeakerCommand(m_shooter, m_arm).withTimeout(4.05),
                    m_drivebase.getAutonomousCommand("22NRedC")
                    

        );
    }
}*/
