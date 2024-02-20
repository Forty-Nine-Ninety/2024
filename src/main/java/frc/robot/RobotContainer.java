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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveSettings;
import frc.robot.Constants.Ports;
import frc.robot.JoystickF310.*;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import java.io.File;

import com.pathplanner.lib.auto.AutoBuilder;

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
  private final ArmSubsystem m_arm = new ArmSubsystem();
   private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final ShooterSubsystem m_shooter = new ShooterSubsystem();

  private final ArmNeutralCommand m_armNeutralCommand = new ArmNeutralCommand(m_arm);
  private final ArmAmpCommand m_armAmpCommand = new ArmAmpCommand(m_arm);
  private final ArmSpeakerCommand m_armSpeakerCommand = new ArmSpeakerCommand(m_arm);
  private final OuttakeAmpCommand m_outtakeAmpCommand = new OuttakeAmpCommand(m_shooter);
  private final OuttakeOfIntakeCommand m_outtakeOfIntakeCommand = new OuttakeOfIntakeCommand(m_intake);
  private final OuttakeSpeakerCommand m_outtakeSpeakerCommand = new OuttakeSpeakerCommand(m_shooter);
 // private final IntakeExtendCommand m_intakeExtendCommand = new IntakeExtendCommand(m_intake); // new (temp comment)
  // private final NoteProcessingCommand m_noteProcessingCommand = new NoteProcessingCommand(m_intake, m_indexer);
  private final DriveCommand m_driveCommand = new DriveCommand(m_drivebase);
  private final DriveSimulationCommand m_driveSimulationCommand = new DriveSimulationCommand(m_drivebase);
  //AUTO
  private final SendableChooser<Command> autoChooser;

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
        () -> -MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), DriveSettings.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftX), DriveSettings.LEFT_X_DEADBAND),
        () -> -joystickDrive.getRawAxis(AxisF310.JoystickRightX),
        () -> -joystickDrive.getRawAxis(AxisF310.JoystickRightY));

    Command driveFieldOrientedDirectAngleSim = m_drivebase.simDriveCommand(
        () -> -MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), DriveSettings.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftX), DriveSettings.LEFT_X_DEADBAND),
        () -> -joystickDrive.getRawAxis(2));

    m_drivebase.setDefaultCommand(
        !RobotBase.isSimulation() ? driveFieldOrientedDirectAngle : driveFieldOrientedDirectAngleSim);
    
    // AUTO- SmartDashboard
    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);
    SmartDashboard.putData("Exit 1", autoChooser);
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

    joystickDrive.getButton(ButtonF310.A).onTrue((Commands.runOnce(m_drivebase::zeroGyro)));
    //joystickDrive.getButton(ButtonF310.B).onTrue(Commands.runOnce(m_drivebase::addFakeVisionReading));
    //joystickDrive.getButton(ButtonF310.X).whileTrue(
        //Commands.deferredProxy(() -> m_drivebase.driveToPose(
                                   //new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
                              //));
      //  joystickDrive.getButton(ButtonF310.Y).whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
    joystickOperator.getButton(ButtonF310.A).onTrue(m_armNeutralCommand);
    joystickOperator.getButton(ButtonF310.B).onTrue(m_armAmpCommand);
    joystickOperator.getButton(ButtonF310.Y).onTrue(m_armSpeakerCommand);
    joystickOperator.getButton(ButtonF310.BumperLeft).onTrue(m_outtakeAmpCommand);
    joystickOperator.getButton(ButtonF310.BumperRight).onTrue(m_outtakeOfIntakeCommand);
    joystickOperator.getButton(ButtonF310.Back).onTrue(m_outtakeSpeakerCommand);
    //joystickOperator.getButton(ButtonF310.X).onTrue(m_intakeExtendCommand);
    //joystickOperator.getButton(POVF310.Top).onTrue(m_indexerCommand);
   // joystickOperator.getButton(POVF310.Bottom).onTrue(m_noteProcessingCommand);
  }
  
  public void setTeleopDefaultCommands()
  {
    // CommandScheduler.getInstance().setDefaultCommand(m_drivebase, !RobotBase.isSimulation() ? m_driveCommand : m_driveSimulationCommand);
    CommandScheduler.getInstance().setDefaultCommand(m_arm, m_armNeutralCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return m_drivebase.getAutonomousCommand("New Auto");
    //return autoChooser.getSelected();
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