package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

public class ZeroGyroCommand extends Command {
    private final SwerveSubsystem m_swerveSubsystem;
    public ZeroGyroCommand(SwerveSubsystem swerveSubsystem){
        m_swerveSubsystem = swerveSubsystem;
    }
    @Override
    public void execute(){
        m_swerveSubsystem.zeroGyro();
    }    
}
