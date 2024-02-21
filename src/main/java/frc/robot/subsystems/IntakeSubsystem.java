// Shreyans

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
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

        pid_intake.setP(MotionControl.INTAKE_EXTEND_PID[0]);
        pid_intake.setI(MotionControl.INTAKE_EXTEND_PID[1]);
        pid_intake.setD(MotionControl.INTAKE_EXTEND_PID[2]);
   
        intakeExtend.setInverted(true);
        intakeRoller.setInverted(true);

        intakeExtend.setSmartCurrentLimit(15, 20);
        intakeRoller.setSmartCurrentLimit(15, 20);
    }

    public void intakePercentOutput(double percent_output_roller){
        intakeExtend.set(MotionControl.POSITION_INTAKE_EXTENDED);
        intakeRoller.set(percent_output_roller);
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
        intakeExtend.set(MotionControl.INTAKE_RETRACTED_POS);
    }
}
