//Work in progress -Chloe 
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;

public class IndexerCommand extends Command {

    private final IndexerSubsystem indexer_subsystem; 
    private double speed = 1; //might need updating 

    public IndexerCommand(IndexerSubsystem indexer_subsystem) {
        this.indexer_subsystem = indexer_subsystem;
        addRequirements(indexer_subsystem);
    }

    @Override
    public void initialize() {
        indexer_subsystem.percentOutput(speed);
    }
}