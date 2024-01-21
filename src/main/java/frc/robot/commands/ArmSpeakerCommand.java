//Allison B)

package frc.robot.commands;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSpeakerCommand {
    private ArmSubsystem arm_subsystem;

    public ArmSpeakerCommand(ArmSubsystem arm_subsystem){
        this.arm_subsystem = arm_subsystem;
    }
    public void initialize(){
        arm_subsystem.moveToPosition(0/*POSITION */);
    }
}
