// Chloe

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
    private CANSparkMax shooter1, shooter2;
    private CANSparkMax indexer1;
    private SparkPIDController m_pidController;
    private RelativeEncoder m_encoder;
    private final DigitalInput m_breakbeam;

    
    public ShooterSubsystem() {
        shooter1 = new CANSparkMax(Ports.CAN_SHOOTER_ONE_SPARKMAX, MotorType.kBrushless); 
        shooter2 = new CANSparkMax(Ports.CAN_SHOOTER_TWO_SPARKMAX, MotorType.kBrushless);
        indexer1 = new CANSparkMax(Ports.CAN_SHOOTER_INDEXER, MotorType.kBrushless);
        m_breakbeam = new DigitalInput(Ports.PORT_DIO_BREAK_BEAM); 
        m_pidController = shooter1.getPIDController();
        m_encoder = shooter1.getEncoder();
      
        configureMotors();
    }
    
    private void configureMotors() {
        shooter1.restoreFactoryDefaults(); 
        shooter2.restoreFactoryDefaults(); 
        indexer1.restoreFactoryDefaults();
        
        shooter1.setInverted(true);
        indexer1.setInverted(false);
        shooter2.setInverted(true);
        
        //m_pidController.setP(MotionControl.SHOOTER_PID.kP);
        //m_pidController.setI(MotionControl.SHOOTER_PID.kI);
        //m_pidController.setD(MotionControl.SHOOTER_PID.kD);

        //shooter1.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);
        //shooter2.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);

        //shooter1.setOpenLoopRampRate(MotionControl.OPEN_LOOP_RAMP_RATE);
        //shooter2.setOpenLoopRampRate(MotionControl.OPEN_LOOP_RAMP_RATE);

        shooter2.follow(shooter1, true);

        shooter1.setSmartCurrentLimit(60);
        shooter2.setSmartCurrentLimit(60);
        indexer1.setSmartCurrentLimit(20);
    }

    public void shoot(double percent_output) {
        shooter1.set(percent_output);
    }

    public void indexerToShooter(double indexer_run_rate){
        indexer1.set(indexer_run_rate);
    }

    public boolean breakBeam() {
        //Detects if anything is obstructing the break beam 
        return !m_breakbeam.get();
    }
}