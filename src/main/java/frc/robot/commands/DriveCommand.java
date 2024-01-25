package frc.robot.commands;

import frc.robot.Constants.*;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;

public class DriveCommand extends Command{
    private final SwerveSubsystem m_drivebase;
    private DoubleSupplier m_translationX, m_translationY, m_headingX, m_headingY;

    public DriveCommand(SwerveSubsystem drivebase) {
        m_drivebase = drivebase;
        addRequirements(drivebase);
    }

    public void setSuppliers(DoubleSupplier translationX, DoubleSupplier translationY, DoubleSupplier headingX, DoubleSupplier headingY) {
        m_translationX = translationX;
        m_translationY = translationY;
        m_headingX = headingX;
        m_headingY = headingY;
    }

    @Override
    public void execute() {
        double xInput = Math.pow(m_translationX.getAsDouble(), DriveSettings.JOYSTICK_THROTTLE_X_EXPONENT); // Smooth control out
        double yInput = Math.pow(m_translationY.getAsDouble(), DriveSettings.JOYSTICK_THROTTLE_Y_EXPONENT); // Smooth control out

        // Make the robot move
        m_drivebase.driveFieldOriented(m_drivebase.getSwerveController().getTargetSpeeds(
                                        -xInput, 
                                        -yInput,
                                        m_headingX.getAsDouble(),
                                        m_headingY.getAsDouble(),
                                        m_drivebase.getSwerveDrive().getYaw().getRadians(),
                                        m_drivebase.getSwerveDrive().getMaximumVelocity()));
    }
}
