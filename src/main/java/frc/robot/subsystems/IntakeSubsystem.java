// Shreyans

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.IdleMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private CANSparkMax intakeExtend, intakeRoller;
    private SparkPIDController pid_intake;
    private RelativeEncoder pid_encoder;

    public IntakeSubsystem(){
        intakeExtend = new CANSparkMax(Ports.CAN_INTAKE_SPARKMAX_EXTEND, MotorType.kBrushless);
        intakeRoller = new CANSparkMax(Ports.CAN_INTAKE_SPARKMAX_ROLLER, MotorType.kBrushless);
        pid_intake = intakeExtend.getPIDController();
        pid_encoder = intakeExtend.getEncoder();
        configureMotors();
    }
    private void configureMotors(){
        intakeExtend.restoreFactoryDefaults();
        intakeRoller.restoreFactoryDefaults();

        pid_intake.setP(MotionControl.INTAKE_EXTEND_PID.kP);
        pid_intake.setI(MotionControl.INTAKE_EXTEND_PID.kI);
        pid_intake.setD(MotionControl.INTAKE_EXTEND_PID.kD);
        
        intakeExtend.setClosedLoopRampRate(MotionControl.CLOSED_LOOP_RAMP_RATE); 
        intakeExtend.setOpenLoopRampRate(MotionControl.OPEN_LOOP_RAMP_RATE); 

        intakeExtend.setInverted(true);
        intakeRoller.setInverted(true);

        intakeExtend.setSmartCurrentLimit(10);
        intakeRoller.setSmartCurrentLimit(40);

        intakeExtend.setIdleMode(IdleMode.kBrake);
        //intakeRoller.setIdleMode(IdleMode.kBrake);
    }
    public void intakePercentOutput(double percent_output_roller){
        intakeRoller.set(percent_output_roller);
    }

    public void extendIntake(){
       pid_intake.setReference(MotionControl.POSITION_INTAKE_EXTENDED,CANSparkBase.ControlType.kPosition);
    }
    
    public void positionPID(double position){
        pid_intake.setReference(position, CANSparkBase.ControlType.kPosition);
    }

    public double intakeCurrentDrawExtend(){
        return intakeExtend.getOutputCurrent();
    }

    public double intakeCurrentDrawRoller(){
        return intakeRoller.getOutputCurrent();
    }

    public void retractIntake(){
        pid_intake.setReference(MotionControl.INTAKE_RETRACTED_POS,CANSparkBase.ControlType.kPosition);
    }
}

// there is no "shared motor" between these 2 subsystems but actually only one subsystem >:)