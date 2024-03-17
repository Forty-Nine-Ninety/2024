package frc.robot.commands;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.*;

public class AutoCommand extends Command{
    private final ArmSubsystem arm;
    private final ShooterSubsystem shooter;
    private final ArmSpeakerCommand m_armSpeakerCommand;
    private final ArmNeutralCommand m_armNeutralCommand;
    private final OuttakeCommand m_shootCommand;
    
    public AutoCommand(ArmSubsystem arm,ShooterSubsystem shooter){
        this.arm = arm;
        m_armSpeakerCommand = new ArmSpeakerCommand(arm);
        m_armNeutralCommand = new ArmNeutralCommand(arm);
        this.shooter = shooter;
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
}
