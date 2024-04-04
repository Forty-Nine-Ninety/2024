//Allison B)

package frc.robot.commands;
import frc.robot.subsystems.ArmSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;

public class ArmManualCommand extends Command {
    private final ArmSubsystem m_arm;
    private double mult = 36.0;
    private DoubleSupplier m_joystickSupplier; 
    
    public ArmManualCommand(ArmSubsystem arm) {
        m_arm = arm;
        addRequirements(arm);
    }

    public void setSuppliers(DoubleSupplier joystickSupplier) {
        m_joystickSupplier = joystickSupplier;
    }

    @Override
    public void execute() {
        double position = m_joystickSupplier.getAsDouble(); 
        double currentRot = m_arm.getPosition();
        m_arm.moveToPosition(currentRot+(position*mult));
    }
}
