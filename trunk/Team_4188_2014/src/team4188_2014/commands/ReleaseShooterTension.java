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
public class ReleaseShooterTension extends Command {
    private boolean gateLatchReady = false;
    private boolean doneYet = false;
    
    private Timer timer;
    private final double TIME_1 = 1.0;
    public ReleaseShooterTension() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!Robot.shooter.getGateLatch() && Robot.shooter.isRetracted()){
            if(!gateLatchReady){
                   CorpsLog.log("Teleop", "Locking Gate Latch...", false, true);
                   Robot.shooter.gateLatchReady();
                   gateLatchReady = true;
               }
            else{
                   if(timer.get() >= TIME_1){
                       CorpsLog.log("Teleop", "Releasing Tension...", false, true);
                       Robot.shooter.releaseTension();
                       CorpsLog.log("Teleop", "Press trigger again to shoot...", false, true);
                       doneYet = true;
                   }
                   else Robot.shooter.doNothing();
               }
        }
        else if(Robot.shooter.isExtended()&& !Robot.shooter.getGateLatch()) {
            CorpsLog.log("Teleop", "Shooter is ready to shoot... Why did you press release shooter tension again?", false, true);
            CorpsLog.log("Teleop", "Exiting command 'ReleaseShooterTension()'...", false, true);
            doneYet = true;
        }
        else {
            CorpsLog.log("Teleop", "Exiting command 'ReleaseShooterTension()'... Need to retract shooter first", false, true);
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
        gateLatchReady = false;
        doneYet = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
