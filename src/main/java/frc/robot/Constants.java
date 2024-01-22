// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import frc.robot.ourunits.*;
import swervelib.math.Matter;
import swervelib.parser.PIDFConfig;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{
  //public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag

  public static class Ports
  {
    public static int PORT_JOYSTICK_DRIVE = 0;
    public static int PORT_JOYSTICK_OPERATOR = 1;

    //CAN IDs (created by Shreyans: ask Isaac for help since he knows more than me)
    public static final int CAN_ARM_LEFT_SPARKMAX = 9;
    public static final int CAN_ARM_RIGHT_SPARKMAX = 10;
    public static final int CAN_INDEXER_SPARKMAX = 11;
    public static final int CAN_SHOOTER_ONE_SPARKMAX = 0; //Note I don't know the CAN IDs for the shooter, I'll put them in later -Chloe
  }
  
  public static class MotorConfig
  {
    public static double CANANDCODER_RESOLUTION = 16384.0;
  }

  public static class RobotMeasurements
  {
    public static double DRIVETRAIN_WHEEL_DIAMETER_IN = 4.0;
    public static final double DRIVE_GEAR_RATIO = 6.75;
    public static final double STEERING_GEAR_RATIO = 150.0/7.0;

    public static double ARM_MOTION_REDUCTION = 1000.0/3.0;

    //public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
    //public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  }

  public static class OurUnits
  {
    //Base units
    public static Unit METER = new BaseUnit(Dimension.Length, 1d);
    public static Unit KILOMETER = new BaseUnit(Dimension.Length, METER.getScalar() * 1000d);
    public static Unit FEET = new BaseUnit(Dimension.Length, METER.getScalar() * 3.280839895d);
    public static Unit INCH = new BaseUnit(Dimension.Length, FEET.getScalar() / 12d);

    public static Unit SECOND = new BaseUnit(Dimension.Time, 1d);
    public static Unit MINUTE = new BaseUnit(Dimension.Time, SECOND.getScalar() * 60d);
    public static Unit HOUR = new BaseUnit(Dimension.Time, MINUTE.getScalar() * 60d);
    public static Unit MILLISECOND = new BaseUnit(Dimension.Time, SECOND.getScalar() / 1000d);
    //public static Unit ENCODER_TIME = new BaseUnit(Dimension.Time, MILLISECOND.getScalar() * 100d);
        
    public static Unit KILOGRAM = new BaseUnit(Dimension.Mass, 1d);

    public static Unit RADIAN = new BaseUnit(Dimension.Angle, 1d);
    public static Unit REVOLUTION = new BaseUnit(Dimension.Angle, RADIAN.getScalar() * 2d * Math.PI);
    public static Unit DEGREE = new BaseUnit(Dimension.Angle, REVOLUTION.getScalar() / 360d);
    // Angle represented by encoder ticks, i.e 4096 ticks is a full revolution
    public static Unit ENCODER_ANGLE = new BaseUnit(Dimension.Angle, REVOLUTION.getScalar() / MotorConfig.CANANDCODER_RESOLUTION);

    public static Unit AMPERE = new BaseUnit(Dimension.Current, 1d);

    //Compound units
    //public static Unit ENCODER_VELOCITY_UNIT = new CompoundUnit(ENCODER_ANGLE, ENCODER_TIME);
    public static Unit ENCODER_ANGULAR_VELOCITY = new CompoundUnit(ENCODER_ANGLE, SECOND);

    public static Unit ANGULAR_VELOCITY = new CompoundUnit(RADIAN, SECOND);
    public static Unit METERS_PER_SECOND = new CompoundUnit(METER, SECOND);

    public static Unit METERS_PER_SECOND_2 = new CompoundUnit(METERS_PER_SECOND, SECOND);
    public static Unit NEWTON = new CompoundUnit(new Unit[] {KILOGRAM, METERS_PER_SECOND_2}, new Unit[] {});
    public static Unit JOULE = new CompoundUnit(new Unit[] {NEWTON, METER}, new Unit[] {});
    public static Unit COULOMB = new CompoundUnit(new Unit[] {AMPERE, SECOND}, new Unit[] {});
    public static Unit VOLTAGE = new CompoundUnit(JOULE, COULOMB);
  }

  public static class SubsystemConfig
  {
    //Movement information
    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class MotionControl
  {
    //PID
    public static final double[] ARM_PID = {0, 0, 0}; // CHANGE PID LATER (this is for ArmSubsystem.java)
    public static final double[] INDEXER_PID = {0, 0, 0}; // CHANGE PID LATER (this is for IndexerSubsystem.java)
    public static final double[] SHOOTER_PID = {0, 0, 0}; //CHANGE PID LATER (this is for ShooterSubsystem.java)
    
    public static final double CLOSED_LOOP_RAMP_RATE = 0.6;
    public static final double OPEN_LOOP_RAMP_RATE = 0.6;
  }

  public static final class Auton
  {
    public static final PIDFConfig TranslationPID = new PIDFConfig(0.7, 0, 0);
    public static final PIDFConfig angleAutoPID   = new PIDFConfig(0.4, 0, 0.01);

    public static final double MAX_ACCELERATION = 2;
  }

  public static class DriveSettings
  {
    // Joystick Deadband
    public static final double LEFT_X_DEADBAND  = 0.01;
    public static final double LEFT_Y_DEADBAND  = 0.01;
    public static final double RIGHT_X_DEADBAND = 0.01;
    public static final double TURN_CONSTANT    = 6;

    //Driver settings
    public static double JOYSTICKF310_AXIS_DEADBAND = 0.01;
    public static double JOYSTICK_THROTTLE_X_EXPONENT = 3;
    public static double JOYSTICK_THROTTLE_Y_EXPONENT = 3;
    public static double JOYSTICK_TURNING_EXPONENT = 3;
    public static double ARCADE_SPEED_X_MULTIPLIER = 0.2;
    public static double ARCADE_SPEED_Y_MULTIPLIER = 0.2;
    public static double ARCADE_ROTATION_MULTIPLIER = 0.2;
  }
}