/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4188_2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import team4188_2014.Robot;
import team4188_2014.RobotMap;

/**
 *
 * @author Owner
 */
public class FlashLEDs extends Command {
    
    public FlashLEDs() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.lights);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.lights.areLEDsOn()){
            Robot.lights.turnOffLEDs();
        }
        else{
            Robot.lights.turnOnLEDs();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
