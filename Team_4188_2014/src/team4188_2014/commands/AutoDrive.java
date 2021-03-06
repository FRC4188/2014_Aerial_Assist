// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package team4188_2014.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team4188_2014.Robot;
import team4188_2014.RobotMap;

/**
 *
 */
public class  AutoDrive extends Command {
    private boolean doneYet = false;
    private Timer timer;
    private double TIME_1 = 1.5;
    private double TIME_2 = 2.0;
    private double TIME_3 = 2.5;
    private double TIME_4 = 3.5;
    private double throttleValue;
    private double startLeft;
    private double startRight;

    public AutoDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
//        requires(Robot.drivetrain);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetEncoders();
//       timer = new Timer();
//       timer.start();
        startLeft = Robot.drivetrain.getRearLeft();
        startRight = Robot.drivetrain.getRearRight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        /**if(timer.get() <= TIME_4) {
            doneYet=false;
            Robot.drivetrain.driveAuto(0, 0.8, 0, 0);
        }
        else {
            doneYet = true;
        } **/
        SmartDashboard.putNumber("start left", startLeft);
        SmartDashboard.putNumber("start right", startRight);
        
        Robot.drivetrain.getEncoderValues();        
       
        if (((Robot.drivetrain.getRearLeft() - Robot.drivetrain.getRearRight()) > 5) &&
                ((Robot.drivetrain.getRearLeft() - Robot.drivetrain.getRearRight()) < 10)) throttleValue = 0.05;
        if (((Robot.drivetrain.getRearRight() - Robot.drivetrain.getRearLeft()) > 5) &&
                ((Robot.drivetrain.getRearRight() - Robot.drivetrain.getRearLeft()) < 10)) throttleValue = -0.05;
        if (((Robot.drivetrain.getRearLeft() - Robot.drivetrain.getRearRight()) > 10) &&
                ((Robot.drivetrain.getRearLeft() - Robot.drivetrain.getRearRight()) < 15)) throttleValue = 0.1;
        if (((Robot.drivetrain.getRearRight() - Robot.drivetrain.getRearLeft()) > 10) &&
                ((Robot.drivetrain.getRearRight() - Robot.drivetrain.getRearLeft()) < 15)) throttleValue = -0.1;
        if (((Robot.drivetrain.getRearLeft() - Robot.drivetrain.getRearRight()) > 15) &&
                ((Robot.drivetrain.getRearLeft() - Robot.drivetrain.getRearRight()) < 20)) throttleValue = 0.15;
        if (((Robot.drivetrain.getRearRight() - Robot.drivetrain.getRearLeft()) > 15) &&
                ((Robot.drivetrain.getRearRight() - Robot.drivetrain.getRearLeft()) < 20)) throttleValue = -0.15;
        if (((Robot.drivetrain.getRearLeft() - Robot.drivetrain.getRearRight()) > 20) &&
                ((Robot.drivetrain.getRearLeft() - Robot.drivetrain.getRearRight()) < 25)) throttleValue = 0.2;
        if (((Robot.drivetrain.getRearRight() - Robot.drivetrain.getRearLeft()) > 20) &&
                ((Robot.drivetrain.getRearRight() - Robot.drivetrain.getRearLeft()) < 25)) throttleValue = -0.2;
        
        
        if (((Robot.drivetrain.getRearLeft()- startLeft) > -400) && ((Robot.drivetrain.getRearRight() - startRight) > -400)) Robot.drivetrain.driveAuto(0, 0.6, throttleValue , 0);
        else if (((Robot.drivetrain.getRearLeft()- startLeft) > -600) && ((Robot.drivetrain.getRearRight() - startRight) > -600)) Robot.drivetrain.driveAuto(0, 0.5, throttleValue , 0);
        else if (((Robot.drivetrain.getRearLeft()- startLeft) > -750) && ((Robot.drivetrain.getRearRight() - startRight) > -750)) Robot.drivetrain.driveAuto(0, 0.3, throttleValue , 0);
        else if (((Robot.drivetrain.getRearLeft()- startLeft) > -900) && ((Robot.drivetrain.getRearRight() - startRight) > -900)) Robot.drivetrain.driveAuto(0, 0.2, throttleValue , 0);
        else if (((Robot.drivetrain.getRearLeft()- startLeft) > -1050) && ((Robot.drivetrain.getRearRight() - startRight) > -1050)) Robot.drivetrain.driveAuto(0, 0.1, throttleValue , 0);
        else {                                                                                                                      
            Robot.drivetrain.driveAuto(0, 0, 0, 0);
            doneYet = true;
        }
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return doneYet;
        
//        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        doneYet=false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
