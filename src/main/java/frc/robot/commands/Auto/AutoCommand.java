/*package frc.robot.commands.Auto;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ArmNeutralCommand;
import frc.robot.commands.ArmSpeakerCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.ZeroGyroCommand;
import frc.robot.subsystems.*;

public class AutoCommand extends Command{
    private final ArmSubsystem m_arm;
    private final ShooterSubsystem m_shooter;
    private final SwerveSubsystem m_drivebase;
    private final ZeroGyroCommand m_zeroGyroCommand;
    private final ArmSpeakerCommand m_armSpeakerCommand;
    private final ArmNeutralCommand m_armNeutralCommand;
    private final OuttakeCommand m_shootCommand;
    private final String m_exitpath;
    
    public AutoCommand(ArmSubsystem arm, ShooterSubsystem shooter, SwerveSubsystem drivebase, String m_path){
        m_drivebase = drivebase;
        m_zeroGyroCommand = new ZeroGyroCommand(m_drivebase);
        this.m_exitpath = m_path;
        m_arm = arm;
        m_armSpeakerCommand = new ArmSpeakerCommand(arm);
        m_armNeutralCommand = new ArmNeutralCommand(arm);
        m_shooter = shooter;
        m_shootCommand = new OuttakeCommand(shooter);
        addRequirements();
    }

    public Command speaker(){;
        SequentialCommandGroup shoot = new SequentialCommandGroup(m_shootCommand);
        shoot.withTimeout(2);

        SequentialCommandGroup speaker = new SequentialCommandGroup(m_armSpeakerCommand,
                                                                    shoot,
                                                                    m_armNeutralCommand);
        speaker.setName("Speaker Auto");
        return speaker;
    }
    public Command exit(){
        SequentialCommandGroup exit = new SequentialCommandGroup(m_drivebase.getAutonomousCommand(m_exitpath),
                                                                 m_zeroGyroCommand
                                                                );
        return exit;
    }


    //COMMANDS
    public Command Blue11NCommand(){
        SequentialCommandGroup Blue11N = new SequentialCommandGroup(speaker(),exit());
        return Blue11N;
    }
}*/
