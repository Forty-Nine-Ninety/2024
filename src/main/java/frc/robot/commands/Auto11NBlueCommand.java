package frc.robot.commands;

import java.util.HashMap;
import java.util.List;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.FollowPathWithEvents;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.*;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.Constants.*;

public class Auto11NBlueCommand extends Command{
    /*private final SwerveSubsystem m_drivetrain;
    private final ArmSubsystem m_arm;
    private final ShooterSubsystem m_shooter;
    private static AutoCommand autoCommand;

    public Auto11NBlueCommand(SwerveSubsystem drivetrain,ArmSubsystem arm,ShooterSubsystem shooter) {
        m_drivetrain = drivetrain;
        m_arm = arm;
        m_shooter = shooter;
        autoCommand = new AutoCommand(arm,shooter);

    }

    public static SequentialCommandGroup getAutonomousCommand() {
        // Create voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint = 
            new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(
                    MotionControl.ksVolts,
                    MotionControl.kvVoltSecondsPerMeter),
                MotionControl.kDriveKinematics,
                SubsystemConfig.AUTO_MAX_VOLTAGE);
        
        // This will load the file "Example Path.path" and generate it with a max velocity of 4 m/s and a max acceleration of 3 m/s^2
        PathPlannerAuto path = new PathPlannerAuto("Auto11NBlue");
        List<PathPlannerPath> pathGroup = PathPlannerAuto.getPathGroupFromAutoFile("Auto11NBlue");
        //PathPlannerTrajectory path11NBlueAuto = new PathPlannerTrajectory("")
        /*PathPlannerPath path11NBlueAuto = new PathPlannerPath​(/*List<Translation2d> bezierPoints,
                                                            List<RotationTarget> holonomicRotations,
                                                            List<ConstraintsZone> constraintZones,
                                                            List<EventMarker> eventMarkers,
                                                            PathConstraints globalConstraints,
                                                            GoalEndState (0.0,0.0),
                                                            boolean reversed,
                                                            -120.24);*/
        
        // This is just an example event map. It would be better to have a constant, global event map
        // in your code that will be used by all path following commands.
        /*HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("Note 1", autoCommand.speaker()); //cannot make a static reference to a non-static something (and: void autoCommand?)
        FollowPathWithEvents command = new FollowPathWithEvents(
            PathPlannerAuto.getPathGroupFromAutoFile("11NBlue.auto"),
            getPathFollowingCommand(path),
            //pathGroup11NBlueAuto.get(0).getEventMarkers(),
            eventMap
        );
        return 
    }*/
}
