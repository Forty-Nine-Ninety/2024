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
    //Subsystems
    private final SwerveSubsystem m_drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
    private final ArmSubsystem m_arm = new ArmSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    //Commands
    private final DriveCommand m_driveCommand = new DriveCommand(m_drivebase);
    private final DriveV2Command m_driveV2Command = new DriveV2Command(m_drivebase);
    private final DriveSimulationCommand m_driveSimulationCommand = new DriveSimulationCommand(m_drivebase);
    private final ArmNeutralCommand m_armNeutralCommand = new ArmNeutralCommand(m_arm);
    private final ArmAmpCommand m_armAmpCommand = new ArmAmpCommand(m_arm);
    private final ArmManualCommand m_armManualCommand = new ArmManualCommand(m_arm);
    private final ArmSpeakerCommand m_armSpeakerCommand = new ArmSpeakerCommand(m_arm);
    //private final OuttakeAmpCommand m_outtakeAmpCommand = new OuttakeAmpCommand(m_shooter);
    //private final OuttakeOfIntakeCommand m_outtakeOfIntakeCommand = new OuttakeOfIntakeCommand(m_intake, m_shooter);
    private final OuttakeSpeakerCommand m_outtakeSpeakerCommand = new OuttakeSpeakerCommand(m_shooter);
    private final RegurgitationCommand m_regurgCommand = new RegurgitationCommand(m_intake, m_shooter);
    private final RegurgitationShooterCommand m_regurgShooterCommand = new RegurgitationShooterCommand(m_shooter,m_arm);
    //private final StopRollerCommand m_stopRollerCommand = new StopRollerCommand(m_intake, m_shooter);
    private final IntakeExtendCommand m_intakeExtendCommand = new IntakeExtendCommand(m_intake,m_shooter);
    //private final IntakeToIndexerCommand m_intakeToIndexerCommand = new IntakeToIndexerCommand(m_intake);
    private final ChainEndgameCommand m_chainEndgameCommand = new ChainEndgameCommand(m_arm);
    //Auto
    private final SendableChooser<Command> autoChooser;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer()
    {
        // Configure the trigger bindings
        configureBindings();
        
        // Auto- SmartDashboard
        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser", autoChooser);
        SmartDashboard.putData("Exit 1", autoChooser);
        SmartDashboard.putData("Exit 3",autoChooser);
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

        m_driveV2Command.setSuppliers(
            () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), DriveSettings.LEFT_Y_DEADBAND),
            () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftX), DriveSettings.LEFT_X_DEADBAND),
            () -> joystickDrive.getRawAxis(AxisF310.JoystickRightX)
        );

        m_driveSimulationCommand.setSuppliers(
            () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftY), DriveSettings.LEFT_Y_DEADBAND),
            () -> MathUtil.applyDeadband(joystickDrive.getRawAxis(AxisF310.JoystickLeftX), DriveSettings.LEFT_X_DEADBAND),
            () -> joystickDrive.getRawAxis(2)
        );

        m_armManualCommand.setSuppliers(
            () -> DriveUtil.powCopySign(joystickOperator.getRawAxis(AxisF310.JoystickLeftY), 1)
        );

        /*m_eyebrowPositionCommand.setSuppliers(
            () -> DriveUtil.powCopySign(joystickOperator.getRawAxis(AxisF310.TriggerRight),1)
        );*/

        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

        joystickDrive.getButton(ButtonF310.A).onTrue((Commands.runOnce(m_drivebase::zeroGyro)));
        //joystickDrive.getButton(ButtonF310.B).onTrue(Commands.runOnce(m_drivebase::addFakeVisionReading));
        //joystickDrive.getButton(ButtonF310.X).whileTrue(
                //Commands.deferredProxy(() -> m_drivebase.driveToPose(
                                                                     //new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
                                                            //));
            //  joystickDrive.getButton(ButtonF310.Y).whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
        
        joystickOperator.getButton(POVF310.Top).onTrue(m_armSpeakerCommand);
        joystickOperator.getButton(POVF310.Right).toggleOnTrue(m_armAmpCommand); //test
        joystickOperator.getButton(POVF310.Bottom).onTrue(m_armNeutralCommand);
        joystickOperator.getButton(POVF310.Left).toggleOnTrue(m_intakeExtendCommand);
        joystickOperator.getButton(ButtonF310.B).toggleOnTrue(m_regurgCommand);
        joystickOperator.getButton(ButtonF310.Y).toggleOnTrue(m_regurgShooterCommand);
        //joystickOperator.getButton(ButtonF310.A).toggleOnTrue(m_intakeToIndexerCommand);
        joystickOperator.getButton(ButtonF310.A).onTrue(m_chainEndgameCommand);
        joystickOperator.getButton(ButtonF310.BumperRight).toggleOnTrue(m_outtakeSpeakerCommand);//OR OUTTAKE AMP COMMAND
        joystickOperator.getButton(ButtonF310.BumperLeft).onTrue(m_armManualCommand);
        /*joystickOperator.getButton(ButtonF310.BumperRight).onTrue(m_eyebrowPositionCommand); */
    }
    public void setTeleopDefaultCommands()
    {
        CommandScheduler.getInstance().setDefaultCommand(m_drivebase, !RobotBase.isSimulation() ? m_driveV2Command : m_driveSimulationCommand);
        CommandScheduler.getInstance().setDefaultCommand(m_arm, m_armNeutralCommand);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        return m_drivebase.getAutonomousCommand("1ExitRed");
        //return m_drivebase.getAutonomousCommand("3ExitRed");
        //return m_drivebase.getAutonomousCommand("1ExitBlue");
        //return m_drivebase.getAutonomousCommand("3ExitBlue");
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