/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4188_2014.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team4188_2014.CorpsLog;
import team4188_2014.Robot;
import team4188_2014.RobotMap;

/**
 *
 * @author Owner
 */
public class RetractShooter extends Command {
    private boolean hit = false;
    private boolean doneYet = false;
    private Timer timer;
    private double TIME_FINAL = 5.0;

    public RetractShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer = new Timer();
        timer.start();        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.shooter.isExtended()&& Robot.shooter.getGateLatch()) {
            if(!hit){
                CorpsLog.log("Teleop", "Releasing Gate Latch...", false, true);
                Robot.shooter.deployShooter();
                CorpsLog.log("Teleop", "Retracting Shooter...", false, true);
                Robot.shooter.retractShooter();
                hit = true;
            }
            else Robot.shooter.doNothing();
        }
        else if(Robot.shooter.isExtended()&& !Robot.shooter.getGateLatch()) {
            CorpsLog.log("Teleop", "Shooter is ready to shoot... Why did you press retract shooter again?", false, true);
            CorpsLog.log("Teleop", "Exiting command 'RetractShooter()'...", false, true);
            doneYet = true;
        }
        else Robot.shooter.doNothing();

        if(timer.get() <= TIME_FINAL) Robot.shooter.doNothing();
        else if(timer.get() > TIME_FINAL){
            if (!Robot.shooter.getGateLatch()) {
                CorpsLog.log("Teleop", "Locking Gate Latch...", false, true);
                Robot.shooter.gateLatchReady();
            } 
            else {
                CorpsLog.log("Teleop", "Limit switch not pressed...", false, true);
                Robot.shooter.doNothing();
            }
            CorpsLog.log("Teleop", "Exiting command 'RetractShooter()'...", false, true);
            timer.stop();
            doneYet = true;
            }     
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return doneYet;
    }

    // Called once after isFinished returns true
    protected void end() {
        hit = false;
        doneYet = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
