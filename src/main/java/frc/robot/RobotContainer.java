// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveSettings;
import frc.robot.Constants.Ports;
import frc.robot.JoystickF310.*;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import java.io.File;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{
  JoystickF310 joystickDrive = new JoystickF310(Ports.PORT_JOYSTICK_DRIVE);
  JoystickF310 joystickOperator = new JoystickF310(Ports.PORT_JOYSTICK_OPERATOR);
  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem m_drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve"));

  private final DriveCommand m_driveCommand = new DriveCommand(m_drivebase);
  private final DriveSimulationCommand m_driveSimulationCommand = new DriveSimulationCommand(m_drivebase);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation
    Command driveFieldOrientedDirectAngle = m_drivebase.driveCommand(
        () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), DriveSettings.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftX), DriveSettings.LEFT_X_DEADBAND),
        () -> joystickDrive.getRawAxis(AxisF310.JoystickRightX),
        () -> joystickDrive.getRawAxis(AxisF310.JoystickRightY));

    Command driveFieldOrientedDirectAngleSim = m_drivebase.simDriveCommand(
        () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), DriveSettings.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftX), DriveSettings.LEFT_X_DEADBAND),
        () -> joystickDrive.getRawAxis(2));

    m_drivebase.setDefaultCommand(
        !RobotBase.isSimulation() ? driveFieldOrientedDirectAngle : driveFieldOrientedDirectAngleSim);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    m_driveCommand.setSuppliers(
      () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), DriveSettings.LEFT_Y_DEADBAND),
      () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftX), DriveSettings.LEFT_X_DEADBAND),
      () -> joystickDrive.getRawAxis(AxisF310.JoystickRightX),
      () -> joystickDrive.getRawAxis(AxisF310.JoystickRightY)
    );

    m_driveSimulationCommand.setSuppliers(
      () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), DriveSettings.LEFT_Y_DEADBAND),
      () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftX), DriveSettings.LEFT_X_DEADBAND),
      () -> joystickDrive.getRawAxis(2)
    );

    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    joystickDrive.getButton(ButtonF310.A).onTrue((new InstantCommand(m_drivebase::zeroGyro)));
    joystickDrive.getButton(ButtonF310.B).onTrue(new InstantCommand(m_drivebase::addFakeVisionReading));
    joystickDrive.getButton(ButtonF310.X).whileTrue(
        Commands.deferredProxy(() -> m_drivebase.driveToPose(
                                   new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
                              ));

    //  joystickDrive.getButton(ButtonF310.X).whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));
  }
  
  public void setTeleopDefaultCommands()
  {
    // CommandScheduler.getInstance().setDefaultCommand(m_drivebase, !RobotBase.isSimulation() ? m_driveCommand : m_driveSimulationCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return m_drivebase.getAutonomousCommand("New Path", true);
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    m_drivebase.setMotorBrake(brake);
  }
}