package frc.robot.commands;

import frc.robot.Constants.*;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.DriveUtil;
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
        double xInput = DriveUtil.powCopySign(m_translationX.getAsDouble(), DriveSettings.JOYSTICK_THROTTLE_X_EXPONENT) * DriveSettings.ARCADE_SPEED_X_MULTIPLIER; // Smooth control out
        double yInput = DriveUtil.powCopySign(m_translationY.getAsDouble(), DriveSettings.JOYSTICK_THROTTLE_Y_EXPONENT) * DriveSettings.ARCADE_SPEED_Y_MULTIPLIER; // Smooth control out

        // Make the robot move
        m_drivebase.driveFieldOriented(m_drivebase.getSwerveController().getTargetSpeeds(
                                        xInput, 
                                        yInput,
                                        m_headingX.getAsDouble() * DriveSettings.ARCADE_ROTATION_MULTIPLIER,
                                        m_headingY.getAsDouble() * DriveSettings.ARCADE_ROTATION_MULTIPLIER,
                                        m_drivebase.getSwerveDrive().getOdometryHeading().getRadians(),
                                        m_drivebase.getSwerveDrive().getMaximumVelocity()));
    }
}