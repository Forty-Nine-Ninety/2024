// Chloe

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import monologue.Logged;
import monologue.Annotations.Log;

import com.revrobotics.CANSparkLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase
{
    private CANSparkMax roller1, roller2;
    private CANSparkMax preshooter;
    private final DigitalInput m_breakbeam;

    
    public ShooterSubsystem() {
        roller1 = new CANSparkMax(Ports.CAN_SHOOTER_ONE_SPARKMAX, MotorType.kBrushless); 
        roller2 = new CANSparkMax(Ports.CAN_SHOOTER_TWO_SPARKMAX, MotorType.kBrushless);
        preshooter = new CANSparkMax(Ports.CAN_PRE_SHOOTER, MotorType.kBrushless);
        m_breakbeam = new DigitalInput(Ports.PORT_DIO_BREAK_BEAM); 
      
        configureMotors();
    }
    
    private void configureMotors() {
        roller1.restoreFactoryDefaults(); 
        roller2.restoreFactoryDefaults(); 
        preshooter.restoreFactoryDefaults();
        
        roller1.setInverted(true);
        roller2.setInverted(true);
        preshooter.setInverted(false);

        roller2.follow(roller1, true);

        roller1.setSmartCurrentLimit(80);
        roller2.setSmartCurrentLimit(80);
        preshooter.setSmartCurrentLimit(20);
    }

    public void shoot(double percent_output) {
        roller1.set(percent_output);
    }

    public void stop(){
        roller1.set(0.0);
        preshooter.set(0.0);
    }

    public void preshoot(double preshooter_run_rate){
        preshooter.set(preshooter_run_rate);
    }

    // @Log
    public boolean breakBeam() {
        //Detects if anything is obstructing the break beam 
        return !m_breakbeam.get();
    }
}