//Allison B)

package frc.robot.commands;
import frc.robot.Constants.RobotMeasurements;
import frc.robot.Constants.OurUnits;
import frc.robot.subsystems.ArmSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;

public class ArmManualCommand extends Command {
    private final ArmSubsystem m_armsubsystem;
    private double mult = 36.0;
    private DoubleSupplier m_joystickSupplier; 
    
    public ArmManualCommand(ArmSubsystem armsubsystem) {
        m_armsubsystem = armsubsystem;
        addRequirements(armsubsystem);
    }

    public void setSuppliers(DoubleSupplier joystickSupplier) {
        m_joystickSupplier = joystickSupplier;
    }

    @Override
    public void execute() {
        double position = m_joystickSupplier.getAsDouble(); 
        double currentRot = m_armsubsystem.getPosition();
        m_armsubsystem.moveToPosition(currentRot+(position*mult));
    }
}
