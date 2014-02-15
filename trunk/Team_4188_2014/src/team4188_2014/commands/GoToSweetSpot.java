/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4188_2014.commands;
import edu.wpi.first.wpilibj.command.Command;
import team4188_2014.Robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team4188_2014.RobotMap;
/**
 *
 * @author Owner
 */
public class GoToSweetSpot extends Command {
    
    boolean doneYet = false;
    final double SWEET_SPOT = 12.0;
    final double TOLERANCE = 0.5;
    
    public GoToSweetSpot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.vision.turnLightsOn();
            if(Robot.vision.getDistance() < SWEET_SPOT - TOLERANCE){
                Robot.drivetrain.driveAuto(0, -0.18, 0, 0);
                doneYet = false;
            }
            
            else if(Robot.vision.getDistance() > SWEET_SPOT + TOLERANCE){
                Robot.drivetrain.driveAuto(0, 0.18, 0, 0);
                doneYet = false;
            }

            else{
                Robot.drivetrain.driveAuto(0, 0, 0, 0);
                Robot.vision.turnLightsOff();
                Robot.drivetrain.turnOnLEDs();
                doneYet = true;
            }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return doneYet;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.vision.turnLightsOff();
        doneYet = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
