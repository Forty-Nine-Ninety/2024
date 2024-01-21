package frc.robot.commands;
import frc.robot.subsystems.ArmSubsystem;

public class ArmSpeakerCommand {
    private ArmSubsystem arm_subsystem;
    private double speed = 0/*SPEED */;

    public ArmSpeakerCommand(ArmSubsystem arm_subsystem){
        this.arm_subsystem = arm_subsystem;
    }
    public void initialize(){
        arm_subsystem.moveToPosition(0/*POSITION */);
    }
}
