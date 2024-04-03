package frc.robot.commands.Auto;

import java.util.HashMap;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.IntakeExtendCommand;
import frc.robot.commands.Auto.AutoAmpCommand;
import frc.robot.commands.Auto.AutoSpeakerCommand;
import frc.robot.subsystems.*;

public class EventMap {
    private static ArmSubsystem arm_subsystem;
    private static ShooterSubsystem shooter_subsystem;
    private static IntakeSubsystem intake_subsystem;

    public EventMap(ArmSubsystem arm_subsystem,ShooterSubsystem shooter_subsystem,IntakeSubsystem intake_subsystem) {
        EventMap.arm_subsystem = arm_subsystem;
        EventMap.shooter_subsystem = shooter_subsystem;
        EventMap.intake_subsystem = intake_subsystem;
    }

  public static HashMap<String, Command> ScoreGrab() {
    HashMap<String, Command> ScoreGrab = new HashMap<>();
    ScoreGrab.put("Shooter", new AutoSpeakerCommand(shooter_subsystem,arm_subsystem));
    ScoreGrab.put("Amp",new AutoAmpCommand(shooter_subsystem,arm_subsystem));
    ScoreGrab.put("Intake", new IntakeExtendCommand(intake_subsystem,shooter_subsystem));
    return ScoreGrab;
  }
}