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
public class ReleaseGateLatch extends Command {
    private boolean noTension = false;
    private boolean doneYet = false;
    private Timer timer;
    private double TIME_FINAL = 2.0;
    
    public ReleaseGateLatch() {
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
        if(!Robot.shooter.getGateLatch() && Robot.shooter.isExtended()) {
            CorpsLog.log("Teleop", "Shooting...", false, true);
            Robot.shooter.deployShooter();
            CorpsLog.log("Teleop", "Exiting command 'ReleaseGateLatch'...", false, true);
            doneYet = true;
        }

        else if((!Robot.shooter.getGateLatch() && Robot.shooter.isRetracted()) || noTension){
            if(!noTension){
                CorpsLog.log("Teleop", "Locking Gate Latch...", false, true);
                Robot.shooter.gateLatchReady();
                Timer.delay(0.1);
                CorpsLog.log("Teleop", "Releasing Tension...", false, true);
                Robot.shooter.releaseTension();
                noTension = true;
            }
            else{
                if(timer.get() < TIME_FINAL) Robot.shooter.doNothing();
                else {
                    CorpsLog.log("Teleop", "Shooting...", false, true);
                    Robot.shooter.deployShooter();
                    timer.stop();
                    CorpsLog.log("Teleop", "Exiting command 'ReleaseGateLatch'...", false, true);
                    doneYet = true;
                }
            }
        }
        else {
            CorpsLog.log("Teleop", "Cannot shoot... Limit switch not pressed... Try retracting shooter...", false, true);
            CorpsLog.log("Teleop", "Exiting command 'ReleaseGateLatch'...", false, true);
            doneYet = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return doneYet;
    }

    // Called once after isFinished returns true
    protected void end() {
        noTension = false;
        doneYet = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
