// Chloe

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotionControl;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
    private CANSparkMax shooter1, shooter2, shooter3;
    private SparkPIDController m_pidController;
    private RelativeEncoder m_encoder;

    
    public ShooterSubsystem() {
        shooter1 = new CANSparkMax(Ports.CAN_SHOOTER_ONE_SPARKMAX, MotorType.kBrushless); //Note, I don't know the CAN IDs, will put these in later -Chloe
        shooter2 = new CANSparkMax(Ports.CAN_SHOOTER_TWO_SPARKMAX, MotorType.kBrushless);

        m_pidController = shooter1.getPIDController();
        m_encoder = shooter1.getEncoder();
      
        configureMotors();
    }
    
    private void configureMotors() {
        shooter1.restoreFactoryDefaults(); 
        shooter2.restoreFactoryDefaults(); 

        m_pidController = shooter1.getPIDController();
 
        m_pidController.setP(MotionControl.SHOOTER_PID[1]);
        m_pidController.setP(MotionControl.SHOOTER_PID[2]);

        shooter1.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);
        shooter2.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);

        shooter1.setOpenLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);
        shooter2.setOpenLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);

        //Need motor inversion?? 

        shooter1.setSmartCurrentLimit(22, 15);
        shooter2.setSmartCurrentLimit(22, 15);
    }

    public void percentOutput(double percent_output) {
        shooter1.set(percent_output);
    }
}