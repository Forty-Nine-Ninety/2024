/*package frc.robot.commands.Auto;

import java.util.HashMap;
import java.util.List;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerTrajectory;
import com.pathplanner.lib.auto.RamseteAutoBuilder;
import com.pathplanner.lib.commands.PathfindRamsete;
import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AutoLoadEventMapCommand extends InstantCommand {
  private final String auto;
  private final HashMap<String, Command> eventmap;
  private final SwerveSubsystem swerve_subsystem;

  public AutoLoadEventMapCommand(String auto, HashMap<String, Command> eventMap,SwerveSubsystem swerve_subsystem) {
    this.auto = auto;
    this.eventmap = eventMap;
    this.swerve_subsystem = swerve_subsystem;
  }

  @Override
  public void initialize() {
    //List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup(auto, new PathConstraints(2, 2));
    List<PathPlannerPath> pathGroup = PathPlannerAuto.getPathGroupFromAutoFile(auto);

    HashMap<String, Command> eventMap = eventmap;

    /*RamseteAutoBuilder autoBuilder = new RamseteAutoBuilder(
        RobotContainer.driveTrainSubsystem::getPose,
        RobotContainer.driveTrainSubsystem::resetOdometry,
        new RamseteController(),
        Constants.kDriveKinematics,
        RobotContainer.driveTrainSubsystem::DifferentialDriveVolts,
        eventMap,
        false,
        RobotContainer.driveTrainSubsystem);*/

    // RamseteAutoBuilder autoBuilder2 = new RamseteAutoBuilder(null, null, null,
    // null, null, null, null, null, eventMap, null)

    /*PathfindRamsete autoBuilder = new PathfindRamsete(
        swerve_subsystem::getPose,
        swerve_subsystem::resetOdometry,
        new RamseteController(),
        Constants.kDriveKinematics,
        swerve_subsystem::DifferentialDriveVolts,
        eventMap,
        false,
        swerve_subsystem);

    autoBuilder.execute(pathGroup).schedule();
  }
}*/