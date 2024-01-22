package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotionControl;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
    private CANSparkMax shooter1, shooter2, shooter3;
    private SparkPIDController m_pidController;
    private RelativeEncoder m_encoder;

    
    public ShooterSubsystem() {
        shooter1 = new CANSparkMax(Ports.CAN_SHOOTER_ONE_SPARKMAX, MotorType.kBrushless); //Note, I don't know the CAN IDs, will put these in later -Chloe
        shooter2 = new CANSparkMax(Ports.CAN_SHOOTER_TWO_SPARKMAX, MotorType.kBrushless);
        shooter3 = new CANSparkMax(Ports.CAN_SHOOTER_THREE_SPARKMAX, MotorType.kBrushless);

        m_pidController1 = shooter1.getPIDController();
        m_encoder1 = shooter1.getEncoder();

        m_pidController3 = shooter3.getPIDController();
        m_encoder3 = shooter3.getEncoder();
      
        configureMotors();
    }
    
    private void configureMotors() {
        shooter1.restoreFactoryDefaults(); 
        shooter2.restoreFactoryDefaults(); 
        shooter3.restoreFactoryDefaults(); //Intakes from indexer 

        m_pidController = shooter1.getPIDController();

        //Note - work in progress 
        m_pidController.setP(MotionControl.SHOOTER_PID[1]);
        m_pidController.setP(MotionControl.SHOOTER_PID[2]);
        m_pidController.setP(MotionControl.SHOOTER_PID[3]);

        shooter1.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);
        shooter2.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);
        shooter3.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);

        shooter1.setOpenLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);
        shooter2.setOpenLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);
        shooter3.setOpenLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);

        shooter2.follow(shooter1, true);

        // Numbers from last year's code 
        shooter1.setSmartCurrentLimit(22, 15);
        shooter2.setSmartCurrentLimit(22, 15);
        shooter3.setSmartCurrentLimit(22, 15);
    }
}