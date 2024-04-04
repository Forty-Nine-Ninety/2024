package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

public class Auto11NBlueCommand extends SequentialCommandGroup{
    private final SwerveSubsystem m_drivebase;
    private final ArmSubsystem m_arm;
    private final ShooterSubsystem m_shooter;

    public Auto11NBlueCommand(SwerveSubsystem drivebase, ArmSubsystem arm, ShooterSubsystem shooter) {
        m_drivebase = drivebase;
        m_arm = arm;
        m_shooter = shooter;
        addCommands(new AutoSpeakerCommand(m_shooter, m_arm),
                    new WaitCommand(0),
                    m_drivebase.getAutonomousCommand("11NBlue")
        );

    }

    /*public SequentialCommandGroup getAutonomousCommand() {
        // Create voltage constraint to ensure we don't accelerate too fast
        /*var autoVoltageConstraint = 
            new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(
                    MotionControl.ksVolts,
                    MotionControl.kvVoltSecondsPerMeter),
                MotionControl.kDriveKinematics,
                SubsystemConfig.AUTO_MAX_VOLTAGE);*/
        
        // This will load the file "Example Path.path" and generate it with a max velocity of 4 m/s and a max acceleration of 3 m/s^2
        /*PathPlannerAuto path = new PathPlannerAuto("Auto11NBlue");
        path.put("Note 1",new AutoSpeakerCommand(arm_subsystem,shooter_subsystem))
        List<PathPlannerPath> pathGroup = PathPlannerAuto.getPathGroupFromAutoFile("Auto11NBlue");
        //PathPlannerTrajectory path11NBlueAuto = new PathPlannerTrajectory("")
        PathPlannerPath path11NBlueAuto = new PathPlannerPath​(List<Translation2d> bezierPoints,
                                                            List<RotationTarget> holonomicRotations,
                                                            List<ConstraintsZone> constraintZones,
                                                            List<EventMarker> eventMarkers,
                                                            PathConstraints globalConstraints,
                                                            GoalEndState (0.0,0.0),
                                                            boolean reversed,
                                                            -120.24);
    }*/
}
