// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveSettings;
import frc.robot.Constants.Ports;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.commands.Auto.*;

import java.io.File;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{
    CommandXboxController joystickDrive = new CommandXboxController(Ports.PORT_JOYSTICK_DRIVE);
    CommandXboxController joystickOperator = new CommandXboxController(Ports.PORT_JOYSTICK_OPERATOR);
    //XboxController joystickOperator = new XboxController(Ports.PORT_JOYSTICK_OPERATOR);

    // The robot's subsystems and commands are defined here...
    //Subsystems
    private final SwerveSubsystem m_drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
    private final ArmSubsystem m_arm = new ArmSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    //Commands
    private final DriveCommand m_driveCommand = new DriveCommand(m_drivebase);
    private final Drive2Command m_drive2Command = new Drive2Command(m_drivebase);
    private final DriveSimulationCommand m_driveSimulationCommand = new DriveSimulationCommand(m_drivebase);
    private final ArmNeutralCommand m_armNeutralCommand = new ArmNeutralCommand(m_arm);
    private final ArmAmpCommand m_armAmpCommand = new ArmAmpCommand(m_arm);
    private final ArmManualCommand m_armManualCommand = new ArmManualCommand(m_arm);
    private final ArmSpeakerCommand m_armSpeakerCommand = new ArmSpeakerCommand(m_arm);
    private final ArmFerryCommand m_armFerryCommand = new ArmFerryCommand(m_arm);
    private final ArmFerryBackupCommand m_armFerryBackupCommand = new ArmFerryBackupCommand(m_arm);
    private final OuttakeCommand m_outtakeCommand = new OuttakeCommand(m_shooter,m_arm);
    private final RegurgitationCommand m_regurgCommand = new RegurgitationCommand(m_intake, m_shooter);
    private final RegurgitationShooterCommand m_regurgShooterCommand = new RegurgitationShooterCommand(m_shooter,m_arm);
    //private final StopRollerCommand m_stopRollerCommand = new StopRollerCommand(m_intake, m_shooter);
    private final IntakeExtendCommand m_intakeExtendCommand = new IntakeExtendCommand(m_intake, m_shooter);
    private final RumbleCommand m_rumbleCommand = new RumbleCommand(joystickDrive);
    private final RumbleCommandHelper m_rumbleCommandHelper = new RumbleCommandHelper(joystickDrive);
    private final ChainEndgameCommand m_chainEndgameCommand = new ChainEndgameCommand(m_arm);
    //Auto
    private SendableChooser<Command> m_autoChooser = new SendableChooser<>();
    private final AutoSpeakerCommand m_autoSpeakerCommand = new AutoSpeakerCommand(m_shooter,m_arm);
    private final AutoAmpCommand m_autoAmpCommand = new AutoAmpCommand(m_shooter,m_arm);
    //private final AutoCommand m_autoCommand = new AutoCommand(m_arm,m_shooter,m_drivebase,"11NBlue");

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer()
    {
        // Configure the trigger bindings
        configureBindings();
        
        NamedCommands.registerCommand("Speaker",new AutoSpeakerCommand(m_shooter,m_arm));
        NamedCommands.registerCommand("Amp",new AutoAmpCommand(m_shooter,m_arm));
        NamedCommands.registerCommand("Intake",new IntakeExtendCommand(m_intake,m_shooter));

        // Auto - SmartDashboard
        m_autoChooser.setDefaultOption("Red 1 Exit", m_drivebase.getAutonomousCommand("1ExitRed"));
        m_autoChooser.addOption("Red 3 Exit", m_drivebase.getAutonomousCommand("3ExitRed"));
        m_autoChooser.addOption("Blue 1 Exit", m_drivebase.getAutonomousCommand("1ExitBlue"));
        m_autoChooser.addOption("Blue 3 Exit", m_drivebase.getAutonomousCommand("3ExitBlue"));
        m_autoChooser.addOption("Red 1 One Note", new Auto11NRedCommand(m_drivebase,m_arm,m_shooter));
        m_autoChooser.addOption("Red 2 One Note", new Auto21NRedCommand(m_drivebase,m_arm,m_shooter));
        m_autoChooser.addOption("Red 3 One Note", new Auto31NRedCommand(m_drivebase,m_arm,m_shooter));
        m_autoChooser.addOption("Blue 1 One Note", new Auto11NBlueCommand(m_drivebase,m_arm,m_shooter));
        m_autoChooser.addOption("Blue 2 One Note", new Auto21NBlueCommand(m_drivebase,m_arm,m_shooter));
        m_autoChooser.addOption("Blue 3 One Note", new Auto31NBlueCommand(m_drivebase,m_arm,m_shooter));
        //m_autoChooser.addOption("Red 2 Two Note", new Auto22NRedCommand(m_drivebase,m_arm,m_shooter,m_intake));
        //m_autoChooser.addOption("Blue 2 Two Note", new Auto22NBlueCommand(m_drivebase,m_arm,m_shooter,m_intake));
        Shuffleboard.getTab("Auto Choose").add("Choose Auto Path", m_autoChooser);
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
        //XBOX
        m_driveCommand.setSuppliers(
            () -> MathUtil.applyDeadband(joystickDrive.getLeftY(), DriveSettings.LEFT_Y_DEADBAND),
            () -> MathUtil.applyDeadband(joystickDrive.getLeftX(), DriveSettings.LEFT_X_DEADBAND),
            () -> joystickDrive.getRightX(),
            () -> joystickDrive.getRightY()
        );

        m_drive2Command.setSuppliers(
            () -> MathUtil.applyDeadband(joystickDrive.getLeftY(), DriveSettings.LEFT_Y_DEADBAND),
            () -> MathUtil.applyDeadband(joystickDrive.getLeftX(), DriveSettings.LEFT_X_DEADBAND),
            () -> MathUtil.applyDeadband(joystickDrive.getRightX(), DriveSettings.RIGHT_X_DEADBAND)
        );

        m_driveSimulationCommand.setSuppliers(
            () -> MathUtil.applyDeadband(joystickDrive.getLeftY(), DriveSettings.LEFT_Y_DEADBAND),
            () -> MathUtil.applyDeadband(joystickDrive.getLeftX(), DriveSettings.LEFT_X_DEADBAND),
            () -> joystickDrive.getRawAxis(2)
        );

        joystickDrive.a().onTrue(Commands.runOnce(m_drivebase::zeroGyro));
        joystickDrive.leftBumper().toggleOnTrue(m_intakeExtendCommand);
        joystickDrive.rightBumper().toggleOnTrue(m_outtakeCommand);

        //OPERATOR
        m_armManualCommand.setSuppliers(
            () -> MathUtil.applyDeadband(DriveUtil.powCopySign(joystickOperator.getLeftY(), 1), DriveSettings.ARM_DEADBAND)      
        );

        joystickOperator.y().onTrue(m_armSpeakerCommand);
        joystickOperator.b().onTrue(m_armAmpCommand);
        joystickOperator.a().onTrue(m_armNeutralCommand);
        joystickOperator.x().onTrue(m_chainEndgameCommand);
        joystickOperator.povRight().onTrue(m_armFerryCommand);
        joystickOperator.povLeft().onTrue(m_armFerryBackupCommand);

        joystickOperator.leftBumper().toggleOnTrue(m_intakeExtendCommand); //onTrue?
        joystickOperator.rightBumper().toggleOnTrue(m_outtakeCommand);

        joystickOperator.rightStick().onTrue(m_armManualCommand);
        


        joystickOperator.povUp().toggleOnTrue(m_regurgShooterCommand);
        joystickOperator.povDown().toggleOnTrue(m_regurgCommand);

        if (m_shooter.breakBeam()){
            new RumbleCommand(joystickDrive);
        } 
    }

    public void setTeleopDefaultCommands()
    {
        CommandScheduler.getInstance().setDefaultCommand(m_drivebase, !RobotBase.isSimulation() ? m_drive2Command : m_driveSimulationCommand);
        CommandScheduler.getInstance().setDefaultCommand(m_arm, m_armNeutralCommand);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand(){
        /*List<PathPlannerPath> path = PathPlannerAuto.getPathGroupFromAutoFile(pathname);
        // defining variables used in thingy
        path.put("Speaker", new AutoSpeakerCommand(m_shooter,m_arm));
        eventMap.put("Amp", new AutoAmpCommand(m_shooter,m_arm)); // matches x button*/

        //return m_drivebase.getAutonomousCommand("1ExitRed");
        //return m_drivebase.getAutonomousCommand("3ExitRed");
        //return m_drivebase.getAutonomousCommand("1ExitBlue");
        //return m_drivebase.getAutonomousCommand("3ExitBlue");
        //return new Auto11NRedCommand(m_drivebase,m_arm,m_shooter);
        //return new Auto21NRedCommand(m_drivebase,m_arm,m_shooter);
        //return new Auto31NRedCommand(m_drivebase,m_arm,m_shooter);
        //return new Auto11NBlueCommand(m_drivebase,m_arm,m_shooter);
        //return new Auto21NBlueCommand(m_drivebase,m_arm,m_shooter);
        //return new Auto31NBlueCommand(m_drivebase,m_arm,m_shooter);
        return m_autoChooser.getSelected();
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