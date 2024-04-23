

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class IntakeExtendCommand extends Command{
    private final IntakeSubsystem m_intake;
    private final ShooterSubsystem m_shooter;
    private final RumbleCommand m_rumble;

    public IntakeExtendCommand(IntakeSubsystem intake, ShooterSubsystem shooter,CommandXboxController drive,CommandXboxController operator){
        m_intake = intake;
        m_shooter = shooter;
        m_rumble = new RumbleCommand(drive,operator);
        addRequirements(intake, shooter);

    }

    @Override
    public void initialize(){
        m_intake.extendIntake();
    }

    @Override
    public void execute(){
        if(m_shooter.breakBeam()){
            cancel();
            m_rumble.rumble(1);
        } else {
            m_intake.intakePercentOutput(1);
            m_shooter.preshoot(-0.2);
        }
    }

    @Override
    public void end(boolean interrupted){
        m_intake.intakePercentOutput(0);
        m_shooter.preshoot(0);
        m_intake.retractIntake();
        m_rumble.rumble(1);
    }
}
