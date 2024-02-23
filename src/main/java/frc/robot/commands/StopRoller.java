// Shreyans

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class StopRoller extends Command {
    private final IntakeSubsystem m_intakeSubsystem;

    public StopRoller(IntakeSubsystem intakeSubsystem){
        m_intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void execute(){
        m_intakeSubsystem.intakePercentOutput(0);
    }
}