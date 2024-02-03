/*

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotionControl;
import frc.robot.Constants.Ports;

// Shreyans


public class IndexerSubsystem extends SubsystemBase {
    private CANSparkMax indexer;
    private SparkPIDController m_pidController;
    private RelativeEncoder m_encoder;

    public IndexerSubsystem(){
        indexer = new CANSparkMax(Ports.CAN_ARM_LEFT_SPARKMAX, MotorType.kBrushless);
        m_pidController = indexer.getPIDController();
        m_encoder = indexer.getEncoder();
        configureMotors();
    }
    private void configureMotors(){
        indexer.restoreFactoryDefaults();
        // No indexer PID
        indexer.setSmartCurrentLimit(22,25);
    }
    public void percentOutput(double percent_output) {
        indexer.set(percent_output);
    }
}

*/