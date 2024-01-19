import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class OuttakeSpeakerCommand extends CommandBase {
    private final ShooterSubsystem m_shooter; 
    private double speed = 1; //idk what this number should be 

    public OuttakeSpeakerCommand {
        m_shooter = shooter; //should this be intake or shooter? 
        addRequirements(m_shooter); 
    }

    @Override
    public void initialize() {
        m_shooter.shooterPercentOutput(speed); 

    }
    //ends the outtake
    public void end() {
        m_shooter.shooterPercentOutput(percent_output: 0)
    }
}