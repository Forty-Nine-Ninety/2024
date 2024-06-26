package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RumbleCommand extends SequentialCommandGroup{
    CommandXboxController joystick;

    public RumbleCommand(CommandXboxController joystick){
        this.joystick = joystick;
        addCommands(new ParallelRaceGroup(new RumbleCommandHelper(joystick),
                                          new WaitCommand(1.0)
                                         )
                   );
    }
}
