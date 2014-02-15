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
public class ShooterPass extends Command {
    private boolean gateDeployed = false;
    private boolean doneYet = false;
    
    private Timer timer;
    private final double TIME_1 = 1.0;
    public ShooterPass() {
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
        if(Robot.shooter.isRetracted()){
            if(!gateDeployed){
                   CorpsLog.log("Teleop", "Releasing Gate Latch...", false, true);
                   Robot.shooter.deployShooter();
                   gateDeployed = true;
               }
            else{
                   if(timer.get() >= TIME_1){
                       CorpsLog.log("Teleop", "Releasing Tension...", false, true);
                       Robot.shooter.releaseTension();
                       CorpsLog.log("Teleop", "Passing ball with shooter...", false, true);
                       doneYet = true;
                   }
                   else Robot.shooter.doNothing();
               }
        }
        else if(Robot.shooter.isExtended() && Robot.shooter.getGateLatch()) {
            CorpsLog.log("Teleop", "Your tension is already released... Need to retract shooter...", false, true);
            CorpsLog.log("Teleop", "Exiting command 'ShooterPass()'...", false, true);
            doneYet = true;
        }
        else {
            CorpsLog.log("Teleop", "You are in firing position... Retract shooter and lock to continue...", false, true);
            CorpsLog.log("Teleop", "Exiting command 'ShooterPass()'...", false, true);
            Robot.shooter.doNothing();
            doneYet = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return doneYet;
    }

    // Called once after isFinished returns true
    protected void end() {
        gateDeployed = false;
        doneYet = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}