package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RumbleCommand extends Command{
    CommandXboxController joystickDrive;
    CommandXboxController joystickOperator;

    public RumbleCommand(CommandXboxController joystickDrive,CommandXboxController joystickOperator){
        this.joystickDrive = joystickDrive;
        this.joystickOperator = joystickOperator;
    }

    @Override
    public void initialize(){
        joystickDrive.getHID().setRumble(RumbleType.kBothRumble,1);
    }

    @Override
    public void execute(){
        joystickDrive.getHID().setRumble(RumbleType.kBothRumble,1);
        //new WaitCommand(1.0);
        //joystickDrive.getHID().setRumble(RumbleType.kBothRumble,0);
    }

    @Override
    public void end(boolean interrupted){
        joystickOperator.getHID().setRumble(RumbleType.kBothRumble,0);
    }

    public void rumble(int joystick /*0,1*/){
        if(joystick==0){
            joystickDrive.getHID().setRumble(RumbleType.kBothRumble,1);
            new WaitCommand(0.5);
            joystickDrive.getHID().setRumble(RumbleType.kBothRumble,0);
        }
        else{
            joystickOperator.getHID().setRumble(RumbleType.kBothRumble,1);
            new WaitCommand(0.5);
            joystickOperator.getHID().setRumble(RumbleType.kBothRumble,0);
        }
    }
}
