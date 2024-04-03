package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

public class ZeroGyroCommand extends Command {
    private final SwerveSubsystem m_drivebase;
    public ZeroGyroCommand(SwerveSubsystem drivebase){
        m_drivebase = drivebase;
        addRequirements(drivebase);

    }
    @Override
    public void execute(){
        m_drivebase.zeroGyro();
    }    
}
