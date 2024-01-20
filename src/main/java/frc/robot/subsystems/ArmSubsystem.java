package frc.robot.subsystems;
import frc.robot.Constants.*;


// Shreyans


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase{
    private CANSparkMax armLeft, armRight;
    private SparkPIDController m_pidController;
    private RelativeEncoder m_encoder;

    public ArmSubsystem(){
        armLeft = new CANSparkMax(Ports.CAN_ARM_LEFT_SPARKMAX, MotorType.kBrushless);
        armRight = new CANSparkMax(Ports.CAN_ARM_RIGHT_SPARKMAX, MotorType.kBrushless);
        m_pidController = armRight.getPIDController();
        m_encoder = armRight.getEncoder();
        configureMotors();
    }
    private void configureMotors(){
        armLeft.restoreFactoryDefaults();
        armRight.restoreFactoryDefaults();

        m_pidController.setP(MotionControl.ARM_PID[0]);
        m_pidController.setI(MotionControl.ARM_PID[1]);
        m_pidController.setD(MotionControl.ARM_PID[2]);

        armLeft.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE); 
        armRight.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE);
        armLeft.setOpenLoopRampRate (MotionControl.OPEN_LOOP_RAMP_RATE); 
        armRight.setOpenLoopRampRate (MotionControl.OPEN_LOOP_RAMP_RATE);

        armLeft.follow(armRight, true);
        armLeft.setSmartCurrentLimit(22,25);
        armRight.setSmartCurrentLimit(22,25);
    }

    public void moveToPosition(double setPoint){
        m_pidController.setReference(setPoint, CANSparkBase.ControlType.kPosition);
    }
    public void resetArmPosition(){
        m_encoder.setPosition(0);
    }
    public double getPosition(){
        return (m_encoder.getPosition());
    } 
}