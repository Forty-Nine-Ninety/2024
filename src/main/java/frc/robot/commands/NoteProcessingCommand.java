package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class NoteProcessingCommand extends SequentialCommandGroup{
    IntakeSubsystem m_intakesubsystem = new IntakeSubsystem();
 //   IndexerSubsystem m_indexersubsystem = new IndexerSubsystem();
    public NoteProcessingCommand(IntakeSubsystem intake){
        m_intakesubsystem = intake;
     //   m_indexersubsystem = indexer;
        
// should this "Addcommands thing be here since its only 1 command?"
        addCommands(
            new IntakeToIndexerCommand(m_intakesubsystem)
          //  new IndexerCommand(m_indexersubsystem)
        );
    }  
}
