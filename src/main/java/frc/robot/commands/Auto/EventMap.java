package frc.robot.commands.Auto;

import java.util.HashMap;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.IntakeExtendCommand;
import frc.robot.subsystems.*;

public class EventMap {
    private static ArmSubsystem m_arm;
    private static ShooterSubsystem m_shooter;
    private static IntakeSubsystem m_intake;

    public EventMap(ArmSubsystem arm, ShooterSubsystem shooter, IntakeSubsystem intake) {
        EventMap.m_arm = arm;
        EventMap.m_shooter = shooter;
        EventMap.m_intake = intake;
    }

    public static HashMap<String, Command> ScoreGrab() {
        HashMap<String, Command> ScoreGrab = new HashMap<>();
        ScoreGrab.put("Shooter", new AutoSpeakerCommand(m_shooter, m_arm));
        ScoreGrab.put("Amp",new AutoAmpCommand(m_shooter, m_arm));
        ScoreGrab.put("Intake", new IntakeExtendCommand(m_intake, m_shooter));
        return ScoreGrab;
    }
}