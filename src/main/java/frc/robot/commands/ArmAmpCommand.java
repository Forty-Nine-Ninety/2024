//Allison B)

package frc.robot.commands;
//import edu.wpi.first.wpilibj2.command.CommandBase; DO WE USE THIS?
import frc.robot.subsystems.ArmSubsystem;

public class ArmAmpCommand {
    private ArmSubsystem arm_subsystem;
    private double armPosition;
    public ArmAmpCommand(ArmSubsystem arm_subsystem){
        this.arm_subsystem = arm_subsystem;
        //arm_subsystem.addRequirements();
    }
    public void setPosition(double armPosition){
        this.armPosition = armPosition;
    }

    //@Override FROM THE COMMANDBASE IMPORT?
    public void initialize(){
        arm_subsystem.moveToPosition(armPosition);
    }
}
